package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.config.properties.AlipayProperties;
import com.wang.mapper.*;
import com.wang.pojo.*;
import com.wang.pojo.bo.AddOrderBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.CommentBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.*;
import com.wang.service.user.UserBookService;
import com.wang.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:51
 * @description
 */

@Service
@Slf4j
public class UserBookServiceImpl implements UserBookService {

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookTypeMapper bookTypeMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AlipayProperties alipayProperties;
    @Resource
    private CarMapper carMapper;

    @Override
    public TypeVo getTypeList() {
        List<Type> types = typeMapper.selectList(null);
        TypeVo typeVo = BeanUtil.copyProperties(types, TypeVo.class);
        return typeVo;
    }

    @Override
    public PageData<BookVo> getBookList(BookSearchBo searchBo, PageQuery pageQuery) {
        // 查询是否是指定分类
        List<Long> bookIds = null;
        if (ObjUtil.isNotEmpty(searchBo.getType())) {
            List<BookType> bookTypes = bookTypeMapper.selectList(new LambdaQueryWrapper<BookType>()
                    .select(BookType::getBookId)
                    .eq(BookType::getTypeId, searchBo.getType())
                    .orderByAsc(BookType::getBookId));
            if (ObjUtil.isNotEmpty(bookTypes)) {
                bookIds = bookTypes.stream()
                        .map(BookType::getBookId)
                        .collect(Collectors.toList());
            }
        }
        Page<Book> bookPage = bookMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Book>()
                .select(Book::getBookId, Book::getBookName, Book::getBookCover, Book::getBookAuthor, Book::getBookPrice)
                .in(ObjectUtil.isNotNull(bookIds), Book::getBookId, bookIds)
                .eq(Book::getStatus, 1)
                .like(searchBo.getPattern().intValue() == 1, Book::getBookName, searchBo.getKey())
                .eq(searchBo.getPattern().intValue() == 2, Book::getBookAuthor, searchBo.getKey())
                .eq(searchBo.getPattern().intValue() == 3, Book::getBookIsbn, searchBo.getKey())
        );
        List<BookVo> bookVos = BeanUtil.copyToList(bookPage.getRecords(), BookVo.class);

        return PageData.build(bookPage.getTotal(), bookVos);
    }

    @Override
    public BookVo getBookInfo(Long bookId) {
        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookId, bookId)
                .eq(Book::getStatus, 1));
        BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
        return bookVo;
    }

    @Override
    public PageData<CommentVo> CommentList(Long bookId, PageQuery pageQuery) {
        // 先获取父评论列表
        Page<Comment> commentPage = commentMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Comment>()
                .eq(Comment::getBookId, bookId)
                .eq(Comment::getStatus, 1)
                .eq(Comment::getCmParentId, 0L)
                .orderByDesc(Comment::getCmDate));
        // TODO　给每条评论设置设置用户信息
        List<CommentVo> commentVos = BeanUtil.copyToList(commentPage.getRecords(), CommentVo.class);
        commentVos.stream().forEach(comment -> {
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .select(User::getUsername, User::getAvatar)
                    .eq(User::getUserId, comment.getUserId()));
            comment.setUsername(user.getUsername());
            comment.setAvatar(user.getAvatar());
            // 获取子评论
            List<Comment> childComments = commentMapper.selectList(new LambdaQueryWrapper<Comment>()
                    .eq(Comment::getStatus, 1)
                    .eq(Comment::getCmParentId, comment.getCmId())
                    .orderByDesc(Comment::getCmDate)
            );
            List<CommentVo> childCommentVos = BeanUtil.copyToList(childComments, CommentVo.class);
            // 子评论设置用户信息
            childCommentVos.stream().forEach(child -> {
                User user2 = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .select(User::getUsername, User::getAvatar)
                        .eq(User::getUserId, child.getUserId()));
                child.setUsername(user2.getUsername());
                child.setAvatar(user2.getAvatar());
            });
        });
        return PageData.build(commentPage.getTotal(), commentVos);
    }

    @Override
    public Boolean setComment(CommentBo bo) {
        // 父评论id是否为0，为0代表是根评论
        if (ObjectUtil.isNull(bo)) {
            return false;
        }
        if (bo.getBookId().intValue() != 0) {
            // 获取父评论是否存在
            Comment comment = commentMapper.selectOne(new LambdaQueryWrapper<Comment>()
                    .select(Comment::getStatus)
                    .eq(Comment::getCmId, bo.getCmParentId()));
            if (comment.getStatus().intValue() == 0) {
                return false;
            }
        }
        Comment comment = BeanUtil.copyProperties(bo, Comment.class);
        comment.setCmParentId(bo.getCmParentId());
        comment.setCmDate(new Date());
        // 获取用户id
        comment.setUserId(SecurityUtil.getUserId());
        int insert = commentMapper.insert(comment);
        return insert == 1;
    }

    @Override
    public Boolean addCar(AddOrderBo bo) {
        if (ObjectUtil.isNull(bo)) {
            return false;
        }
        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookId, bo.getBookId()));
        if (ObjectUtil.isNull(book) || book.getStatus().intValue() == 0) {
            return false;
        }
        // TODO 加入redis
        return null;
    }

    @Override
    public String buyBook(AddOrderBo bo) {
        if (ObjectUtil.isNull(bo)) {
            return "请传入非空信息";
        }
        Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                .eq(Book::getBookId, bo.getBookId()));
        if (ObjectUtil.isNull(book) || book.getStatus().intValue() == 0) {
            return "该书籍已下架";
        }
        if (book.getBookStock() - bo.getOrderNum() < 0) {
            return "书籍库存不够了";
        }
        // TODO 构造订单
        Order order = new Order();
        order.setBookId(bo.getBookId());
        // 用户id
        order.setUserId(SecurityUtil.getUserId());
        order.setOrderDate(new Date());
        order.setOrderNum(bo.getOrderNum());
        order.setStatus(0);
        // 计算价格
        BigDecimal price = book.getBookPrice().multiply(new BigDecimal(bo.getOrderNum()));
        order.setOrderPrice(price);
        orderMapper.insert(order);

        // 库存减少
        bookMapper.update(null, new LambdaUpdateWrapper<Book>()
                .eq(Book::getBookId, book.getBookId())
                .set(Book::getBookStock, book.getBookStock() - bo.getOrderNum()));
        // TODO 支付宝付款
        return sendRequestToAlipay(Long.toString(order.getOrderId()), price.toPlainString(), book.getBookName());
    }


    /**
     * 付款
     *
     * @param outTradeNo  订单ID（自定义生成，在调用方法前生成）
     * @param totalAmount 订单金额
     * @param subject     订单描述
     * @return
     */
    private String sendRequestToAlipay(String outTradeNo, String totalAmount, String subject) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getGatewayUrl(), alipayProperties.getAppId(), alipayProperties.getAppPrivateKey(),
                "JSON", "UTF-8", alipayProperties.getAlipayPublicKey(), alipayProperties.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyUrl());
        // 默认写法，不可更改
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        // 返回响应体
        return result;
    }


    /**
     * 同步回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) {
        log.info("=================================同步回调=====================================");
        // TODO 进行相应的操作

    }

    /**
     * 异步回调
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    public String notifyUrl(HttpServletRequest request) throws AlipayApiException {
        log.info("=================================异步回调=====================================");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(), "UTF-8", alipayProperties.getSignType()); // 调用SDK验证签名
        //验证签名通过
        if (signVerified) {
            log.info("交易名称: " + params.get("subject"));
            log.info("交易状态: " + params.get("trade_status"));
            log.info("支付宝交易凭证号: " + params.get("trade_no"));
            log.info("商户订单号: " + params.get("out_trade_no"));
            log.info("交易金额: " + params.get("total_amount"));
            log.info("买家在支付宝唯一id: " + params.get("buyer_id"));
            log.info("买家付款时间: " + params.get("gmt_payment"));
            log.info("买家付款金额: " + params.get("buyer_pay_amount"));
            log.info("重定向URL: " + params.get("body"));

            // TODO 支付成功，修改数据库等信息
            Integer res = orderMapper.update(null, new LambdaUpdateWrapper<Order>()
                    .set(Order::getBuyDate, new Date())
                    .set(Order::getAlipayNo, params.get("trade_no"))
                    .set(Order::getStatus, 1));
            if (res >= 1) {
                //跳转付款成功页面
                return "ok";
            }
            // 支付宝失败退款
            //跳转付款失败页面
            return "no";
        } else {
            //跳转付款失败页面
            return "no";
        }
    }

    @Override
    public PageData<OrderVo> orderList(PageQuery pageQuery) {
        Page<Order> orderPage = orderMapper.selectPage(pageQuery.build(), null);
        List<OrderVo> orders = new ArrayList<>(orderPage.getRecords().size());
        orderPage.getRecords().forEach(order -> {
            Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                    .select(Book::getBookName, Book::getBookCover)
                    .eq(Book::getBookId, order.getBookId()));
            OrderVo orderVo = new OrderVo();
            orderVo.setOrderId(orderVo.getOrderId());
            orderVo.setBookId(order.getBookId());
            orderVo.setOrderNum(order.getOrderNum());
            orderVo.setStatus(order.getStatus());
            orderVo.setBookName(book.getBookName());
            orderVo.setBookCover(book.getBookCover());
            orderVo.setOrderDate(order.getOrderDate());
            orderVo.setBuyDate(order.getBuyDate());
            orderVo.setOrderPrice(order.getOrderPrice());
            orders.add(orderVo);
        });
        return PageData.build(orderPage.getTotal(), orders);
    }

    @Override
    public PageData<CarVo> carList(PageQuery pageQuery) {

        Page<Car> carPage = carMapper.selectPage(pageQuery.build(), null);
        List<CarVo> cars = new ArrayList<>(carPage.getRecords().size());
        carPage.getRecords().forEach(car -> {
            Book book = bookMapper.selectOne(new LambdaQueryWrapper<Book>()
                    .select(Book::getBookName, Book::getBookCover, Book::getBookAuthor,
                            Book::getBookIsbn, Book::getBookPrice)
                    .eq(Book::getBookId, car.getBookId()));
            CarVo carVo = new CarVo();
            carVo.setBookId(car.getBookId());
            carVo.setBookName(book.getBookName());
            carVo.setBookAuthor(book.getBookAuthor());
            carVo.setBookCover(book.getBookCover());
            carVo.setBookIsbn(book.getBookIsbn());
            carVo.setOrderNum(car.getOrderNum());
            BigDecimal price = book.getBookPrice().multiply(new BigDecimal(car.getOrderNum()));
            carVo.setOrderPrice(price);
            cars.add(carVo);
        });

        return PageData.build(carPage.getTotal(), cars);
    }


}

package com.wang;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.config.AliyunConfig;
import com.wang.mapper.BookMapper;
import com.wang.mapper.UserMapper;
import com.wang.pojo.Book;
import com.wang.pojo.User;
import com.wang.service.AsyncService;
import com.wang.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-13-21:21
 * @description
 */

@SpringBootTest
public class TestDemo {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private AliyunConfig aliyunConfig;
    @Resource
    private AsyncService asyncService;

    public static void main(String[] args) {

    }

    @Test
    public void testRedis() {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getPhone)
                .eq(User::getUserId, 1L)
                .eq(User::getStatus, 1));
        System.out.println(user);
    }


    @Test
    public void testAddBooks() throws IOException {
        File file = ResourceUtils.getFile("classpath:books.json");
        String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));
        List<Book> books = JSON.parseArray(jsonString, Book.class);
        books.forEach(book -> {
            book.setBookAddDate(new Date());
            book.setStatus(1);
            book.setBookStock(new Random().nextInt(20) % +20);
            if (ObjUtil.isEmpty(book.getBookPrice())) {
                book.setBookPrice(new BigDecimal("55.00"));
            }
            InputStream is = getInputStream(book.getBookCover());
            String fileName = UUID.randomUUID().toString();
            String filePath = new StringBuilder(aliyunConfig.getRootPath())
                    .append("/images/")
                    .append(fileName)
                    .append(".jpg")
                    .toString();
            // 上传到阿里云OSS
            asyncService.uploadImg(filePath, is);

            book.setBookCover(aliyunConfig.getUrlPrefix() + filePath);
            bookMapper.insert(book);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("添加成功");
    }

    private static InputStream getInputStream(String str) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(str);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputStream;
    }


}

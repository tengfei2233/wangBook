package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.pojo.Order;
import com.wang.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 14:53
 * @Description
 **/

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    List<BookVo> selectHotBooks(@Param("count") Integer count);
}

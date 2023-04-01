package com.wang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.pojo.Book;
import org.springframework.stereotype.Repository;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-21:33
 * @description
 */

@Repository
public interface BookMapper extends BaseMapper<Book> {
}

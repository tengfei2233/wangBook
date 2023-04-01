package com.wang.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-22:10
 * @description
 */

@Data
@ApiModel("分页数据")
public class PageData<T> {


    private Long total;

    private List<T> data;


    public static <T> PageData<T> build(IPage<T> page) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotal(page.getTotal());
        pageData.setData(page.getRecords());
        return pageData;
    }

    public static <T> PageData<T> build(Long total, List<T> data) {
        PageData<T> pageData = new PageData<>();
        pageData.setTotal(total);
        pageData.setData(data);
        return pageData;
    }
}

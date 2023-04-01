package com.wang.pojo.bo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-21:08
 * @description
 */

@Data
@ApiModel("分页参数")
public class PageQuery {

    @ApiModelProperty("一页大小，默认10")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页，默认第1页")
    private Integer pageNum = 1;

    public <T> Page<T> build() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        Page<T> page = new Page<>(pageNum, pageSize);
        return page;
    }

}

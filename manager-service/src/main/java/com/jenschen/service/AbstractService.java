package com.jenschen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.constant.CommonConstant;
import com.jenschen.request.Page;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> {

    /**
     * 返回分页查询的QueryWrapper
     * @param page 分页
     * @return 返回分页，未删除的查询
     */
    protected QueryWrapper<T> getPageQueryWrapper(Page page){
        QueryWrapper<T> queryWrapper = this.getDefaultQuery();

        if(page.getDescOderBy() == null){
            List<String> cols = new ArrayList<>();
            cols.add(CommonConstant.UPDATED_AT);
            page.setDescOderBy(cols);
        }

        if(page.getAscOderBy() != null){
            queryWrapper.orderByAsc(page.getAscOderBy().toArray(new String[0]));
        }

        if(page.getDescOderBy() != null){
            queryWrapper.orderByDesc(page.getDescOderBy().toArray(new String[0]));
        }

        queryWrapper.last("limit " + (page.getCurrentPage() - 1) * page.getPageSize() + "," + page.getPageSize());
        return queryWrapper;
    }

    /**
     * 返回默认QueryWrapper （未删除）
     * @return 返回默认没有删除的查询
     */
    protected QueryWrapper<T> getDefaultQuery(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", CommonConstant.DEFAULT);
        return queryWrapper;
    }

}

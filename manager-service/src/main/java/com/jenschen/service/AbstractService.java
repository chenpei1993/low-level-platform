package com.jenschen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.constant.CommonConstant;
import com.jenschen.request.Page;

public abstract class AbstractService<T> {

    protected QueryWrapper<T> getPageQueryWrapper(Page page){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if(page.getDescOderBy() != null){
            queryWrapper.orderByDesc(page.getDescOderBy().toArray(new String[0]));
        }

        if(page.getAscOderBy() != null){
            queryWrapper.orderByAsc(page.getAscOderBy().toArray(new String[0]));
        }

        queryWrapper.eq("is_deleted", CommonConstant.DEFAULT);
        queryWrapper.last("limit " + (page.getCurrentPage() - 1) * page.getPageSize() + "," + page.getPageSize());
        return queryWrapper;
    }
}

package com.jenschen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.request.Page;

public abstract class AbstractService<T> {

    protected QueryWrapper<T> getPageQueryWrapper(Page page){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(page.getDescOderBy().toArray(new String[0]));
        queryWrapper.orderByAsc(page.getAscOderBy().toArray(new String[0]));
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.last("limit " + page.getCurrentPage() * page.getSize() + "," + page.getSize());
        return queryWrapper;
    }
}

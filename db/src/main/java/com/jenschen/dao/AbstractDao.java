package com.jenschen.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.constant.CommonConstant;

public abstract class AbstractDao<T> {


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

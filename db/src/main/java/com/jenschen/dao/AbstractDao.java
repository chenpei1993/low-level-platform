package com.jenschen.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jenschen.constant.CommonConstant;
import com.jenschen.entity.PermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 因为使用了mybatis-plus其实可以将Dao和Mapper合并成一个。这样就有点像JPA的处理方式
 * 但是同样的出现多表关联的问题还是不好解决
 */
public abstract class AbstractDao<T> implements BaseDao<T>{


    /**
     * 返回默认QueryWrapper （未删除）
     * @return 返回默认没有删除的查询
     */
    protected QueryWrapper<T> getDefaultQuery(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", CommonConstant.DEFAULT);
        return queryWrapper;
    }

    @Override
    public int insert(T entity) {
        return getMapper().insert(entity);
    }

    @Override
    public int updateById(@Param(Constants.ENTITY) T entity){
        return getMapper().updateById(entity);
    }

    @Override
    public T selectById(Serializable id){
        return getMapper().selectById(id);
    }

    @Override
    public Integer selectCount(Wrapper<T> queryWrapper) {
        return getMapper().selectCount(queryWrapper);
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return getMapper().selectList(queryWrapper);
    }

    public T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper){
        return getMapper().selectOne(queryWrapper);
    }

    public abstract BaseMapper<T> getMapper();

}

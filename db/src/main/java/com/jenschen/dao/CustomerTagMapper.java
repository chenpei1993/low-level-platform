package com.jenschen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.CustomerEntity;
import com.jenschen.entity.CustomerTagEntity;
import com.jenschen.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerTagMapper extends BaseMapper<CustomerTagEntity> {
    /**
     * 根据客户ID 查找所有的结果集
     * @param customerId 客户ID
     * @return 标签结果集
     */
    List<TagEntity> getTagsByCustomerId(int customerId);
}
package com.jenschen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<CustomerEntity> {

}
package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper extends BaseMapper<CustomerEntity> {

    /**
     * 查找邮箱或者手机号相同的记录
     * @param email 邮箱
     * @param phone 手机号
     * @return
     */
    List<CustomerEntity> findByEmailOrPhone(String email, String phone);
}
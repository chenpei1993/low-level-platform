package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jenschen.entity.CustomerTagEntity;
import com.jenschen.entity.TagEntity;
import com.jenschen.helper.SpringHelper;
import com.jenschen.mapper.CustomerTagMapper;
import com.jenschen.response.TagResp;
import com.jenschen.service.CustomerTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerTagServiceImpl implements CustomerTagService {

    @Autowired
    private CustomerTagMapper customerTagMapper;

    @Override
    public void deletedByCustomerId(int customerId) {
        UpdateWrapper<CustomerTagEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("customer_id", customerId);
        CustomerTagEntity entity = CustomerTagEntity.builder().build();
        entity.deleted(LocalDateTime.now(), SpringHelper.getUserId());
        customerTagMapper.update(entity, updateWrapper);
    }

    @Override
    public void deletedByTagId(int tagId) {
        UpdateWrapper<CustomerTagEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("tag_id", tagId);
        CustomerTagEntity entity = CustomerTagEntity.builder().build();
        entity.deleted(LocalDateTime.now(), SpringHelper.getUserId());
        customerTagMapper.update(entity, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCustomerTags(int customerId, int[] tagIds) {
//        List<CustomerTagEntity> list = new ArrayList<>();
        for(int tagId : tagIds){
            CustomerTagEntity customerTagEntity = CustomerTagEntity.builder()
                                                        .customerId(customerId)
                                                        .tagId(tagId)
                                                        .build();
            customerTagEntity.created(LocalDateTime.now(), SpringHelper.getUserId());
            //TODO 批量添加
            customerTagMapper.insert(customerTagEntity);
        }
    }

    @Override
    public List<TagResp> getTagsByCustomerId(int customerId) {
        List<TagEntity> tagEntityList = customerTagMapper.getTagsByCustomerId(customerId);
        return BeanUtil.copyToList(tagEntityList, TagResp.class);
    }
}

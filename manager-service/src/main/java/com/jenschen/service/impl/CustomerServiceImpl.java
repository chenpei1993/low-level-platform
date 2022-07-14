package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.mapper.CustomerMapper;
import com.jenschen.entity.CustomerEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.request.CustomerReq;
import com.jenschen.request.Page;
import com.jenschen.response.CustomerResp;
import com.jenschen.response.PageResp;
import com.jenschen.response.TagResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.CustomerService;
import com.jenschen.service.CustomerTagService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractService<CustomerEntity> implements CustomerService {

    private static CopyOptions customerCCopyOption;
    static {
        customerCCopyOption =  CopyOptions.create().setIgnoreProperties("tags");
    }

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerTagService customerTagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> insert(CustomerReq customerReq) {
        //验证邮箱唯一和手机号唯一
        List<CustomerEntity> entities = customerMapper.findByEmailOrPhone(customerReq.getEmail(), customerReq.getPhone());
        if(CollUtil.isNotEmpty(entities)){
            return ResultUtil.error(ErrorEnum.CUSTOMER_DUPLICATE_PHONE_OR_EMAIL);
        }

        CustomerEntity customerEntity = BeanUtil.copyProperties(customerReq, CustomerEntity.class);
        customerEntity.created(LocalDateTime.now(), 1);
        customerMapper.insert(customerEntity);
        //添加用户和标签的映射
        customerTagService.insertCustomerTags(customerEntity.getId(), customerReq.getTags());
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Response<Object> update(CustomerReq customerReq) {
        List<CustomerEntity> entities = customerMapper.findByEmailOrPhone(customerReq.getEmail(), customerReq.getPhone());
        //如果存在多条，则有重复记录
        if(entities.size() > 1){
            return ResultUtil.error(ErrorEnum.CUSTOMER_DUPLICATE_PHONE_OR_EMAIL);
        }

        //只存在一条，但是id号不相等，则有重复记录
        if(entities.size() == 1 && entities.get(0).getId() != customerReq.getId()){
            return ResultUtil.error(ErrorEnum.CUSTOMER_DUPLICATE_PHONE_OR_EMAIL);
        }

        CustomerEntity customerEntity = BeanUtil.copyProperties(customerReq, CustomerEntity.class);
        customerEntity.updated(LocalDateTime.now(), 1);
        customerMapper.updateById(customerEntity);

        //删除用户和标签的映射
        customerTagService.deletedByCustomerId(customerReq.getId());

        //添加用户和标签的映射
        customerTagService.insertCustomerTags(customerEntity.getId(), customerReq.getTags());
        return ResultUtil.success();
    }

    @Override
    public Response<Object> delete(int id) {
        CustomerEntity entity = CustomerEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        customerMapper.updateById(entity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<CustomerEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<CustomerEntity> customerEntityList = customerMapper.selectList(queryWrapper);
        List<CustomerResp> respList = BeanUtil.copyToList(customerEntityList, CustomerResp.class, customerCCopyOption);
        for(CustomerResp customerResp : respList){
            List<TagResp> tagsResp = customerTagService.getTagsByCustomerId(customerResp.getId());
            customerResp.setTags(tagsResp);
        }
        int count = customerMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, respList));
    }

}

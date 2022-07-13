package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.dao.CustomerMapper;
import com.jenschen.entity.CustomerEntity;
import com.jenschen.request.CustomerReq;
import com.jenschen.request.Page;
import com.jenschen.response.CustomerResp;
import com.jenschen.response.PageResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.CustomerService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractService<CustomerEntity> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Response<Object> insert(CustomerReq customerReq) {
        CustomerEntity customerEntity = BeanUtil.copyProperties(customerReq, CustomerEntity.class);
        customerEntity.created(LocalDateTime.now(), 1);
        customerMapper.insert(customerEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> updated(CustomerReq customerReq) {
        CustomerEntity customerEntity = BeanUtil.copyProperties(customerReq, CustomerEntity.class);
        customerEntity.updated(LocalDateTime.now(), 1);
        customerMapper.updateById(customerEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<CustomerEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<CustomerEntity> customerEntityList = customerMapper.selectList(queryWrapper);
        List<CustomerResp> resp = BeanUtil.copyToList(customerEntityList, CustomerResp.class);
        int count = customerMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }
}

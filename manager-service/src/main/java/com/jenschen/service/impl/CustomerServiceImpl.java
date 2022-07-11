package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.CustomerMapper;
import com.jenschen.request.CustomReq;
import com.jenschen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper){
        this.customerMapper = customerMapper;
    }

    @Override
    public Response<Object> insert(CustomReq customDTO) {
        return null;
    }

    @Override
    public Response<Object> updated(CustomReq customReq) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

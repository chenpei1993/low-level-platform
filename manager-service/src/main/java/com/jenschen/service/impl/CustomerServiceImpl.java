package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.CustomerMapper;
import com.jenschen.dto.CustomDTO;
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
    public Response<Object> insert(CustomDTO customDTO) {
        return null;
    }

    @Override
    public Response<Object> updated(CustomDTO customDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

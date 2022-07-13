package com.jenschen.service.impl;

import com.jenschen.dao.CustomerMapper;
import com.jenschen.dao.CustomerTagMapper;
import com.jenschen.service.CustomerService;
import com.jenschen.service.CustomerTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTagServiceImpl implements CustomerTagService {

    @Autowired
    private CustomerTagMapper customerTagMapper;

    @Override
    public void deletedByTagId(int tagId) {
        //TODO
    }
}

package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dto.InfoDTO;
import com.jenschen.service.InfoService;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Response<Object> insert(InfoDTO infoDTO) {
        return null;
    }

    @Override
    public Response<Object> updated(InfoDTO infoDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

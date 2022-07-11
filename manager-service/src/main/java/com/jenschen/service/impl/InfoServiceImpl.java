package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.InfoMapper;
import com.jenschen.request.InfoDTO;
import com.jenschen.entity.InfoEntity;
import com.jenschen.service.InfoService;
import com.jenschen.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private TaskService taskService;

    @Override
    public Response<Object> insert(InfoDTO infoDTO) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoDTO, infoEntity);
        infoEntity.created(new Date(), 1);

        //生成URL

        infoMapper.insert(infoEntity);
        //如果设置定时发送
        if(infoDTO.isAutoSend()){
            taskService.insertByInfo(infoDTO);
        }
        return null;
    }

    @Override
    public Response<Object> updated(InfoDTO infoDTO) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoDTO, infoEntity);
        infoEntity.updated(new Date(), 1);
        infoMapper.updateById(infoEntity);
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setId(id);
        infoEntity.deleted(new Date(), 1);
        infoMapper.updateById(infoEntity);
        return null;
    }
}

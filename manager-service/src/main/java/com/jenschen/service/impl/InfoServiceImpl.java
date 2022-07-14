package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.jenschen.base.Response;
import com.jenschen.enumeration.InfoStatusEnum;
import com.jenschen.mapper.InfoMapper;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.request.InfoReq;
import com.jenschen.entity.InfoEntity;
import com.jenschen.service.InfoService;
import com.jenschen.service.TaskService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private TaskService taskService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> insert(InfoReq infoReq) {
        // 信息本体
        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        //限制默认参数
        infoEntity.setType(InfoTypeEnum.QUESTION);
        infoEntity.setUrl(RandomUtil.randomString(5));
        infoEntity.setStatus(InfoStatusEnum.EDIT);
        infoEntity.created(LocalDateTime.now(), 1);
        infoMapper.insert(infoEntity);

        //如果设置定时发送
        if(infoReq.isAutoSend()){
            taskService.insertSendTask(infoReq);
        }

        //如果延时器不为空
        if(CollUtil.isNotEmpty(infoReq.getDelayTipTimers())){
            taskService.insertTipTask(infoReq);
        }

        return ResultUtil.success();
    }

    @Override
    public Response<Object> updated(InfoReq infoDTO) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoDTO, infoEntity);
        infoEntity.updated(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setId(id);
        infoEntity.deleted(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);
        return null;
    }
}

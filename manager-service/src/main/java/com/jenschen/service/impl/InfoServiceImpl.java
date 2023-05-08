package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.constant.SettingsConstant;
import com.jenschen.entity.SubInfoEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.InfoStatusEnum;
import com.jenschen.mapper.InfoMapper;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.mapper.SubInfoMapper;
import com.jenschen.request.InfoReq;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.Page;
import com.jenschen.response.InfoResp;
import com.jenschen.response.PageResp;
import com.jenschen.service.*;
import com.jenschen.service.impl.taskConverter.InfoSpliter;
import com.jenschen.service.impl.taskConverter.InfoSpliterFactory;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InfoServiceImpl extends AbstractService<InfoEntity> implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private SubInfoMapper subInfoMapper;

    @Autowired
    @Qualifier("TaskServiceImpl")
    private TaskService taskService;

    @Autowired
    private InfoSpliterFactory infoSpliterFactory;

    @Autowired
    @Lazy
    private QuestionService questionService;

    @Autowired
    private SettingService settingService;

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<InfoEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<InfoEntity> infoEntityList = infoMapper.selectList(queryWrapper);

        String ip = settingService.getValue(SettingsConstant.QUESTION_FRONT_URL);

        List<InfoResp> list = new ArrayList<>(infoEntityList.size());
        for(var info : infoEntityList){
            InfoResp resp = BeanUtil.copyProperties(info, InfoResp.class);
            resp.setUrl(ip + "/" + info.getUrl());
            list.add(resp);
        }

        int count = infoMapper.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, list));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> insert(InfoReq infoReq) {
        InfoSpliter infoSpliter = infoSpliterFactory.getTaskConverter(infoReq.getRepeatCollectType());

        //TODO 验证数据

        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        //限制默认参数
        infoEntity.setType(InfoTypeEnum.QUESTION);
        infoEntity.setUrl(RandomUtil.randomString(16));
        infoEntity.setStatus(InfoStatusEnum.EDIT);
        infoEntity.created(LocalDateTime.now(), 1);
        infoMapper.insert(infoEntity);

        //分割的活动信息
        List<InfoEntity> infoEntityList = infoSpliter.convert(infoReq, infoEntity.getId());
        List<SubInfoEntity> subInfoEntityList = BeanUtil.copyToList(infoEntityList, SubInfoEntity.class);
        for(SubInfoEntity subInfoEntity : subInfoEntityList){
            subInfoEntity.created(LocalDateTime.now(), 1);
            subInfoMapper.insert(subInfoEntity);
        }

        //如果设置定时发送
        List<TaskEntity> sendList;
        if(infoReq.isAutoSend()){
            sendList = taskService.insertSendTask(infoReq, infoEntityList);
            taskService.saveList(sendList);
        }

        //如果延时器不为空
        List<TaskEntity> delayList;
        if(CollUtil.isNotEmpty(infoReq.getDelayTipTimers())){
            delayList = taskService.insertTipTask(infoReq, infoEntityList);
            taskService.saveList(delayList);
        }

        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(InfoReq infoDTO) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoDTO, infoEntity);
        infoEntity.updated(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> publish(InfoReq infoReq) {
        this.setStatus(InfoStatusEnum.PUBLISH, infoReq.getId());
        return ResultUtil.success();
    }

    @Override
    public Response<Object> stop(InfoReq infoReq) {
        this.setStatus(InfoStatusEnum.EDIT, infoReq.getId());
        return ResultUtil.success();
    }

    private void setStatus(InfoStatusEnum status, int id){
        InfoEntity info = infoMapper.selectById(id);
        info.setStatus(status);
        infoMapper.updateById(info);
    }

    @Override
    public Response<Object> delete(int id) {
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setId(id);
        infoEntity.deleted(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);

        //删除 Question 列表
        questionService.deleteByInfoId(id);
        return ResultUtil.success();
    }

    @Override
    public InfoEntity get(int id) {
        return  infoMapper.selectById(id);
    }
}

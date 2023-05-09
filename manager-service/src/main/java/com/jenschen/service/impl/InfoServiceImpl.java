package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.constant.SettingsConstant;
import com.jenschen.dao.QuestionDao;
import com.jenschen.elastic.dao.AnswerDao;
import com.jenschen.elastic.entity.AnswerEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.entity.SubInfoEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.InfoStatusEnum;
import com.jenschen.mapper.InfoMapper;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.mapper.SubInfoMapper;
import com.jenschen.request.AnswerPageReq;
import com.jenschen.request.InfoReq;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.Page;
import com.jenschen.response.*;
import com.jenschen.service.*;
import com.jenschen.service.impl.taskConverter.InfoSpliter;
import com.jenschen.service.impl.taskConverter.InfoSpliterFactory;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Qualifier("TipTaskService")
    private TaskService tipTaskService;

    @Autowired
    @Qualifier("SendTaskService")
    private TaskService sendTaskService;

    @Autowired
    private InfoSpliterFactory infoSpliterFactory;

    @Autowired
    @Lazy
    private QuestionService questionService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private QuestionDao questionDao;

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

            List<TaskEntity> tips = tipTaskService.getByInfoId(info.getId());
            List<TaskResp> tipsResp = BeanUtil.copyToList(tips, TaskResp.class);
            resp.setTipTasks(tipsResp);

            List<TaskEntity> sends = sendTaskService.getByInfoId(info.getId());
            List<TaskResp> sendRep = BeanUtil.copyToList(sends, TaskResp.class);
            resp.setSendTasks(sendRep);
        }

        int count = infoMapper.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, list));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> insert(InfoReq infoReq) {

        //TODO 验证数据

        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        //限制默认参数
        infoEntity.setType(InfoTypeEnum.QUESTION);
        infoEntity.setUrl(RandomUtil.randomString(16));
        infoEntity.setStatus(InfoStatusEnum.EDIT);
        infoEntity.created(LocalDateTime.now(), 1);
        infoMapper.insert(infoEntity);


        updateTask(infoReq, infoEntity);

        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(InfoReq infoReq) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoReq, infoEntity);
        infoEntity.updated(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);

        //删除任务表
        tipTaskService.deleteByInfoId(infoReq.getId());
        sendTaskService.deleteByInfoId(infoReq.getId());

        //重新构建任务
        updateTask(infoReq, infoEntity);

        return ResultUtil.success();
    }

    private void updateTask(InfoReq infoReq, InfoEntity infoEntity){
        InfoSpliter infoSpliter = infoSpliterFactory.getTaskConverter(infoReq.getRepeatCollectType());

        //分割的活动信息
        List<InfoEntity> infoEntityList = infoSpliter.convert(infoReq, infoEntity.getId());
        List<SubInfoEntity> subInfoEntityList = BeanUtil.copyToList(infoEntityList, SubInfoEntity.class);
        for(SubInfoEntity subInfoEntity : subInfoEntityList){
            subInfoEntity.created(LocalDateTime.now(), 1);
//            subInfoMapper.insert(subInfoEntity);
        }

        //如果提醒器不为空
        if(CollUtil.isNotEmpty(infoReq.getDelayTipTimers())){
            List<TaskEntity> delayList;
            if(CollUtil.isNotEmpty(infoReq.getDelayTipTimers())){
                delayList = tipTaskService.insertTipTask(infoReq, infoEntityList);
                tipTaskService.saveList(delayList);
            }

        }

        //如果设置定时发送
        if(infoReq.isAutoSend()){
            List<TaskEntity> sendList = tipTaskService.insertSendTask(infoReq, infoEntityList);
            tipTaskService.saveList(sendList);
        }
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

    @Override
    public Response<Object> getAnswers(AnswerPageReq page) {
        List<QuestionEntity> questionEntityList = questionDao.getByInfoId(page.getInfoId());

        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<AnswerEntity> ans = answerDao.findByInfoId(page.getInfoId(), pageable);
        int count = (int) ans.getTotalElements();
        List<AnswerResp> list = new ArrayList();
        ans.forEach((e) -> {
            AnswerResp resp = AnswerResp.builder()
                    .answer(e.getAnswer())
                    .build();
            resp.setCreatedAt(e.getCreatedAt());
            resp.setUpdatedAt(e.getUpdatedAt());
            list.add(resp);
        });
        return ResultUtil.success(AnswerPageResp.build(questionEntityList, count, list));
    }
}

package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
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
import com.jenschen.service.AbstractService;
import com.jenschen.service.InfoService;
import com.jenschen.service.TaskService;
import com.jenschen.service.impl.taskConverter.InfoSpliter;
import com.jenschen.service.impl.taskConverter.InfoSpliterFactory;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<InfoEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<InfoEntity> infoEntityList = infoMapper.selectList(queryWrapper);
        List<InfoResp> resp = BeanUtil.copyToList(infoEntityList, InfoResp.class);
        int count = infoMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> insert(InfoReq infoReq) {
        InfoSpliter infoSpliter = infoSpliterFactory.getTaskConverter(infoReq.getRepeatCollectType());

        //TODO 验证数据

        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        //限制默认参数
        infoEntity.setType(InfoTypeEnum.QUESTION);
        infoEntity.setUrl(RandomUtil.randomString(5));
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
    public Response<Object> updated(InfoReq infoDTO) {
        InfoEntity infoEntity = new InfoEntity();
        BeanUtils.copyProperties(infoDTO, infoEntity);
        infoEntity.updated(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> deleted(int id) {
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setId(id);
        infoEntity.deleted(LocalDateTime.now(), 1);
        infoMapper.updateById(infoEntity);
        return ResultUtil.success();
    }

    @Override
    public InfoEntity get(int id) {
        return  infoMapper.selectById(id);
    }
}

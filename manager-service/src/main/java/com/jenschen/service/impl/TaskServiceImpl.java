package com.jenschen.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.enumeration.TaskStatusEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.enumeration.TimeUnitEnum;
import com.jenschen.exception.BizException;
import com.jenschen.mapper.TaskMapper;
import com.jenschen.request.InfoReq;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.request.TaskReq;
import com.jenschen.entity.TaskEntity;
import com.jenschen.request.TipTimerPageReq;
import com.jenschen.service.AbstractService;
import com.jenschen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("TaskServiceImpl")
public class TaskServiceImpl extends AbstractService<TaskEntity> implements TaskService {

    @Autowired
    protected TaskMapper taskMapper;

    @Override
    public List<TaskEntity> insertSendTask(InfoReq infoReq, List<InfoEntity> infoEntityList) {
        List<TaskEntity> list = new ArrayList<>();
        LocalDateTime sendDateTime = infoReq.getSendDateTime();
        if(RepeatCollectTypeEnum.ONCE.equals(infoReq.getRepeatCollectType())){
            InfoEntity infoEntity = infoEntityList.get(0);
            TaskEntity taskEntity = TaskEntity.builder()
                        .infoId(infoEntity.getId())
                        .type(TaskTypeEnum.SEND)
                        .executionDateTime(sendDateTime)
                        .sendType(infoReq.getSendType())
                        .sendMessage(infoReq.getSendMessage())
                        .status(TaskStatusEnum.INIT)
                        .build();
            taskEntity.created(LocalDateTime.now(), 1);
            list.add(taskEntity);
            return list;
        }

        for(InfoEntity infoEntity : infoEntityList){
            LocalDateTime dateTime = LocalDateTimeUtil.of(infoEntity.getStartDateTime());
            dateTime = dateTime.withHour(sendDateTime.getHour()).withMinute(sendDateTime.getMinute());
            TaskEntity taskEntity = TaskEntity.builder()
                    .infoId(infoEntity.getId())
                    .type(TaskTypeEnum.SEND)
                    .executionDateTime(dateTime)
                    .sendType(infoReq.getSendType())
                    .sendMessage(infoReq.getSendMessage())
                    .status(TaskStatusEnum.INIT)
                    .build();
            taskEntity.created(LocalDateTime.now(), 1);
            list.add(taskEntity);
        }
        return list;
    }

    @Override
    public List<TaskEntity> insertTipTask(InfoReq infoReq, List<InfoEntity> infoEntityList) {
        List<TaskReq> taskReqList = infoReq.getDelayTipTimers();
        List<TaskEntity> delayList = new ArrayList<>();

        for(InfoEntity infoEntity : infoEntityList){
            for(TaskReq taskReq : taskReqList){
                LocalDateTime dateTime = LocalDateTimeUtil.of(infoEntity.getEndDateTime());
                dateTime = dateTime.minusMinutes(beforeMinutes(taskReq.getTimeUnit(), taskReq.getValue()));
                TaskEntity taskEntity = TaskEntity.builder()
                        .infoId(infoEntity.getId())
                        .type(TaskTypeEnum.TIP)
                        .executionDateTime(dateTime)
                        .sendType(infoReq.getSendType())
                        .sendMessage(infoReq.getSendMessage())
                        .status(TaskStatusEnum.INIT)
                        .build();
                delayList.add(taskEntity);
            }
        }
        return delayList;
    }

    @Override
    public void saveList(List<TaskEntity> taskEntityList) {
        for(TaskEntity taskEntity : taskEntityList){
            taskMapper.insert(taskEntity);
        }
    }

    private int beforeMinutes(TimeUnitEnum timeUnit, int value){
        if(TimeUnitEnum.MINUTES.equals(timeUnit)){
            return value;
        }else if(TimeUnitEnum.HOURS.equals(timeUnit)){
            return value * 60;
        }else if(TimeUnitEnum.DAYS.equals(timeUnit)){
            return value * 60 * 24;
        }

        return value;
    }

    @Override
    public Response<Object> updated(TaskReq taskDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }

    @Override
    public Response<Object> pageSendTimer(SendTimerPageReq sendTimerPageReq) {
        throw new BizException("未定义行为");
    }

    @Override
    public Response<Object> pageTipTimer(TipTimerPageReq tipTimerPageReq) {
        throw new BizException("未定义行为");
    }
}

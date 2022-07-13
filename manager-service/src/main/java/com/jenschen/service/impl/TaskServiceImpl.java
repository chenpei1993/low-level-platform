package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.TaskMapper;
import com.jenschen.request.InfoReq;
import com.jenschen.request.TaskReq;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.service.TaskService;
import com.jenschen.service.impl.taskConverter.TaskConverter;
import com.jenschen.service.impl.taskConverter.TaskConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskConverterFactory taskConverterFactory;

    @Override
    public void insertSendTask(InfoReq infoDTO) {

        List<TaskEntity> mergeList = new ArrayList<>();

        TaskConverter taskConverter = taskConverterFactory.getTaskConverter(infoDTO.getRepeatCollectType());

        //处理定时推送的
        List<TaskEntity> sendList = taskConverter.convert(TaskTypeEnum.SEND,
                                        infoDTO.getStartDateTime(),
                                        infoDTO.getEndDateTime(),
                                        infoDTO.getSendDateTime(), infoDTO.getSendType(), null);

        mergeList.addAll(sendList);

        //TODO 转化，添加到数据库中
    }

    @Override
    public void insertTipTask(InfoReq infoDTO) {

        TaskConverter taskConverter = taskConverterFactory.getTaskConverter(infoDTO.getRepeatCollectType());
        List<TaskEntity> mergeList = new ArrayList<>();

        List<TaskReq> taskDTOList = infoDTO.getDelayTipTimers();
        //处理延时推送
        for(TaskReq taskDTO : taskDTOList){
            List<TaskEntity> delayList = taskConverter.convert(TaskTypeEnum.SEND,
                    infoDTO.getStartDateTime(),
                    infoDTO.getEndDateTime(),
                    infoDTO.getSendDateTime(), infoDTO.getSendType(), null);
            mergeList.addAll(delayList);
        }

        //TODO 转化，添加到数据库中
    }

    @Override
    public Response<Object> updated(TaskReq taskDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

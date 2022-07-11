package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.TaskMapper;
import com.jenschen.request.InfoDTO;
import com.jenschen.request.TaskDTO;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.service.TaskService;
import com.jenschen.service.impl.taskConverter.TaskConverter;
import com.jenschen.service.impl.taskConverter.TaskConverterFactory;
import lombok.Data;
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
    public void insertByInfo(InfoDTO infoDTO) {

        List<TaskEntity> mergeList = new ArrayList<>();

        List<TaskDTO> taskDTOList = infoDTO.getDelayTipTimers();
        TaskConverter taskConverter = taskConverterFactory.getTaskConverter(infoDTO.getRepeatCollectType());


        //处理定时推送的
        List<TaskEntity> sendList = taskConverter.convert(TaskTypeEnum.SEND,
                                        infoDTO.getStartDateTime(),
                                        infoDTO.getEndDateTime(),
                                        infoDTO.getSendDate(), infoDTO.getSendType(), null);

        mergeList.addAll(sendList);

        //处理延时推送
        for(TaskDTO taskDTO : taskDTOList){
            List<TaskEntity> delayList = taskConverter.convert(TaskTypeEnum.SEND,
                                        infoDTO.getStartDateTime(),
                                        infoDTO.getEndDateTime(),
                                        infoDTO.getSendDate(), infoDTO.getSendType(), null);
            mergeList.addAll(delayList);
        }

        //TODO 转化，添加到数据库中
    }

    @Override
    public Response<Object> updated(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

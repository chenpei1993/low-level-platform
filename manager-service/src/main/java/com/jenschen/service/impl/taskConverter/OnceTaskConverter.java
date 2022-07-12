package com.jenschen.service.impl.taskConverter;

import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OnceTaskConverter implements TaskConverter{
    @Override
    public List<TaskEntity> convert(TaskTypeEnum taskTypeEnum, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime dateTime, SendTypeEnum sendType, String message) {
        List<TaskEntity> tasks = new ArrayList<>();
        TaskEntity t = new TaskEntity();
        t.setExecutionDatetime(dateTime);
        t.setSendType(sendType);
        tasks.add(t);
        return tasks;
    }
}

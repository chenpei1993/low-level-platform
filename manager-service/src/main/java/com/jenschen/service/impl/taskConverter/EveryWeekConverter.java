package com.jenschen.service.impl.taskConverter;

import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EveryWeekConverter implements TaskConverter{
    @Override
    public List<TaskEntity> convert(TaskTypeEnum taskTypeEnum, LocalDateTime startDateTime,
                                    LocalDateTime endDateTime, LocalDateTime sendDateTime,
                                    SendTypeEnum sendType, String message) {

        int hour = sendDateTime.getHour();
        int minute = sendDateTime.getMinute();

        LocalDateTime start = startDateTime.withHour(hour).withMinute(minute).withSecond(0);

        List<TaskEntity> tasks = new ArrayList<>();

        while(start.isBefore(endDateTime)){
            start = start.plusDays(1);
            tasks.add(build(start, taskTypeEnum, sendType));
        }

        return tasks;
    }

    private TaskEntity build(LocalDateTime date, TaskTypeEnum taskTypeEnum, SendTypeEnum sendType){
        TaskEntity t = new TaskEntity();
        t.setExecutionDatetime(date);
        t.setType(taskTypeEnum);
        t.setSendType(sendType);
        return t;
    }
}

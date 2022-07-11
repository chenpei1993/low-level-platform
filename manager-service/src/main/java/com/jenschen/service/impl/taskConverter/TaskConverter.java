package com.jenschen.service.impl.taskConverter;

import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskTypeEnum;

import java.util.Date;
import java.util.List;

public interface TaskConverter {
    List<TaskEntity> convert(TaskTypeEnum taskTypeEnum,
                             Date startDateTime,
                             Date endDateTime,
                             Date dateTime,
                             SendTypeEnum sendType,
                             String message);
}

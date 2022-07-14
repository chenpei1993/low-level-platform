package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.request.InfoReq;
import com.jenschen.request.TaskReq;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface TaskService {


    List<TaskEntity> insertSendTask(InfoReq infoReq, List<InfoEntity> infoEntityList);


    List<TaskEntity> insertTipTask(InfoReq infoReq, List<InfoEntity> infoEntityList);

    /**
     * 更新任务
     * @param taskReq 任务
     * @return 结果
     */
    Response<Object> updated(TaskReq taskReq);

    /**
     * 逻辑删除任务
     * @param id 任务id
     * @return 结果
     */
    Response<Object> deleted(int id);
}

package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.request.InfoReq;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.request.TaskReq;
import com.jenschen.request.TipTimerPageReq;

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

    /**
     * 定时器任务详情 分页
     * @param sendTimerPageReq 分页数据
     * @return 结果
     */
    Response<Object> pageSendTimer(SendTimerPageReq sendTimerPageReq);

    /**
     * 定时器任务详情 分页
     * @param tipTimerPageReq 分页数据
     * @return 结果
     */
    Response<Object> pageTipTimer(TipTimerPageReq tipTimerPageReq);
}

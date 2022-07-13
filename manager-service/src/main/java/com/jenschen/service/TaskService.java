package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.InfoReq;
import com.jenschen.request.TaskReq;

public interface TaskService {

    /**
     * 插入定时发送任务
     * @param infoDTO 信息
     */
    void insertSendTask(InfoReq infoDTO);

    /**
     * 插入延时提醒任务
     * @param infoDTO 信息
     */
    void insertTipTask(InfoReq infoDTO);

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

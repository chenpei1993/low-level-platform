package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.request.TipTimerPageReq;
import com.jenschen.request.info.InfoReq;
import com.jenschen.request.tag.TaskReq;

import java.util.List;

public interface TaskService {

    /**
     * 插入推送定时器
     * @param infoReq info请求
     * @param infoEntityList info信息列表
     * @return 分割的推送任务
     */
    List<TaskEntity> insertSendTask(InfoReq infoReq, List<InfoEntity> infoEntityList);

    /**
     * 插入提醒定时器
     * @param infoReq info请求
     * @param infoEntityList info信息列表
     * @return 分割的提醒任务
     */
    List<TaskEntity> insertTipTask(InfoReq infoReq, List<InfoEntity> infoEntityList);

    /**
     * 批量保存列表
     * @param taskEntityList 任务列表
     */
    void saveList(List<TaskEntity> taskEntityList);

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

    List<TaskEntity> getByInfoId(Integer infoId);

    void deleteByInfoId(Integer infoId);

}

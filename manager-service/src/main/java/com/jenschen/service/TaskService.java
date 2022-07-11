package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.InfoDTO;
import com.jenschen.request.TaskDTO;

public interface TaskService {

    void insertByInfo(InfoDTO infoDTO);

    /**
     * 更新任务
     * @param taskDTO 任务
     * @return 结果
     */
    Response<Object> updated(TaskDTO taskDTO);

    /**
     * 逻辑删除任务
     * @param id 任务id
     * @return 结果
     */
    Response<Object> deleted(int id);
}

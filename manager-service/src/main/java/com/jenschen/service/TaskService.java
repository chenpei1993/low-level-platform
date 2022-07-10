package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.dto.CustomDTO;
import com.jenschen.dto.TaskDTO;

public interface TaskService {
    /**
     * 插入任务
     * @param taskDTO 任务
     * @return 结果
     */
    Response<Object> insert(TaskDTO taskDTO);

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

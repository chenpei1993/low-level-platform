package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dto.TaskDTO;
import com.jenschen.service.TaskService;
import lombok.Data;

@Data
public class TaskServiceImpl implements TaskService {
    @Override
    public Response<Object> insert(TaskDTO taskDTO) {
        return null;
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

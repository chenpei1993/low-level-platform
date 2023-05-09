package com.jenschen.messagehandler;

import com.jenschen.entity.TaskEntity;

public interface MessageHandler {

    public void send(TaskEntity taskEntity);
}

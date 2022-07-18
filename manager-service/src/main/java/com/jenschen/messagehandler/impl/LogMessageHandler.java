package com.jenschen.messagehandler.impl;

import com.jenschen.entity.TaskEntity;
import com.jenschen.messagehandler.MessageHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogMessageHandler implements MessageHandler {
    @Override
    public void send(TaskEntity taskEntity) {
        log.info(taskEntity.toString());
    }
}

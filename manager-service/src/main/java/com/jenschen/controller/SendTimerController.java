package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.service.TaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("send_timer")
public class SendTimerController {

    private final TaskService taskService;

    public SendTimerController(@Qualifier("SendTaskService") TaskService taskService){
        this.taskService = taskService;
    }

    /**
     * 分页
     * @param sendTimerPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody SendTimerPageReq sendTimerPageReq){
        return taskService.pageSendTimer(sendTimerPageReq);
    }
}

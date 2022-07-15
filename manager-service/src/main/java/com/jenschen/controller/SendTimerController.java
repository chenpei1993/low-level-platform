package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.service.TaskService;
import com.jenschen.service.impl.task.SendTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("send_timer")
public class SendTimerController {

    @Autowired
    @Qualifier("SendTaskServiceImpl")
    private TaskService taskService;

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

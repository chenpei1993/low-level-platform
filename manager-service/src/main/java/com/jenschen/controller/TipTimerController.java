package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.TipTimerPageReq;
import com.jenschen.service.TaskService;
import com.jenschen.service.impl.task.TipTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tip_timer")
public class TipTimerController {

    @Autowired
    @Qualifier("TipTaskServiceImpl")
    private TaskService taskService;

    /**
     * 分页
     * @param tipTimerPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody TipTimerPageReq tipTimerPageReq){
        return taskService.pageTipTimer(tipTimerPageReq);
    }
}

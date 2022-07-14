package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.InfoPageReq;
import com.jenschen.request.InfoReq;
import com.jenschen.request.TagPageReq;
import com.jenschen.service.InfoService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 */
@RestController
@RequestMapping("info")
@CrossOrigin("*")
public class InfoController {

    @Autowired
    private InfoService infoService;

    /**
     * 添加消息
     * @param infoReq 消息
     * @return 结果
     */
    @PutMapping
    public Response<Object> add(@RequestBody InfoReq infoReq){
        return infoService.insert(infoReq);
    }

    /**
     * 删除消息
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return ResultUtil.success();
    }

    /**
     * 分页
     * @param infoPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody InfoPageReq infoPageReq){
        return infoService.page(infoPageReq);
    }
}

package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.AnswerPageReq;
import com.jenschen.request.info.InfoPageReq;
import com.jenschen.request.info.InfoReq;
import com.jenschen.service.InfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 */
@RestController
@RequestMapping("info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    /**
     * 添加活动
     *
     * @param infoReq 活动在
     * @return 结果
     */
    @PutMapping
    @PreAuthorize("hasAuthority('activity:info:add')")
    public Response<Object> add(@RequestBody @Validated InfoReq infoReq) {
        return infoService.insert(infoReq);
    }

    /**
     * 编辑活动
     *
     * @param infoReq 活动
     * @return 结果
     */
    @PostMapping
    @PreAuthorize("hasAuthority('activity:info:edit')")
    public Response<Object> edit(@RequestBody @Validated InfoReq infoReq) {
        return infoService.edit(infoReq);
    }

    /**
     * 发布活动
     * @return 结果
     */
    @PostMapping("publish")
    @PreAuthorize("hasAuthority('activity:info:publish')")
    public Response<Object> publish(@RequestBody InfoReq infoReq){
        return infoService.publish(infoReq);
    }

    /**
     * 停用活动
     * @return 结果
     */
    @PostMapping("stop")
    @PreAuthorize("hasAuthority('activity:info:stop')")
    public Response<Object> stop(@RequestBody InfoReq infoReq){
        return infoService.stop(infoReq);
    }

    /**
     * 删除消息
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('activity:info:del')")
    public Response<Object> del(@PathVariable Integer id){
        return infoService.delete(id);
    }

    /**
     * 分页
     * @param infoPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    @PreAuthorize("hasAuthority('activity:info:query')")
    public Response<Object> page(@RequestBody InfoPageReq infoPageReq){
        return infoService.page(infoPageReq);
    }

    /**
     * 结果分页查询
     * @param anwserPage 结果分页查询
     * @return  结果
     */
    @PostMapping(value = "answer/page")
    @ResponseBody
    public Response<Object> answerPage(@RequestBody AnswerPageReq anwserPage){
        return infoService.getAnswers(anwserPage);
    }
}

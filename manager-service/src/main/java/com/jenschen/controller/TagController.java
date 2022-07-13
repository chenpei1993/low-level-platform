package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.TagReq;
import com.jenschen.request.TagPageReq;
import com.jenschen.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 标记控制
 */

@RestController
@RequestMapping("tag")
@CrossOrigin("*")
public class TagController {

    @Autowired
    private TagService tagService;

    @PutMapping
    public Response<Object> add(@RequestBody @Validated TagReq tagReq){
        return tagService.insert(tagReq);
    }

    @PostMapping
    public Response<Object> edit(@RequestBody @Validated TagReq tagReq){
        return tagService.updated(tagReq);
    }

    /**
     * 删除标签
     * @param tagReq 删除请求实例
     * @return 结果
     */
    @DeleteMapping
    public Response<Object> del(@RequestBody TagReq tagReq){
        return tagService.deleted(tagReq);
    }

    /**
     * 获取标签
     * @param id 标签
     * @return 结果
     */
    @GetMapping("/{id}")
    public Response<Object> get(@PathVariable int id){
        return tagService.get(id);
    }

    /**
     * 分页
     * @param tagPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody TagPageReq tagPageReq){
        return tagService.page(tagPageReq);
    }


}

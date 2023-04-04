package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.TagReq;
import com.jenschen.request.TagPageReq;
import com.jenschen.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('tag:add')")
    public Response<Object> add(@RequestBody @Validated TagReq tagReq){
        return tagService.insert(tagReq);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('tag:edit')")
    public Response<Object> edit(@RequestBody @Validated TagReq tagReq){
        return tagService.update(tagReq);
    }

    /**
     * 删除标签
     * @param id 标签ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('tag:del')")
    public Response<Object> del(@PathVariable int id){
        return tagService.delete(id);
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
     * 获取所有标签
     * @return 结果
     */
    @GetMapping("/all")
    public Response<Object> all(){
        return tagService.all();
    }

    /**
     * 分页
     * @param tagPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @PreAuthorize("hasAuthority('tag:query')")
    @ResponseBody
    public Response<Object> page(@RequestBody TagPageReq tagPageReq){
        return tagService.page(tagPageReq);
    }


}

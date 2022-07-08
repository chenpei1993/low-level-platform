package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.dto.TagDTO;
import com.jenschen.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * 标记控制
 */

@RestController
@RequestMapping("tag")
@CrossOrigin("*")
public class TagController {

    @PutMapping
    public Response<Object> add(@RequestBody TagDTO tagDTO){
        return ResultUtil.success();
    }

    /**
     * 删除标签
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return ResultUtil.success();
    }

    /**
     * 添加标签
     * @param tag 标签
     * @return 结果
     */
    @GetMapping("/{tag}")
    public Response<Object> get(@PathVariable String tag){
        return ResultUtil.success();
    }


}

package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * 问卷控制器
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @GetMapping
    public Response<Object> get(){
        return ResultUtil.success();
    }

    @PostMapping
    public Response<Object> saveInfo(){
        return ResultUtil.success();
    }

    /**
     * 删除问卷
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> delInfo(@PathVariable Integer id){
        return ResultUtil.success();
    }
}

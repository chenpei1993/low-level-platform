package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.QuestionReq;
import com.jenschen.service.QuestionService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 问卷控制器
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getByInfoId/{id}")
    public Response<Object> get(@PathVariable Integer id){
        return questionService.getByInfoId(id);
    }

    @PutMapping
    public Response<Object> add(@RequestBody @Validated QuestionReq questionReq){
        return questionService.add(questionReq);
    }

    /**
     * 根据question id 删除问卷
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return ResultUtil.success();
    }

    /**
     * info id 删除问卷
     * @param infoId info id
     * @return 结果
     */
    @DeleteMapping("/getInfoId")
    public Response<Object> delByInfoId(@PathVariable Integer infoId){
        return ResultUtil.success();
    }
}

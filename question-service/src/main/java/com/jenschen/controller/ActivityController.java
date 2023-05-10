package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.AnswerRequest;
import com.jenschen.response.CheckQuestionResponse;
import com.jenschen.response.InfoResponse;
import com.jenschen.response.QuestionResponse;
import com.jenschen.service.InfoService;
import com.jenschen.util.ResultUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("activity")
@CrossOrigin("*")
public class ActivityController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/info/{code}")
    public Response<Object> getQuestionsInfo(@PathVariable String code){
        return infoService.getInfo(code);
    }

    @PostMapping("submit")
    private Response<Object> submit(@RequestBody AnswerRequest answerRequest){
        return infoService.submit(answerRequest);
    }
}

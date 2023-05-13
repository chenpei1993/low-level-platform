package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.AnswerRequest;
import com.jenschen.service.InfoService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("activity")
@CrossOrigin("*")
public class ActivityController {

    private final InfoService infoService;

    public ActivityController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping("/info/{code}")
    public Response<Object> getQuestionsInfo(@PathVariable String code){
        return infoService.getInfo(code);
    }

    @PostMapping("submit")
    private Response<Object> submit(@RequestBody AnswerRequest answerRequest){
        return infoService.submit(answerRequest);
    }
}

package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.AnswerRequest;
import com.jenschen.response.CheckQuestionResponse;
import com.jenschen.response.InfoResponse;
import com.jenschen.response.QuestionResponse;
import com.jenschen.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin("*")
public class QuestionController {

    @GetMapping("/info/{id}")
    public InfoResponse getQuestionsInfo(@PathVariable String id){

        InfoResponse info = new InfoResponse();
        info.setTitle("测试");
        info.setStyleUrl("./css/default.css");
        info.setPageTitle("测试123");
        List<QuestionResponse> questionList = new ArrayList<>();
        QuestionResponse a = new QuestionResponse();
        a.setId(10001L);
        a.setIdx(1);
        a.setType(1);
        a.setRequire(false);
        a.setQuestionDesc("这是一个测试问答题");
        questionList.add(a);

        CheckQuestionResponse b = new CheckQuestionResponse();
        b.setId(10002L);
        b.setIdx(2);
        b.setType(2);
        b.setRequire(true);
        b.setQuestionDesc("这是一个测试单选择题");
        b.setQuestionOptions(Arrays.asList("答案1","答案2","答案3"));
        questionList.add(b);

        CheckQuestionResponse c = new CheckQuestionResponse();
        c.setId(10003L);
        c.setIdx(3);
        c.setType(3);
        c.setRequire(true);
        c.setQuestionDesc("这是一个测试单选择题");
        c.setQuestionOptions(Arrays.asList("答案1","答案2","答案3","答案4","答案5"));
        questionList.add(c);

        info.setQuestions(questionList);
        return info;
    }

    @PostMapping("submit")
    private Response<Object> submit(AnswerRequest answer){
        return ResultUtil.success();
    }
}

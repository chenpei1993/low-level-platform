package com.jenschen.response;

import com.jenschen.entity.QuestionEntity;
import lombok.Data;

import java.util.List;

@Data
public class AnswerPageResp<T> extends PageResp{
    private List<QuestionEntity> questionsInfo;

    public static <T> PageResp<T> build(List<QuestionEntity> questionsInfo, int total, List<T> data){
        AnswerPageResp<T> pageResp = new AnswerPageResp<>();
        pageResp.setQuestionsInfo(questionsInfo);
        pageResp.setData(data);
        pageResp.setTotal(total);
        return pageResp;
    }
}

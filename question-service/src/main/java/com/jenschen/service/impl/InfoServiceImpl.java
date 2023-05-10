package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jenschen.base.Response;
import com.jenschen.dao.InfoDao;
import com.jenschen.dao.QuestionDao;
import com.jenschen.elastic.dao.AnswerDao;
import com.jenschen.elastic.entity.AnswerEntity;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.request.Answer;
import com.jenschen.request.AnswerRequest;
import com.jenschen.response.InfoResponse;
import com.jenschen.response.QuestionResponse;
import com.jenschen.service.InfoService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private InfoDao infoDao;

    @Autowired
    private AnswerDao answerDao;


    public Response<Object> getInfo(String code){
        //判断活动状态并提示
        InfoEntity infoEntity = infoDao.getByCode(code);
        if(infoEntity == null){
            return ResultUtil.error(ErrorEnum.ACTIVITY_NOT_EXIST);
        }

        if(infoEntity.getStartDateTime().isAfter(LocalDateTime.now())){
            return ResultUtil.error(ErrorEnum.ACTIVITY_NOT_BEGIN);
        }

        if(infoEntity.getEndDateTime().isBefore(LocalDateTime.now())){
            return ResultUtil.error(ErrorEnum.ACTIVITY_END);
        }

        List<QuestionEntity>  questionEntities = questionDao.getByCode(code);
        List<QuestionResponse> questionList = BeanUtil.copyToList(questionEntities, QuestionResponse.class);

        InfoResponse info = BeanUtil.copyProperties(infoEntity, InfoResponse.class);
        info.setQuestions(questionList);

        return ResultUtil.success(info);
    }

    @Override
    public Response<Object> submit(AnswerRequest answerRequest) {
        List<Answer> list = answerRequest.getAnswers();
        Integer infoId = answerRequest.getInfoId();

        List<QuestionEntity> questionEntities = questionDao.getByInfoId(infoId);
        Map<Integer, QuestionEntity> map = new HashMap<>();
        for(var entity : questionEntities){
            map.put(entity.getId(), entity);
        }
        for(var answer : list){
            QuestionEntity question = map.get(answer.getQuestionId());
            if(question == null){
                return ResultUtil.error(ErrorEnum.QUESTION_REQUIRED);
            }

            if(question.isRequired() && StrUtil.isBlank(answer.getAnswer())){
                return ResultUtil.error(ErrorEnum.QUESTION_REQUIRED);
            }
        }

        String answer = JSONUtil.toJsonPrettyStr(list);
        LocalDateTime now = LocalDateTime.now();
        AnswerEntity data =  AnswerEntity.builder()
                .infoId(infoId)
                .answer(answer)
                .createdAt(now)
                .updatedAt(now)
                .build();
        answerDao.save(data);

        return ResultUtil.success();
    }

}

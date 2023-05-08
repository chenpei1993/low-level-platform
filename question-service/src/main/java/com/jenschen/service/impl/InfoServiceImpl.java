package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jenschen.base.Response;
import com.jenschen.dao.InfoDao;
import com.jenschen.dao.QuestionDao;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.response.InfoResponse;
import com.jenschen.response.QuestionResponse;
import com.jenschen.service.InfoService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private InfoDao infoDao;


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

}

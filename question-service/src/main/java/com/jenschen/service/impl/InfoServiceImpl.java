package com.jenschen.service.impl;

import com.jenschen.dao.QuestionDao;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.mapper.InfoMapper;
import com.jenschen.response.InfoResponse;
import com.jenschen.service.InfoService;
import com.jenschen.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private QuestionDao questionDao;

    public InfoResponse getInfo(Integer id){
        InfoEntity info = infoMapper.selectById(id);

        List<QuestionEntity>  questionEntities = questionDao.getByInfoId(id);
        return null;
    }

}

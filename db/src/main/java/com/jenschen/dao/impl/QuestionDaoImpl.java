package com.jenschen.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.QuestionDao;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.mapper.QuestionMapper;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDaoImpl extends AbstractDao<QuestionEntity> implements QuestionDao {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionEntity> getByInfoId(Integer id) {
        QueryWrapper<QuestionEntity> queryWrapper = this.getDefaultQuery();
        queryWrapper.eq("info_id", id);
        return questionMapper.selectList(queryWrapper);
    }

    @Override
    public List<QuestionEntity> getByCode(String code) {
        return questionMapper.getByCode(code);
    }
}

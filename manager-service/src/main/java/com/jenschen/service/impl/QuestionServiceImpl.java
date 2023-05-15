package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jenschen.base.Response;
import com.jenschen.dao.InfoDao;
import com.jenschen.dao.QuestionDao;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.enumeration.InfoStatusEnum;
import com.jenschen.request.QuestionInfo;
import com.jenschen.request.QuestionReq;
import com.jenschen.response.QuestionResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.QuestionService;
import com.jenschen.util.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionServiceImpl extends AbstractService<QuestionEntity> implements QuestionService {

    private final QuestionDao questionDao;

    private final InfoDao infoDao;

    public QuestionServiceImpl(QuestionDao questionDao, InfoDao infoDao){
        this.questionDao = questionDao;
        this.infoDao = infoDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> add(QuestionReq questionReq) {
        InfoEntity infoEntity = infoDao.selectById(questionReq.getInfoId());
        //当info 被删除 或者已经发布时，不能添加内容
        if(InfoStatusEnum.PUBLISH.equals(infoEntity.getStatus()) || infoEntity.getIsDeleted()){
            return ResultUtil.error(ErrorEnum.DELETED_RECORD);
        }

        //删除问题
        deleteByInfoId(questionReq.getInfoId());

        //TODO 批量添加
//        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for(QuestionInfo questionInfo : questionReq.getQuestionInfoList()){
            QuestionEntity question = BeanUtil.copyProperties(questionInfo, QuestionEntity.class);
            question.setInfoId(questionReq.getInfoId());
            question.created(LocalDateTime.now(), 1);
//            questionEntityList.add(question);
            questionDao.insert(question);
        }
        return ResultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> edit(QuestionReq questionReq) {
        //数据被删除时
        InfoEntity infoEntity =  infoDao.selectById(questionReq.getInfoId());
        if(infoEntity == null ||  infoEntity.getIsDeleted()){
            return ResultUtil.error(ErrorEnum.DELETED_RECORD);
        }

        //当info已发布
        if(InfoStatusEnum.PUBLISH.equals(infoEntity.getStatus())){
            return ResultUtil.error(ErrorEnum.PUBLISHED_RECORD);
        }

        //需要先删除所有的数据
        this.deleteByInfoId(questionReq.getInfoId());

        //再添加信息
        return this.add(questionReq);
    }

    @Override
    public Response<Object> getByInfoId(Integer id) {
        List<QuestionEntity> questionEntityList = questionDao.getByInfoId(id);
        List<QuestionResp> resp = BeanUtil.copyToList(questionEntityList, QuestionResp.class);
        return ResultUtil.success(resp);
    }

    @Override
    public Response<Object> delete(Integer id) {
        QuestionEntity questionEntity = QuestionEntity.builder().build();
        questionEntity.setId(id);
        questionEntity.deleted(LocalDateTime.now(), 1);
        questionDao.updateById(questionEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> deleteByInfoId(Integer infoId) {
        QuestionEntity questionEntity = QuestionEntity.builder().build();
        questionEntity.setInfoId(infoId);
        questionEntity.deleted(LocalDateTime.now(), 1);
        questionDao.deleteByInfoId(questionEntity);
        return ResultUtil.success();
    }
}

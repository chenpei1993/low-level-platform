package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.QuestionEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.enumeration.InfoStatusEnum;
import com.jenschen.mapper.QuestionMapper;
import com.jenschen.request.QuestionInfo;
import com.jenschen.request.QuestionPageReq;
import com.jenschen.request.QuestionReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.QuestionResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.InfoService;
import com.jenschen.service.QuestionService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionServiceImpl extends AbstractService<QuestionEntity> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private InfoService infoService;

    @Override
    public Response<Object> add(QuestionReq questionReq) {
        InfoEntity infoEntity = infoService.get(questionReq.getInfoId());
        //当info 被删除 或者已经发布时，不能添加内容
        if(InfoStatusEnum.PUBLISH.equals(infoEntity.getStatus()) || infoEntity.getIsDeleted()){
            return ResultUtil.error(ErrorEnum.DELETED_RECORD);
        }

        //TODO 批量添加
//        List<QuestionEntity> questionEntityList = new ArrayList<>();
        for(QuestionInfo questionInfo : questionReq.getQuestionInfoList()){
            QuestionEntity question = BeanUtil.copyProperties(questionInfo, QuestionEntity.class);
            question.created(LocalDateTime.now(), 1);
//            questionEntityList.add(question);
            questionMapper.insert(question);
        }
        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(QuestionReq questionReq) {
        InfoEntity infoEntity = infoService.get(questionReq.getInfoId());
        //当info 被删除 或者已经发布时，不能修改内容
        if(InfoStatusEnum.PUBLISH.equals(infoEntity.getStatus()) || infoEntity.getIsDeleted()){
            return ResultUtil.error(ErrorEnum.DELETED_RECORD);
        }

        //需要先删除所有的数据
        this.deleteByInfoId(questionReq.getInfoId());

        //再添加信息
        return this.add(questionReq);
    }

    @Override
    public Response<Object> getByInfoId(QuestionPageReq questionPageReq) {
        QueryWrapper<QuestionEntity> queryWrapper = this.getPageQueryWrapper(questionPageReq);
        queryWrapper.eq("info_id", questionPageReq.getInfoId());
        List<QuestionEntity> questionEntityList = questionMapper.selectList(queryWrapper);
        List<QuestionResp> resp = BeanUtil.copyToList(questionEntityList, QuestionResp.class);
        int count = questionMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> delete(Integer id) {
        QuestionEntity questionEntity = QuestionEntity.builder().build();
        questionEntity.setId(id);
        questionEntity.deleted(LocalDateTime.now(), 1);
        questionMapper.updateById(questionEntity);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> deleteByInfoId(Integer infoId) {
        QuestionEntity questionEntity = QuestionEntity.builder().build();
        questionEntity.setInfoId(infoId);
        questionEntity.deleted(LocalDateTime.now(), 1);
        questionMapper.deleteByInfoId(questionEntity);
        return ResultUtil.success();
    }
}

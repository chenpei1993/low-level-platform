package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.dao.CustomerTagMapper;
import com.jenschen.dao.TagMapper;
import com.jenschen.request.Page;
import com.jenschen.request.TagReq;
import com.jenschen.entity.TagEntity;
import com.jenschen.response.PageResp;
import com.jenschen.response.TagResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.CustomerTagService;
import com.jenschen.service.TagService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl extends AbstractService<TagEntity> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CustomerTagService customerTagService;


    @Override
    public Response<Object> page(Page page) {
       QueryWrapper<TagEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<TagEntity> tagEntityList = tagMapper.selectList(queryWrapper);
        List<TagResp> resp = BeanUtil.copyToList(tagEntityList, TagResp.class);
        int count = tagMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> insert(TagReq tagReq) {
        TagEntity tag = BeanUtil.copyProperties(tagReq, TagEntity.class);
        tag.created(LocalDateTime.now(),1);
        tagMapper.insert(tag);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> get(int id) {
        TagEntity tagEntity = tagMapper.selectById(id);
        TagResp tagResp = BeanUtil.copyProperties(tagEntity, TagResp.class);
        return ResultUtil.success(tagResp);
    }

    @Override
    public Response<Object> updated(TagReq tagReq) {
        TagEntity tag = BeanUtil.copyProperties(tagReq, TagEntity.class);
        tag.updated(LocalDateTime.now(), 1);
        tagMapper.updateById(tag);
        return ResultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> deleted(TagReq tagReq) {
        TagEntity tag = BeanUtil.copyProperties(tagReq, TagEntity.class);
        tag.deleted(LocalDateTime.now(), 1);
        tagMapper.updateById(tag);
        //删除客户的标签
        customerTagService.deletedByTagId(tagReq.getId());
        return ResultUtil.success();
    }
}
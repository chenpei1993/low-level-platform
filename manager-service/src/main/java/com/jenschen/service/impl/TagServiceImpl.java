package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.dao.TagMapper;
import com.jenschen.request.Page;
import com.jenschen.request.TagReq;
import com.jenschen.entity.TagEntity;
import com.jenschen.response.TagResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.TagService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends AbstractService<TagEntity> implements TagService {

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper){
        this.tagMapper = tagMapper;
    }

    @Override
    public Response<Object> page(Page page) {
       QueryWrapper<TagEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<TagEntity> tagEntityList = tagMapper.selectList(queryWrapper);
        List<TagResp> resp = BeanUtil.copyToList(tagEntityList, TagResp.class);
        return ResultUtil.success(resp);
    }

    @Override
    public Response<Object> insert(TagReq tagDTO) {
        TagEntity tag = new TagEntity();
        BeanUtils.copyProperties(tagDTO, tag);
        tagMapper.insert(tag);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> get(int id) {
        TagEntity tagEntity = tagMapper.selectById(id);
        TagResp tagResp = new TagResp();
        BeanUtils.copyProperties(tagEntity, tagResp);
        return ResultUtil.success(tagResp);
    }

    @Override
    public Response<Object> updated(TagReq tagDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

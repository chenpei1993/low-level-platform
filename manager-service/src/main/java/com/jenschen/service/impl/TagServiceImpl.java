package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.dao.TagDao;
import com.jenschen.entity.RoleEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.mapper.TagMapper;
import com.jenschen.request.Page;
import com.jenschen.request.TagReq;
import com.jenschen.entity.TagEntity;
import com.jenschen.response.PageResp;
import com.jenschen.response.TagResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.CustomerTagService;
import com.jenschen.service.TagService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagServiceImpl extends AbstractService<TagEntity> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CustomerTagService customerTagService;


    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<TagEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<TagEntity> tagEntityList = tagDao.selectList(queryWrapper);
        List<TagResp> resp = BeanUtil.copyToList(tagEntityList, TagResp.class);
        int count = tagDao.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> insert(TagReq tagReq) {
        //TODO 是否需要验证标签名是否唯一
        TagEntity tag = BeanUtil.copyProperties(tagReq, TagEntity.class);
        tag.created(LocalDateTime.now(),1);
        tagDao.insert(tag);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> get(int id) {
        TagEntity tagEntity = tagDao.selectById(id);
        TagResp tagResp = BeanUtil.copyProperties(tagEntity, TagResp.class);
        return ResultUtil.success(tagResp);
    }

    @Override
    public Response<Object> all() {
        QueryWrapper<TagEntity> queryWrapper = this.getDefaultQuery();
        List<TagEntity> tagEntityList = tagDao.selectList(queryWrapper);
        List<TagResp> resp = BeanUtil.copyToList(tagEntityList, TagResp.class);
        return ResultUtil.success(resp);
    }

    @Override
    public Response<Object> update(TagReq tagReq) {

        //数据被删除时
        TagEntity tagEntity = tagDao.selectById(tagReq.getId());
        if(tagEntity == null ||  tagEntity.getIsDeleted()){
            return ResultUtil.error(ErrorEnum.DELETED_RECORD);
        }

        TagEntity tag = BeanUtil.copyProperties(tagReq, TagEntity.class);
        tag.updated(LocalDateTime.now(), 1);
        tagDao.updateById(tag);
        return ResultUtil.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Object> delete(int id) {
        TagEntity entity = TagEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        tagDao.updateById(entity);
        //删除客户的标签
        customerTagService.deletedByTagId(id);
        return ResultUtil.success();
    }
}

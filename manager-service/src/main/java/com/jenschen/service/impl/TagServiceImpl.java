package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.dao.TagMapper;
import com.jenschen.dto.TagDTO;
import com.jenschen.entity.Tag;
import com.jenschen.service.TagService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper){
        this.tagMapper = tagMapper;
    }

    @Override
    public Response<Object> insert(TagDTO tagDTO) {
//        Tag tag = new Tag();
//        BeanUtils.copyProperties(tagDTO, tag);
//        int res = tagMapper.insert(tag);
//        return res == 1 ? ResultUtil.success() : ResultUtil.fail();
        return null;
    }

    @Override
    public Response<Object> updated(TagDTO tagDTO) {
        return null;
    }

    @Override
    public Response<Object> deleted(int id) {
        return null;
    }
}

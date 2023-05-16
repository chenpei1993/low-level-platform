package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.helper.SpringHelper;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.TaskResp;
import com.jenschen.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SendTaskService")
public class SendTaskServiceImpl extends AbstractTipService {
    private static final int TYPE = 1;

    @Override
    public List<TaskEntity> getByInfoId(Integer infoId){
        return taskMapper.getByInfoId(TYPE , infoId);
    }

    @Override
    public void deleteByInfoId(Integer infoId){
        taskMapper.deleteByInfoId(TYPE, infoId, SpringHelper.getUserId());
    }

    @Override
    public Response<Object> pageSendTimer(SendTimerPageReq sendTimerPageReq) {
        QueryWrapper<TaskEntity> queryWrapper = this.getPageQueryWrapper(sendTimerPageReq);
        queryWrapper.eq("type", TaskTypeEnum.SEND.getValue());
        queryWrapper.eq("info_id", sendTimerPageReq.getInfoId());
        List<TaskEntity> tagEntityList = taskMapper.selectList(queryWrapper);
        List<TaskResp> resp = BeanUtil.copyToList(tagEntityList, TaskResp.class);
        int count = taskMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }
}

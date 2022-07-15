package com.jenschen.service.impl.task;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.TaskResp;
import com.jenschen.service.impl.TaskServiceImpl;
import com.jenschen.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SendTaskServiceImpl")
public class SendTaskServiceImpl extends TaskServiceImpl{


    @Override
    public Response<Object> pageSendTimer(SendTimerPageReq sendTimerPageReq) {
        QueryWrapper<TaskEntity> queryWrapper = this.getPageQueryWrapper(sendTimerPageReq);
        queryWrapper.eq("type", TaskTypeEnum.SEND.getValue());
        List<TaskEntity> tagEntityList = taskMapper.selectList(queryWrapper);
        List<TaskResp> resp = BeanUtil.copyToList(tagEntityList, TaskResp.class);
        int count = taskMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }
}

package com.jenschen.service.impl.task;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.cron.task.Task;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.TagEntity;
import com.jenschen.entity.TaskEntity;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.request.SendTimerPageReq;
import com.jenschen.request.TipTimerPageReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.TagResp;
import com.jenschen.response.TaskResp;
import com.jenschen.service.impl.TaskServiceImpl;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TipTaskServiceImpl")
public class TipTaskServiceImpl extends TaskServiceImpl{


    @Override
    public Response<Object> pageTipTimer(TipTimerPageReq tipTimerPageReq) {
        QueryWrapper<TaskEntity> queryWrapper = this.getPageQueryWrapper(tipTimerPageReq);
        queryWrapper.eq("type", TaskTypeEnum.TIP.getValue());
        List<TaskEntity> tagEntityList = taskMapper.selectList(queryWrapper);
        List<TaskResp> resp = BeanUtil.copyToList(tagEntityList, TaskResp.class);
        int count = taskMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
    }
}

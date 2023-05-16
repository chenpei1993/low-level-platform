package com.jenschen.service.impl.taskConverter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.info.InfoReq;

import java.time.LocalDateTime;
import java.util.List;

public interface InfoSpliter {

    /**
     * 将活动按照条件分割
     *
     * @param infoReq 活动信息请求
     * @param infoId  活动ID
     * @return 日期被分割的活动信息
     */
    List<InfoEntity> convert(InfoReq infoReq, int infoId);

    default InfoEntity getValidSubInfo(LocalDateTime dateTime, InfoReq infoReq, int infoId){
        int hours = infoReq.getBeginHours();
        int minutes = infoReq.getBeginMinutes();

        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        infoEntity.setId(infoId);
        LocalDateTime startDateTime = infoReq.getStartDateTime().withSecond(0);
        LocalDateTime endDateTime = infoReq.getEndDateTime().withSecond(0);



        LocalDateTime begin = LocalDateTimeUtil.of(dateTime);
        infoEntity.setStartDateTime(LocalDateTimeUtil.of(begin));

        begin = begin.withHour(hours).withMinute(minutes);
        if(begin.isBefore(startDateTime)){
            begin = begin.withHour(startDateTime.getHour()).withMinute(startDateTime.getMinute()).withSecond(0);
        }
        infoEntity.setSendDateTime(begin);

        LocalDateTime end = LocalDateTimeUtil.endOfDay(begin);
        if(end.isAfter(endDateTime)){
            end = end.withHour(endDateTime.getHour()).withMinute(endDateTime.getMinute()).withSecond(0);
        }
        infoEntity.setEndDateTime(end);

        return infoEntity;
    }
}

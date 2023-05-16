package com.jenschen.service.impl.taskConverter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.info.InfoReq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public interface InfoSpliter {

    /**
     * 将活动按照条件分割
     * @param infoReq 活动信息请求
     * @param infoId 活动ID
     * @return 日期被分割的活动信息
     */
    default List<InfoEntity> convert(InfoReq infoReq, int infoId){
        List<InfoEntity> infoEntityList = new ArrayList<>();
        LocalDateTime cur = LocalDateTimeUtil.beginOfDay(infoReq.getStartDateTime());
        while(cur.isBefore(infoReq.getEndDateTime())){
            int[] weeks = Arrays.stream(infoReq.getRepeatValue().split(","))
                    .flatMapToInt(e -> IntStream.of(Integer.parseInt(e)))
                    .toArray();
            if(Arrays.binarySearch(weeks, cur.getDayOfWeek().getValue()) >= 0) {
                InfoEntity infoEntity = getValidSubInfo(cur, infoReq, infoId);
                infoEntityList.add(infoEntity);
            }
            cur = cur.plusDays(1);
        }
        return infoEntityList;
    }

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

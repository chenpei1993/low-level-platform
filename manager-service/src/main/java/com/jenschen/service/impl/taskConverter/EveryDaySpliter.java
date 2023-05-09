package com.jenschen.service.impl.taskConverter;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.InfoReq;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EveryDaySpliter implements InfoSpliter {

    @Override
    public List<InfoEntity> convert(InfoReq infoReq, int infoId) {

        List<InfoEntity> infoEntityList = new ArrayList<>();
        LocalDateTime cur = LocalDateTimeUtil.beginOfDay(infoReq.getStartDateTime());
        while (cur.isBefore(infoReq.getEndDateTime())) {
            InfoEntity infoEntity = getValidSubInfo(cur, infoReq, infoId);
            infoEntityList.add(infoEntity);
            cur = cur.plusDays(1);
        }
        return infoEntityList;
    }

}

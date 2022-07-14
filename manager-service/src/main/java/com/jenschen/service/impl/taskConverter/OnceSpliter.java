package com.jenschen.service.impl.taskConverter;

import cn.hutool.core.bean.BeanUtil;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.InfoReq;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OnceSpliter implements InfoSpliter {

    @Override
    public List<InfoEntity> convert(InfoReq infoReq, int infoId) {
        List<InfoEntity> infoEntityList = new ArrayList<>();
        InfoEntity infoEntity = BeanUtil.copyProperties(infoReq, InfoEntity.class);
        infoEntity.setId(infoId);
        infoEntityList.add(infoEntity);
        return infoEntityList;
    }
}

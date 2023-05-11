package com.jenschen.dao;

import com.jenschen.entity.InfoEntity;

import java.util.List;

public interface InfoDao extends BaseDao<InfoEntity> {


    InfoEntity getByCode(String code);

    List<InfoEntity> getLastNInfo(Integer num);
}

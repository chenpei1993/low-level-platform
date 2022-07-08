package com.jenschen.dao;

import com.jenschen.entity.Info;
import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    Info selectByPrimaryKey(Integer id);

    List<Info> selectAll();

    int updateByPrimaryKey(Info record);
}
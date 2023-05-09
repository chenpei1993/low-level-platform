package com.jenschen.dao;

import com.jenschen.entity.HomeEntity;

public interface HomeDao {
    HomeEntity getBasicHomeInfo(String username);

}

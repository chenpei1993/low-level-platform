package com.jenschen.service;

public interface SettingService {

    /**
     * 根据key获取系统设置
     * @param key 键
     * @return 值
     */
    String getValue(String key);
}

package com.jenschen.service.impl;

import com.jenschen.entity.SettingEntity;
import com.jenschen.mapper.SettingMapper;
import com.jenschen.service.AbstractService;
import com.jenschen.service.SettingService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service()
public class SettingServiceImpl extends AbstractService<SettingEntity> implements SettingService {

    private final SettingMapper mapper;

    public static Map<String, SettingEntity> settings = new HashMap<>();

    SettingServiceImpl(SettingMapper mapper){
        this.mapper = mapper;
        this.init();
    }

    public void init(){
        List<SettingEntity> list = mapper.selectList(this.getDefaultQuery());
        for(var setting : list){
            settings.put(setting.getKey(), setting);
        }
    }

    @Override
    public String getValue(String key){
        SettingEntity setting = settings.get(key);
        if(setting == null){
            return "";
        }
        return setting.getValue();
    }
}

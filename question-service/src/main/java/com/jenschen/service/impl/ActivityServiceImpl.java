package com.jenschen.service.impl;

import com.jenschen.service.ActivityService;
import com.jenschen.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private InfoService infoService;

}

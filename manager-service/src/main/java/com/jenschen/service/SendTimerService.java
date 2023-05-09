package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.SendTimerPageReq;
import org.springframework.stereotype.Service;

@Service
public interface SendTimerService {
    /**
     * 分页
     * @param sendTimerPageReq 分页参数以及过滤
     * @return 结果
     */
    Response<Object> page(SendTimerPageReq sendTimerPageReq);
}

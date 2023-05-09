package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.TipTimerPageReq;
import org.springframework.stereotype.Service;

@Service
public interface TipTimerService {
    /**
     * 分页
     * @param tipTimerPageReq 分页参数以及过滤
     * @return 结果
     */
    Response<Object> page(TipTimerPageReq tipTimerPageReq);
}

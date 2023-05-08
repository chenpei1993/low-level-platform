package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.response.InfoResponse;

public interface InfoService {
    Response<Object> getInfo(String code);
}

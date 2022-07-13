package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.InfoReq;

public interface InfoService {
    /**
     * 插入消息
     * @param infoDTO 消息
     * @return 结果
     */
    Response<Object> insert(InfoReq infoDTO);

    /**
     * 更新消息
     * @param infoReq 消息
     * @return 结果
     */
    Response<Object> updated(InfoReq infoReq);

    /**
     * 逻辑删除消息
     * @param id 消息id
     * @return 结果
     */
    Response<Object> deleted(int id);
}

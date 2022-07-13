package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.CustomReq;
import com.jenschen.request.Page;

public interface CustomerService {
    /**
     * 插入客户
     * @param customReq 客户
     * @return 结果
     */
    Response<Object> insert(CustomReq customReq);

    /**
     * 更新客户
     * @param customReq 客户
     * @return 结果
     */
    Response<Object> updated(CustomReq customReq);

    /**
     * 逻辑删除客户
     * @param id 客户id
     * @return 结果
     */
    Response<Object> deleted(int id);

    /**
     * 插入标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);
}

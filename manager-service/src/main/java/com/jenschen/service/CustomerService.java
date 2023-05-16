package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.custom.CustomerReq;

public interface CustomerService {
    /**
     * 插入客户
     * @param customerReq 客户
     * @return 结果
     */
    Response<Object> insert(CustomerReq customerReq);

    /**
     * 更新客户
     * @param customerReq 客户
     * @return 结果
     */
    Response<Object> update(CustomerReq customerReq);

    /**
     * 逻辑删除客户
     * @param id 客户id
     * @return 结果
     */
    Response<Object> delete(int id);

    /**
     * 插入标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);
}

package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.dto.CustomDTO;

public interface CustomerService {
    /**
     * 插入客户
     * @param customDTO 客户
     * @return 结果
     */
    Response<Object> insert(CustomDTO customDTO);

    /**
     * 更新客户
     * @param customDTO 客户
     * @return 结果
     */
    Response<Object> updated(CustomDTO customDTO);

    /**
     * 逻辑删除客户
     * @param id 客户id
     * @return 结果
     */
    Response<Object> deleted(int id);
}

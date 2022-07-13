package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.TagReq;

public interface TagService {
    /**
     * 插入标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 插入标签
     * @param tagReq 标签
     * @return 结果
     */
    Response<Object> insert(TagReq tagReq);

    /**
     * 获取标签
     * @param id 标签
     * @return 结果
     */
    Response<Object> get(int id);

    /**
     * 更新标签
     * @param tagReq 标签
     * @return 结果
     */
    Response<Object> updated(TagReq tagReq);

    /**
     * 逻辑删除标签
     * @param tagReq 标签
     * @return 结果
     */
    Response<Object> deleted(TagReq tagReq);
}

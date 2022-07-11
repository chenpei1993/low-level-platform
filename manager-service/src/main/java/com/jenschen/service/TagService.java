package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.TagDTO;

public interface TagService {
    /**
     * 插入标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 插入标签
     * @param tagDTO 标签
     * @return 结果
     */
    Response<Object> insert(TagDTO tagDTO);

    /**
     * 获取标签
     * @param tagDTO 标签
     * @return 结果
     */
    Response<Object> get(int tagDTO);

    /**
     * 更新标签
     * @param tagDTO 标签
     * @return 结果
     */
    Response<Object> updated(TagDTO tagDTO);

    /**
     * 逻辑删除标签
     * @param id 标签id
     * @return 结果
     */
    Response<Object> deleted(int id);
}

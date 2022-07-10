package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.dto.TagDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface TagService {
    /**
     * 插入标签
     * @param tagDTO 标签
     * @return 结果
     */
    Response<Object> insert(TagDTO tagDTO);

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

package com.jenschen.service;

import com.jenschen.response.TagResp;

import java.util.List;

public interface CustomerTagService {

    /**
     * 根据用户ID删除
     * @param customerId 用户ID
     */
    void deletedByCustomerId(int customerId);

    /**
     * 根据标签ID删除
     * @param tagId 标签ID
     */
    void deletedByTagId(int tagId);

    /**
     * 根据CustomerID和对应的TagIDs 批量添加 映射
     * @param customerId 用户ID
     * @param tagIds 多个标签
     */
    void insertCustomerTags(int customerId, int[] tagIds);

    /**
     *
     */
    List<TagResp> getTagsByCustomerId(int customerId);
}

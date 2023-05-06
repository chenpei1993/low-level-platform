package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.entity.InfoEntity;
import com.jenschen.request.InfoReq;
import com.jenschen.request.Page;

public interface InfoService {

    /**
     * 分页获取活动信息
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 插入活动
     * @param infoDTO 活动信息
     * @return 结果
     */
    Response<Object> insert(InfoReq infoDTO);

    /**
     * 更新活动
     * @param infoReq 活动信息
     * @return 结果
     */
    Response<Object> edit(InfoReq infoReq);

    /**
     * 逻辑删除活动
     * @param id 活动id
     * @return 结果
     */
    Response<Object> delete(int id);

    /**
     *  根据活动Id获取一条数据
     * @param id 活动id
     * @return 结果
     */
    InfoEntity get(int id);
}

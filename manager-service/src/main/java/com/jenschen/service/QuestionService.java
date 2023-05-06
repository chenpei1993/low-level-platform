package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.QuestionReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface QuestionService {

    /**
     * 根据info id 添加问题集合
     * @param questionReq info id 和问题集合
     * @return 结果
     */
    Response<Object> add(QuestionReq questionReq);

    /**
     * 根据 info id 修改问题集合
     * @param questionReq questionReq info id 和问题集合
     * @return 结果
     */
    Response<Object> edit(QuestionReq questionReq);

    /**
     * 根据Info ID 获取对应的 问题
     * @param id 活动ID
     * @return 结果
     */
    Response<Object> getByInfoId(Integer id);

    /**
     * 根据问题id 删除问题
     * @param id 问题id
     * @return 结果
     */
    Response<Object> delete(Integer id);

    /**
     * 根据问卷 id 删除问题
     * @param infoId 问卷ID
     * @return 结果
     */
    Response<Object> deleteByInfoId(Integer infoId);

}

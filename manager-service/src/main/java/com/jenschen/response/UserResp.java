package com.jenschen.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResp extends BaseResponse {

    /**
     * 唯一键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

}

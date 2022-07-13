package com.jenschen.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagResp extends BaseResponse {

    /**
     * 唯一键
     */
    private Integer id;

    /**
     * 标签名
     */
    private String name;

    /**
     * 颜色
     */
    private String color;

}

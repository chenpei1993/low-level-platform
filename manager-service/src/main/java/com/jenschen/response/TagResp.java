package com.jenschen.response;

import lombok.Data;

@Data
public class TagResp extends BaseResponse {
    /**
     * 标签名
     */
    private String name;

    /**
     * 颜色
     */
    private String color;

}

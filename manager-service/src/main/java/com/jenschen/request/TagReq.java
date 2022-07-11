package com.jenschen.request;

import com.jenschen.annotation.ValidColor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TagReq {
    /**
     * 标签名
     */
    @NotBlank(message = "标签名不能为空")
    private String name;

    /**
     * 颜色
     */
    @ValidColor
    private String color;

}

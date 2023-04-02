package com.jenschen.request.user;

import com.jenschen.request.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReq extends Page {
    /**
     * 用户名
     */
    private String name;

}

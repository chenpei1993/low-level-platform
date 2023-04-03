package com.jenschen.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserReq {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 权限ID
     */
    private List<Integer> roleIds;

}

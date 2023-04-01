package com.jenschen.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResp {

    /**
     * json web token
     */
    private String jwt;
}

package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/hello")
    @ResponseBody
    public Response<Object> index(){
        return ResultUtil.success("ok");
    }
}

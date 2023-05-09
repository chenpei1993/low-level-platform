package com.jenschen.controller;


import com.jenschen.base.Response;
import com.jenschen.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private HomeService homeService;


    @GetMapping
    @ResponseBody
    public Response<Object> getHomeInfo(){
        return homeService.getHomeInfo();
    }
}

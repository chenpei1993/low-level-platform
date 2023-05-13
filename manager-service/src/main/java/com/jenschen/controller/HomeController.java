package com.jenschen.controller;


import com.jenschen.base.Response;
import com.jenschen.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }


    @GetMapping
    @ResponseBody
    public Response<Object> getHomeInfo(){
        return homeService.getHomeInfo();
    }
}

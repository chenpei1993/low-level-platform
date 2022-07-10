package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.dto.InfoDTO;
import com.jenschen.dto.TagDTO;
import com.jenschen.service.InfoService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 */
@RestController
@RequestMapping("info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    /**
     * 添加消息
     * @param infoDTO 消息
     * @return 结果
     */
    @PutMapping
    public Response<Object> add(@RequestBody InfoDTO infoDTO){
        return infoService.insert(infoDTO);
    }

    /**
     * 删除消息
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return ResultUtil.success();
    }
}

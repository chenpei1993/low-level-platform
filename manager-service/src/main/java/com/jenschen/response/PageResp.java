package com.jenschen.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResp<T> {
    private long total;
    private List<T> data;


    public static <T> PageResp<T> build(long total, List<T> data){
        PageResp<T> pageResp = new PageResp<>();
        pageResp.setData(data);
        pageResp.setTotal(total);
        return pageResp;
    }
}

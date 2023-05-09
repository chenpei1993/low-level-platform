package com.jenschen.response.home;

import com.jenschen.response.home.ChartData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HomeResp {

    private String name;

    private String role;

    private Integer infoTotal;

    private Integer publishInfoTotal;

    private List<ChartData> data;

}

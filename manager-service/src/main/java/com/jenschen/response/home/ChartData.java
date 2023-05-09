package com.jenschen.response.home;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChartData {
    private String name;
    private List<String> dates;
    private List<Integer> values;
}

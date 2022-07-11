package com.jenschen.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Page {
    @NotNull(message = "当前页不能为空")
    private Integer currentPage;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;
    private List<String> descOderBy;
    private List<String> ascOderBy;
}

package com.jenschen.request;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    private int currentPage;
    private int size;
    private List<String> descOderBy;
    private List<String> ascOderBy;
}

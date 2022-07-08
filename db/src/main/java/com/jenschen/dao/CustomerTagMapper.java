package com.jenschen.dao;

import com.jenschen.entity.CustomerTag;
import java.util.List;

public interface CustomerTagMapper {
    int insert(CustomerTag record);

    List<CustomerTag> selectAll();
}
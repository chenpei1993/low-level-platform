package com.jenschen.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public interface BaseEnum {
    @JsonValue
    int getValue();
}

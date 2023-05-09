package com.jenschen.util;

import com.jenschen.enumeration.BaseEnum;

public class EnumUtil {

    private EnumUtil(){};

    public static <E extends Enum<?> & BaseEnum> E valueOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == code)
                return e;
        }
        return null;
    }
}

package com.yrs.code;

/**
 * Created by yrs on 2017/4/11.
 */
public class Id<T> {
    private final Class<T> clazz;
    private final long value;
    public Id(Class<T> clazz, long value) {
        this.clazz = clazz;
        this.value = value;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public long getValue() {
        return value;
    }
}

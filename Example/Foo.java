package com.yrs.code;

/**
 * Created by yrs on 2017/4/6.
 */
public class Foo<T> {
    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
                
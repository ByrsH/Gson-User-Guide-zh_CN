package com.yrs.code;

/**
 * Created by yrs on 2017/4/11.
 */
public class Student {
    private Id id;
    private int age;
    private Password password;

    public Student(Id id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "id = " + id + " age = " + age + " password = " + password.getPassword();
    }
}
                
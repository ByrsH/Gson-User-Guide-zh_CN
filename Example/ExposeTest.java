package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Created by yrs on 2017/4/14.
 */
public class ExposeTest {
    private static class People{
        private String name;

        @Expose
        private int age;

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String [] args) {
        People people = new People("abc", 18);
        Gson gson = new Gson();
        System.out.println(gson.toJson(people));

        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(people));
    }
}
                
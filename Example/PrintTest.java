package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by yrs on 2017/4/12.
 */
public class PrintTest {
    public static void main(String [] args) {
        Gson gson = new Gson();
        People people = new People();
        people.setAge(18);
        people.setName("abc");

        System.out.println(gson.toJson(people));

        gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(people));
    }
}
                
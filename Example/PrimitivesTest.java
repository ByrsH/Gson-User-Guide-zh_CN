package com.yrs.code;

import com.google.gson.Gson;

/**
 * Created by yrs on 2017/4/4.
 */
public class PrimitivesTest {
    public static void main(String [] args) {
        //Serialization
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));               // ==> 1
        System.out.println(gson.toJson("abcd"));         // ==> "abcd"
        System.out.println(gson.toJson(new Long(10)));   // ==> 10
        int[] values = { 1 };
        System.out.println(gson.toJson(values));         // ==> [1]

        //Deserialization
        int one = gson.fromJson("1", int.class);
        Integer tow = gson.fromJson("2", Integer.class);
        Long three = gson.fromJson("3", Long.class);
        Boolean aBoolean = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
    }
}
                
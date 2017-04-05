package com.yrs.code;

import com.google.gson.Gson;

/**
 * Created by yrs on 2017/4/4.
 */
public class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
    private Integer value4;
    //不能循环引用，将会造成无限递归
    //private BagOfPrimitives bagOfPrimitives = new BagOfPrimitives();
    BagOfPrimitives() {
        //no-args constructor
    }

    public static void main(String [] args) {
        //Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);

        //Deserialization
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2.value4);
    }

}
                
package com.yrs.code;

import com.google.gson.Gson;

/**
 * Created by yrs on 2017/4/5.
 */
public class NestedClassTest {

    public static void main(String [] args) {
        A test = new A();
        test.a = "a";
        A.B testb = new A.B();
        testb.b = "b";
        Gson gson = new Gson();
        System.out.println(gson.toJson(test) + "class:" + test.getClass());
        System.out.println(gson.toJson(testb) + "class:" + testb.getClass());

        String json = "{" + "\"b\":\"b\" " + "}";
        System.out.println(json);
        System.out.println(gson.fromJson(json, A.B.class).getClass());  //类B为非静态时，也能够反序列化  ???
    }
}
                
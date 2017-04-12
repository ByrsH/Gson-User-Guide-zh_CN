package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by yrs on 2017/4/12.
 */
public class NullTest {
    public static class Foo {
        private final String s;
        private final int i;

        public Foo() {
            this(null, 5);
        }

        public Foo(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    public static void main(String [] args) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Foo foo = new Foo();
        String json = gson.toJson(foo);
        System.out.println(json);

        json = gson.toJson(null);
        System.out.println(json);


        gson = new Gson();
        json = gson.toJson(foo);
        System.out.println(json);

        json = gson.toJson(null);
        System.out.println(json);
    }

}
                
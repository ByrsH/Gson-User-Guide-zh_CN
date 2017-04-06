package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by yrs on 2017/4/6.
 */
public class GenericTest {

    public static void main(String [] args) {
        Gson gson = new Gson();
        Foo<People> foo = new Foo<People>();
        People people = new People();
        people.setName("a");
        people.setAge(1);
        foo.value = people;

        String json = gson.toJson(foo);
        System.out.println(json);
        foo = gson.fromJson(json, foo.getClass());
        System.out.println("foo.value = " + foo.value);
//        System.out.println("foo.value.getClass = "+ foo.value.getClass());    // error
        System.out.println("foo.getClass = "+foo.getClass());

        System.out.println("==========================================");

        Type fooType = new TypeToken<Foo<People>>(){}.getType();
        json = gson.toJson(foo, fooType);
        System.out.println(json);
        foo = gson.fromJson(json, fooType);
        System.out.println("foo.value = "+ foo.value);
        System.out.println("foo.value.getClass = "+ foo.value.getClass());
        System.out.println("fooType = "+fooType);
    }

}
                
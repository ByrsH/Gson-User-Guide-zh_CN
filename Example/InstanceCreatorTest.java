package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by yrs on 2017/4/11.
 */
public class InstanceCreatorTest {

    private static class PasswordInstanceCreator implements InstanceCreator<Password> {

        @Override
        public Password createInstance(Type type) {
            return new Password("123456");
        }
    }

    private static class IdInstanceCreator implements InstanceCreator<Id> {
        public Id createInstance(Type type) {
            return new Id(Object.class, 0L);
        }
    }

    public static void main(String [] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Password.class, new PasswordInstanceCreator());
        gsonBuilder.registerTypeAdapter(Id.class, new IdInstanceCreator()).create();
        Gson gson = gsonBuilder.create();

        String json = "{" + "\"id\": {}," +
                        "\"age\": 1," +
                        "\"password\": {}" +
                        "}";
        Student student = gson.fromJson(json, Student.class);
        System.out.println(student);


        json = "{" +
                "}";
        Id id = gson.fromJson(json, Id.class);
        System.out.println(id.getClazz() + " " + id.getValue());

    }


}
                
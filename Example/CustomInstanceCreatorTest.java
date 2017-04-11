package com.yrs.code;

/**
 * Created by yrs on 2017/4/11.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class CustomInstanceCreatorTest {

    static class PasswordInstanceCreator implements InstanceCreator<Password> {
        @Override
        public Password createInstance(Type type) {
            return new Password("default_password");
        }
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Password.class,
                        new PasswordInstanceCreator()) //Register custom instance creator
                .setPrettyPrinting().create();

        String jsonStr =
                "{" +
                        "\"firstName\": \"Sriram\"," +
                        "\"lastName\": \"Kasireddi\"," +
                        "\"age\": 2," +
                        "\"hobby\": \"Singing\"," +
                        "\"dob\": \"May 6, 2010 12:00:00 AM\"," +
                        "\"password\": {}" +
                        "}";

        Student3 stud = gson.fromJson(jsonStr, Student3.class);
        System.out.println("password : " + stud.getPassword().getPassword());
    }


}


                
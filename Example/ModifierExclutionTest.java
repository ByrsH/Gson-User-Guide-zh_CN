package com.yrs.code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by yrs on 2017/4/14.
 */
public class ModifierExclutionTest {

    private static class ModiferClass {
        private static String name = "abc";
        private transient String idCard;

        public ModiferClass(String idCard) {
            this.idCard = idCard;
        }
    }

    public static void main(String [] args) {
        ModiferClass modiferClass = new ModiferClass("123456");
        Gson gson = new Gson();
        System.out.println(gson.toJson(modiferClass));

        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
        System.out.println(gson.toJson(modiferClass));

        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE)
                .create();
        System.out.println(gson.toJson(modiferClass));

    }
}
                
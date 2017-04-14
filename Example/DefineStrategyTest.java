package com.yrs.code;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yrs on 2017/4/14.
 */
public class DefineStrategyTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface Foo {
        // Field tag only annotation
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface FloatTag {
        // Field tag only annotation
    }

    public static class SampleObjectForTest {
        @Foo private final int annotatedField;
        private final String stringField;
        private final long longField;
        @FloatTag private final float floatField;
        private final Class<?> clazzField;

        public SampleObjectForTest() {
            annotatedField = 5;
            stringField = "someDefaultValue";
            longField = 1234;
            clazzField = null;
            floatField = 0;
        }
    }

    public static class MyExclusionStrategy implements ExclusionStrategy {
        private final Class<?> typeToSkip;

        private MyExclusionStrategy(Class<?> typeToSkip) {
            this.typeToSkip = typeToSkip;
        }

        public boolean shouldSkipClass(Class<?> clazz) {
            return (clazz == typeToSkip);
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getAnnotation(Foo.class) != null || f.getAnnotation(FloatTag.class) != null;
        }
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new MyExclusionStrategy(String.class))
                .serializeNulls()
                .create();
        SampleObjectForTest src = new SampleObjectForTest();
        String json = gson.toJson(src);
        System.out.println(json);
    }
}
                
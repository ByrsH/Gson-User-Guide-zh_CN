package com.yrs.code;

import com.google.gson.*;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by yrs on 2017/4/10.
 */
public class CustomTest {

    private static class DateTimeSerializer implements JsonSerializer<DateTime> {

        public JsonElement serialize(DateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(dateTime.toString());
        }
    }

    private static class DateTimeDeserializer implements JsonDeserializer<DateTime> {

        public DateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new DateTime(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }


    public static void main(String [] args) {
        DateTime time = new DateTime(new Date());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeSerializer());
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeDeserializer());
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(time);
        System.out.println("serialize DateTime json = " + json);

        System.out.println("deserialize DateTime json = " + gson.fromJson(json, DateTime.class));
    }

}
                
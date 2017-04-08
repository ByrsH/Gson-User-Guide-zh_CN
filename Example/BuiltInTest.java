package com.yrs.code;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by yrs on 2017/4/8.
 */
public class BuiltInTest {
    public static void main(String [] args) {
        try {
            URL url = new URL("https://github.com/google/gson/");
            URI uri = new URI("/google/gson/");
            Gson gson = new Gson();
            String jsonUrl = gson.toJson(url);
            String jsonUri = gson.toJson(uri);
            System.out.println("url gson built_in serialize = " + jsonUrl);
            System.out.println("uri gson built_in serialize = " + jsonUri);

            System.out.println("url gson built_in deserialize = " + gson.fromJson(jsonUrl, URL.class));
            System.out.println("uri gson built_in deserialize = " + gson.fromJson(jsonUri, URI.class));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
                
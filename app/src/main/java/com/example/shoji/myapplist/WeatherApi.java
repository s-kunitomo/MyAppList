package com.example.shoji.myapplist;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shoji on 2015/01/04.
 */
public class WeatherApi
{
    private static final String USER_AGENT = "WeatherForecasts Sample";
    private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";

    public static WeatherForecast getWeather(Context context, String pointId) throws IOException, JSONException
    {
        AndroidHttpClient client = AndroidHttpClient.newInstance(USER_AGENT, context);
        HttpGet get = new HttpGet(URL + pointId);
        StringBuilder stringBuilder = new StringBuilder();

        try
        {
            HttpResponse response = client.execute(get);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = null;
            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        }
        finally
        {
            client.close();
        }
//        Log.v("WebAPI_TEST", stringBuilder.toString());
        //return stringBuilder.toString();
        return new WeatherForecast(new JSONObject(stringBuilder.toString()));
    }
}

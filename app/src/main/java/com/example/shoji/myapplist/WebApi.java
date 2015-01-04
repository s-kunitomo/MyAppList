package com.example.shoji.myapplist;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class WebApi extends ActionBarActivity
{
    private TextView textView;
//    private Handler handler;

    private class GetWeatherForecastTask extends GetWeatherForecastApiTask
    {
        public GetWeatherForecastTask(Context context)
        {
            super(context);
        }

        @Override
        protected void onPostExecute(WeatherForecast data)
        {
            super.onPostExecute(data);

            if (data != null)
            {
                textView.setText(data.location.area  + " " + data.location.prefecture + " " + data.location.city);

                for (WeatherForecast.Forecast forecast : data.forecastList)
                {
                    textView.append("\n");
                    textView.append(forecast.dateLabel + " " + forecast.telop);
                }
            }
            else if (exception != null)
            {
                Toast.makeText(WebApi.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_api);

        textView = (TextView) findViewById(R.id.tv_main);

//        handler = new Handler();
//        Log.v("WebAPI_TEST", "1");
//        Thread thread = new Thread()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    final String data = WeatherApi.getWeather(WebApi.this, "400040");
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(data);
//                        }
//                    });
//                }
//                catch(final IOException e)
//                {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(WebApi.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        };
//        thread.start();
        new GetWeatherForecastTask(this).execute("400040");
    }
}

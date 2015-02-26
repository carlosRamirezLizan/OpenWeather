package com.openweather;

import android.os.AsyncTask;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.openweather.entity.City;
import com.openweather.entity.error.ApiError;
import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */

public class Services {

    private static final String GET_CITY_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String GET_METHOD = "GET";
    private static final int TIMEOUT = 10000;
    private static final int TIMEOUT_READ = 15000;

    public static void loadCityWeather(final String city, final CompletionHandlerWithError completionHandlerWithError)
    {
        performConnection(city, GET_CITY_WEATHER_URL, new ConnectionCompletionHandler() {
            @Override
            public void call(Object response, HttpURLConnection connection, Exception exception)
            {
                String body = (String) response;
                City city = null;
                ApiError error = null;

                try {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK && !body.isEmpty()) {
                        city = gson.fromJson(body, new TypeToken<City>() {}.getType());
                        error = gson.fromJson(body, new TypeToken<ApiError>() {}.getType());
                    }
                    if (connection.getResponseCode() >= 400){
                        error = gson.fromJson(body, new TypeToken<ApiError>() {}.getType());
                    }
                    completionHandlerWithError.call(city != null, city, error);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    completionHandlerWithError.call(false, city, error);
                }
            }
        });
    }

    public static AsyncTask<Void, Void, HashMap<String, Object>> performConnection(final String city, final String urlString, final ConnectionCompletionHandler completionHandler)
    {
        return new AsyncTask<Void, Void, HashMap<String, Object>>() {
            @Override
            protected HashMap<String, Object> doInBackground(Void... params)
            {
                Object response = null;
                HttpURLConnection urlConnection = null;
                Exception exception = null;

                try {
                    HttpRequest httpRequest = HttpRequest.get(urlString+city).connectTimeout(TIMEOUT).readTimeout(TIMEOUT_READ).followRedirects(true);;
                    urlConnection = httpRequest.getConnection();
                    response = httpRequest.body();
                }
                catch (Exception e) {
                    exception = e;
                    e.printStackTrace();
                }

                HashMap<String, Object> data = new HashMap<>();
                data.put("response", response);
                data.put("connection", urlConnection);
                data.put("exception", exception);

                return data;
            }

            @Override
            protected void onPostExecute(HashMap<String, Object> data)
            {
                super.onPostExecute(data);

                Object response = data.get("response");
                HttpURLConnection urlConnection = (HttpURLConnection) data.get("connection");
                Exception exception = (Exception) data.get("exception");

                if (!isCancelled() && completionHandler != null) {
                    completionHandler.call(response, urlConnection, exception);
                }
            }

            @Override
            protected void onCancelled()
            {
                super.onCancelled();

                if (completionHandler != null) {
                    completionHandler.call(null, null, null);
                }
            }
        }.execute();
    }


    abstract public interface CompletionHandlerWithError {
        void call(boolean success, Object response, ApiError error);
    }


    abstract public interface ConnectionCompletionHandler {

        void call(Object response, HttpURLConnection connection, Exception exception);
    }


}

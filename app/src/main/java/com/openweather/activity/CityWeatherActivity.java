package com.openweather.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.openweather.R;
import com.openweather.Services;
import com.openweather.Utils;
import com.openweather.entity.City;
import com.openweather.entity.error.ApiError;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Carlos Ramírez Lizán on 16/01/2015
 * Narami Solutions Inc.
 */
public class CityWeatherActivity extends ActionBarActivity {

    public static final String CITY = "city";
    private static final String GET_WEATHER_ICON_URL = "http://openweathermap.org/img/w/";

    String city_name;
    private TextView titleTextView, currentTemperatureTextView, windTextView,
            mainWeatherDescriptionTextView, pressureTextView, humidityTextView, sunriseTextView, sunsetTextView,
            geoBoundsTextView;
    private ImageView iconImageView;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null || getIntent().getExtras() == null) {
            this.finish();
        }
        city_name = getIntent().getExtras().getString(CITY);
        setContentView(R.layout.activity_city_weather);

        Services.loadCityWeather(city_name, new Services.CompletionHandlerWithError() {
            @Override
            public void call(boolean success, Object response, ApiError error) {
                if(success && response instanceof City) {
                    city = (City) response;
                    setUpView();
                }
                else if(error!=null){
                   Utils.alertDialog(CityWeatherActivity.this, error.getMessage(), new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           CityWeatherActivity.this.finish();
                       }
                   }).show();
                } else {
                    Utils.alertDialog(CityWeatherActivity.this, getResources().getString(R.string.error_message_default), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CityWeatherActivity.this.finish();
                        }
                    }).show();
                }
            }
        });
    }

    private void setUpView(){
        iconImageView =(ImageView) findViewById(R.id.iconImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        currentTemperatureTextView = (TextView) findViewById(R.id.currentTemperatureTextView);
        mainWeatherDescriptionTextView = (TextView) findViewById(R.id.mainWeatherDescriptionTextView);
        pressureTextView = (TextView) findViewById(R.id.pressureTextView);
        windTextView = (TextView) findViewById(R.id.windTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        sunriseTextView = (TextView) findViewById(R.id.sunriseTextView);
        sunsetTextView = (TextView) findViewById(R.id.sunsetTextView);
        geoBoundsTextView = (TextView) findViewById(R.id.geoBoundsTextView);
        iconImageView = (ImageView) findViewById(R.id.iconImageView);

        loadDataInView();
    }

    private void loadDataInView(){
        if(city.getName()!=null && !TextUtils.isEmpty(city.getName())) {
            titleTextView.setText(city.getName());
        }
        currentTemperatureTextView.setText(String.valueOf(Math.round(city.getTermicInformation().getTemp()-273)));
        if(city.getWeather()!=null && city.getWeather().size()!=0) {
            mainWeatherDescriptionTextView.setText(city.getWeather().get(0).getDescription());
        }
        pressureTextView.setText(String.valueOf(city.getTermicInformation().getPressure() + " hpa"));
        humidityTextView.setText(String.valueOf(city.getTermicInformation().getHumidity() + " %"));
        windTextView.setText(String.valueOf(city.getWind().getSpeed() + "m/s"));
        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat df2 = new SimpleDateFormat("HH:mm");
        String sunrise = df.format(city.getInformation().getSunrise());
        String sunset = df2.format(city.getInformation().getSunset());
        sunriseTextView.setText(sunrise);
        sunsetTextView.setText(sunset);
        geoBoundsTextView.setText("lat: " + city.getCoordinates().getLat() + " long: "+ city.getCoordinates().getLon());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getBaseContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(options)
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

        ImageLoader.getInstance().displayImage(GET_WEATHER_ICON_URL + city.getWeather().get(0).getIcon()+".png", iconImageView);
    }
}

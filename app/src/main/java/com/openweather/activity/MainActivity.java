package com.openweather.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.openweather.R;


public class MainActivity extends ActionBarActivity {

    private EditText selectCityEditText;
    private Button showWheatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectCityEditText = (EditText)findViewById(R.id.selectCityEditText);
        showWheatherButton = (Button)findViewById(R.id.showWeatherButton);

        showWheatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCity();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkCity() {
        if (selectCityEditText != null) {
            if (selectCityEditText.getText() != null && !selectCityEditText.getText().toString().equals("")) {
                Intent intent = new Intent(this, CityWeatherActivity.class);
                intent.putExtra("city",selectCityEditText.getText().toString());
                startActivity(intent);
            }
        }
    }
}

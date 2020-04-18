package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import schema.Data;


public class secondactivity extends AppCompatActivity {

    RequestQueue queue;
    ImageView image;
    TextView temp,unit,city,weather,humidity,pressure;

    String URL="http://api.openweathermap.org/data/2.5/weather?q=Panchkula&APPID=c15d6d52f4eb8f1a3cb0f07affb8e1a7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);



        image=findViewById(R.id.weatheriamge);
        temp=findViewById(R.id.temp);
        unit=findViewById(R.id.unit);
        city=findViewById(R.id.city);
        weather=findViewById(R.id.wea);
        humidity=findViewById(R.id.hum);
        pressure=findViewById(R.id.press);
    final Gson gson=new GsonBuilder().create();

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Data d=gson.fromJson(response,Data.class);
                Toast.makeText(secondactivity.this,
                        ""+d.getCoord().getLat(), Toast.LENGTH_SHORT).show();


                temp.setText(""+(int)(d.getMain().getTemp()-273));
                city.setText(""+d.getName());
                weather.setText(d.getWeather().get(0).getMain());
                humidity.setText(""+d.getMain().getHumidity());
                pressure.setText(""+d.getMain().getPressure());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(secondactivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
       queue.add(request);
        Toast.makeText(secondactivity.this,""+queue,Toast.LENGTH_SHORT).show();


    }
}

package com.example.smartcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    GifImageView gif_routes;
    GifImageView gif_vikram;
    GifImageView gif_buses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gif_buses = (GifImageView) findViewById(R.id.bus_info_image_view);
        gif_vikram = (GifImageView) findViewById(R.id.vikram_image_view);
        gif_routes = (GifImageView) findViewById(R.id.route_image_view);
        try{
            InputStream inputStreamBus = getAssets().open("fK2201o.gif");
            byte[] bytesBus = IOUtils.toByteArray(inputStreamBus);
            gif_buses.setBytes(bytesBus);
            gif_buses.startAnimation();
            InputStream inputStreamVikram = getAssets().open("rikshaw.gif");
            InputStream inputStreamRoute = getAssets().open("Compass.gif");
            byte[] byteVikram = IOUtils.toByteArray(inputStreamVikram);
            byte[] byteRoute = IOUtils.toByteArray(inputStreamRoute);
            gif_vikram.setBytes(byteVikram);
            gif_routes.setBytes(byteRoute);
            gif_vikram.startAnimation();
            gif_routes.startAnimation();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void routes(View view){
        Intent intent = new Intent(this,Routes.class);
        startActivity(intent);
    }

    public void busInfo(View view){
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
    }

    public void vikramInfo(View view){
        //Intent intent = new Intent(this,VikramInfo.class);
        //startActivity(intent);
    }
}

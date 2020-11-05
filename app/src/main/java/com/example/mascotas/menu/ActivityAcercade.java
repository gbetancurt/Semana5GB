package com.example.mascotas.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mascotas.R;

public class ActivityAcercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
        setOnClick();
    }
    private void setOnClick(){
        final ImageButton botonatras = (ImageButton) findViewById(R.id.botonatras);
        botonatras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
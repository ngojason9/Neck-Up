package net.jason.sensortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class survey2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);

        Intent intent = getIntent();

        ImageView imgview = (ImageView) findViewById(R.id.imageView2);
        imgview.bringToFront();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(survey2.this, smiley2.class);
                startActivity(myIntent);
            }
        });

    }
}

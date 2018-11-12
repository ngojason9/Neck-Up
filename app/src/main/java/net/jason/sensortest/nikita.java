package net.jason.sensortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class nikita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nikita);

        Intent intent = getIntent();

        ImageView imgview = (ImageView) findViewById(R.id.imageView5);
        imgview.bringToFront();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(nikita.this, dashboard.class);
                startActivity(myIntent);
            }
        });

    }
}

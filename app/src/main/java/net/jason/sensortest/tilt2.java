package net.jason.sensortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;



public class tilt2 extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean color = false;
    private View view;
    private long lastUpdate;

    public static void wakeDevice(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
                (PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK
                        | PowerManager.ACQUIRE_CAUSES_WAKEUP), "sensor:wake");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt2);

        view = findViewById(R.id.textView2);
        view.setBackgroundColor(Color.WHITE);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        Intent intent = getIntent();

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            getAccelerometer(event);
            getOrientation(event);
        }
    }

    private void getOrientation(SensorEvent event) {
        float[] values = event.values;

        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        long actualTime = event.timestamp;

        if (-3 < z && z < 3) {
//        if (.4 < x && x < .8) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
//            view.setBackgroundColor(Color.WHITE);
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.screenBrightness =1f;// i needed to dim the display
            this.getWindow().setAttributes(lp);

        } else {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
//            Toast.makeText(this, "Tilt Your Device Upright", Toast.LENGTH_SHORT).show();
//            view.setBackgroundColor(Color.BLACK);
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.screenBrightness =0.00001f;// i needed to dim the display
            this.getWindow().setAttributes(lp);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
//                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}

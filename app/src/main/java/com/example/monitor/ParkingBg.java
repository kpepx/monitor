package com.example.monitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParkingBg extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_bg);
        Button buttonleave = (Button)findViewById(R.id.leavepage);
        buttonleave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeFragment.class);
                startActivity(i);
            }
        });
    }
}
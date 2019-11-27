package com.example.monitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
public class ParkingPage  extends Activity {
    Dialog myDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking);
        myDialog = new Dialog(this);
        Button buttonback = (Button)findViewById(R.id.buttonback);
        buttonback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FavFragment.class);
                startActivity(i);
            }
        });
    }
    public void showpopup (View v){
        TextView txtclose;
        myDialog.setContentView(R.layout.info);
        txtclose = (TextView) myDialog.findViewById(R.id.leave);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void fullpopup(View v) {
        TextView txtclose;
        myDialog.setContentView(R.layout.info_full);
        txtclose = (TextView) myDialog.findViewById(R.id.leavefull);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

}


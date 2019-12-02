package com.example.monitor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ParkingPage extends Fragment {
    DatabaseReference data;
    Dialog myDialog;
    String rfid = "F2 B2 FC 1E";
    public String value_place;
    TextView close_txt,slot_txt,available_txt,open_txt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.parking, container, false);
        myDialog = new Dialog(getActivity());
        final ImageView image_1 = (ImageView) view.findViewById(R.id.image1);
        final ImageView image_2 = (ImageView) view.findViewById(R.id.image2);
        final ImageView image_3 = (ImageView) view.findViewById(R.id.image3);
        final TextView name = (TextView) view.findViewById(R.id.namePark);
        Button buttonback = (Button) view.findViewById(R.id.buttonback);
        buttonback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data = database.getReference().child("User").child(rfid);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String value_status = String.valueOf(map.get("status"));
                if(value_status.equals("in")) {
                    value_place = String.valueOf(map.get("place"));
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    data = database.getReference().child("Park").child(value_place);
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map map = (Map) dataSnapshot.getValue();
                            String value_name = String.valueOf(map.get("name"));
                            name.setText(value_name);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    data = database.getReference().child("Park").child(value_place).child("CarIn");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map map = (Map) dataSnapshot.getValue();
                            String car1 = String.valueOf(map.get("car_1"));
                            String car2 = String.valueOf(map.get("car_2"));
                            String car3 = String.valueOf(map.get("car_3"));
                            if (car1.equals("1")) {
                                image_1.setImageResource(R.drawable.red);
                            }
                            else if (car1.equals("0")) {
                                image_1.setImageResource(R.drawable.green);
                            }
                            else{
                                image_3.setImageResource(R.drawable.notavaliable);
                            }
                            if (car2.equals("1")) {
                                image_2.setImageResource(R.drawable.red);
                            }
                            else if (car2.equals("0")) {
                                image_2.setImageResource(R.drawable.green);
                            }
                            else{
                                image_3.setImageResource(R.drawable.notavaliable);
                            }
                            if (car3.equals("1")) {
                                image_3.setImageResource(R.drawable.red);
                            }
                            else if (car3.equals("0")) {
                                image_3.setImageResource(R.drawable.green);
                            }
                            else{
                                image_3.setImageResource(R.drawable.notavaliable);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Button info = (Button) view.findViewById(R.id.buttoninfo);
        info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showpopup(view);
            }
        });
        return view;
    }
    public void showpopup (View v){
        TextView txtclose;
        myDialog.setContentView(R.layout.info);
        close_txt = (TextView) myDialog.findViewById(R.id.close);
        open_txt = (TextView) myDialog.findViewById(R.id.open);
        slot_txt = (TextView) myDialog.findViewById(R.id.capacity);
        available_txt = (TextView) myDialog.findViewById(R.id.available);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data = database.getReference().child("Park").child(value_place);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String value_slot = String.valueOf(map.get("slot"));
                slot_txt.setText("Capacity: "+value_slot+" Cars");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                data = database.getReference().child("Park").child(value_place).child("CarIn");
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int count = 0;
                        for (DataSnapshot carin : dataSnapshot.getChildren()) {
                            if (carin.getValue(int.class) == 0) {
                                count++;
                            }
                            carin.getKey();
                        }
                        available_txt.setText("Available: "+Integer.toString(count)+" Cars");
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                open_txt.setText("Open: "+dataSnapshot.child("open").child("hour").getValue()+":"+dataSnapshot.child("open").child("min").getValue()+" AM");
                close_txt.setText("Close: "+dataSnapshot.child("close").child("hour").getValue()+":"+dataSnapshot.child("open").child("min").getValue()+" PM");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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

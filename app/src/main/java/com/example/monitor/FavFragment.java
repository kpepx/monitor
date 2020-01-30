package com.example.monitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class FavFragment extends Fragment {
    SharedPreferences myPrefs;
    public DatabaseReference data;
    String rfid = "F2 B2 FC 1E";
    String value_1,value_2,value_3,value_count;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav,container,false);
        final Button image_1 = (Button) view.findViewById(R.id.imageView3);
        final Button image_2 = (Button) view.findViewById(R.id.imageView6);
        final Button image_3 = (Button) view.findViewById(R.id.imageView7);
        myPrefs = this.getActivity().getSharedPreferences("ID", 0);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data = database.getReference().child("User").child(rfid).child("fav");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value_1 = String.valueOf(dataSnapshot.child("1").getValue());
                value_2 = String.valueOf(dataSnapshot.child("2").getValue());
                value_3 = String.valueOf(dataSnapshot.child("3").getValue());
                value_count = String.valueOf(dataSnapshot.child("count").getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
//        if(value_count.equals("0")){
//            image_1.setVisibility(View.GONE);
//            image_2.setVisibility(View.GONE);
//            image_3.setVisibility(View.GONE);
//        }
//        if(value_count.equals("1")){
//            if(value_1.equals("1")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 1).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_1.setText("FIBO");
//            }
//            else if(value_1.equals("2")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 2).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_1.setText("CB1");
//            }
//            else if(value_1.equals("51")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 51).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_1.setText("TEACHER");
//            }
//            image_2.setVisibility(View.GONE);
//            image_3.setVisibility(View.GONE);
//        }
//        else if(value_count.equals("2")){
//            if(value_1.equals("0")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 2).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                Button map2 = (Button)view.findViewById(R.id.button2);
//                map2.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 51).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_1.setText("CB1");
//                image_2.setText("TEACHER");
//            }
//            if(value_2.equals("0")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 1).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                Button map2 = (Button)view.findViewById(R.id.button2);
//                map2.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 51).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_1.setText("FIBO");
//                image_2.setText("TEACHER");
//            }
//            if(value_3.equals("0")){
//                Button map = (Button)view.findViewById(R.id.button1);
//                map.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 1).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                Button map2 = (Button)view.findViewById(R.id.button2);
//                map2.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        myPrefs.edit().putInt("MapClick", 2).apply();
//                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new MapParking()).commit();
//                    }
//                });
//                image_2.setText("CB1");
//                image_1.setText("FIBO");
//            }
//            image_3.setVisibility(View.GONE);
//        }
//        else if(value_count.equals("3")){
                Button map1 = (Button)view.findViewById(R.id.imageView3);
                map1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        myPrefs.edit().putInt("MapClick", 1).apply();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new MapParking()).commit();
                    }
                });
                Button map2 = (Button)view.findViewById(R.id.imageView6);
                map2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        myPrefs.edit().putInt("MapClick", 2).apply();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new MapParking()).commit();
                    }
                });
                Button map3 = (Button)view.findViewById(R.id.imageView7);
                map3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        myPrefs.edit().putInt("MapClick", 51).apply();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new MapParking()).commit();
                    }
                });
            image_1.setText("FIBO");
            image_1.setTextSize( 55);
            image_2.setText("CB1");
            image_2.setTextSize(55);
            image_3.setText("TEACHER");
            image_3.setTextSize(55);
//        }
        return view;
    }
}

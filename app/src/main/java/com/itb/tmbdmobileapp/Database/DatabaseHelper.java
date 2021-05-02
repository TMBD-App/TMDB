package com.itb.tmbdmobileapp.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itb.tmbdmobileapp.Fragments.MovieIds;
import com.itb.tmbdmobileapp.Fragments.RecomendationsFragment;
import com.itb.tmbdmobileapp.Fragments.WellcomeFragment;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.Modelos.TV;
import com.itb.tmbdmobileapp.Modelos.User;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("User");


    public static void insert(User u) {
        myRef.child(u.getId()).child("email").setValue(u.getEmail());
        myRef.child(u.getId()).child("username").setValue(u.getUsername());
        myRef.child(u.getId()).child("id").setValue(u.getId());

    }

    public static void search (String uid) {
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                User u = snapshot.getValue(User.class);
                WellcomeFragment.textViewHeaderUsername.setText(u.getUsername());
                WellcomeFragment.textViewHeaderEmail.setText(u.getEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static void getList (String uid){
        myRef.child(uid).child("pelisFavoritas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Integer> ids = new ArrayList<>();
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    int data = datasnapshot.getValue(Integer.class);
                    ids.add(data);
                }

                RecomendationsFragment.pelisFav = ids;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child(uid).child("seriesFavoritas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Integer> ids = new ArrayList<>();
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    int data = datasnapshot.getValue(Integer.class);
                    ids.add(data);
                }
                RecomendationsFragment.seriesFav = ids;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child(uid).child("actoresFavoritos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Integer> ids = new ArrayList<>();
                for (DataSnapshot datasnapshot : snapshot.getChildren()){
                    int data = datasnapshot.getValue(Integer.class);
                    ids.add(data);
                }

                RecomendationsFragment.actoresFav = ids;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public static void updatePelis (Movie m, String id){
        myRef.child(id).child("pelisFavoritas").child(m.getId()+"").setValue(m.getId());
    }

    public static void updateSeries (TV tv, String id){
        myRef.child(id).child("seriesFavoritas").child(tv.getId() + "").setValue(tv.getId());
    }

    public static void updateActores (People p, String id){
        myRef.child(id).child("actoresFavoritos").child(p.getId()+"").setValue(p.getId());
    }

}
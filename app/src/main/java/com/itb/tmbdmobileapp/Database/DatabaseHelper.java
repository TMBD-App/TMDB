package com.itb.tmbdmobileapp.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itb.tmbdmobileapp.Fragments.WellcomeFragment;
import com.itb.tmbdmobileapp.Modelos.Movie;
import com.itb.tmbdmobileapp.Modelos.User;


public class DatabaseHelper {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("User");
    public static User u;

    public static void insert(User u) {
        myRef.child(u.getId()).child("email").setValue(u.getEmail());
        myRef.child(u.getId()).child("username").setValue(u.getUsername());
        myRef.child(u.getId()).child("id").setValue(u.getId());

        for (int i = 0; i < u.getPelis().size(); i++){
            myRef.child(u.getId()).child("pelisFavoritas").child(u.getPelis().get(i)+"").setValue(u.getPelis().get(i));
        }

        // FOR PARA SERIES Y ACTORES
    }

    public static void search (String uid) {
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                u = snapshot.getValue(User.class);
                WellcomeFragment.textViewHeaderUsername.setText(u.getUsername());
                WellcomeFragment.textViewHeaderEmail.setText(u.getEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public static void updatePelis (Movie m, String id){

        myRef.child(id).child("pelisFavoritas").child(m.getId() + "").setValue(m.getId());

    }



    public void delete(String id) {
        myRef.child(id).removeValue();
    }
}
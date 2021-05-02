package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.itb.tmbdmobileapp.Activities.MainActivity;
import com.itb.tmbdmobileapp.Database.DatabaseHelper;
import com.itb.tmbdmobileapp.Modelos.People;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.Support.AppFragmentPossibilities;
import com.itb.tmbdmobileapp.Support.Common;
import com.squareup.picasso.Picasso;

public class ActorDetailsFragment extends Fragment {
    private ImageView image;
    private TextView name, departemnt;
    public static RecyclerView recyclerViewFilms, recyclerViewSeries;
    private FloatingActionButton addButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WellcomeFragment.fragment = this;
        WellcomeFragment.currentFragment = AppFragmentPossibilities.ActorDetailsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specific_people, container, false);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        People people = (People) requireArguments().get("actor");

        addButton = view.findViewById(R.id.add_button);

        image = view.findViewById(R.id.photoSpecific);
        name = view.findViewById(R.id.titleSpecific);
        departemnt = view.findViewById(R.id.textViewDepartment);
        recyclerViewFilms = view.findViewById(R.id.recyclerViewFilms);
        recyclerViewSeries = view.findViewById(R.id.recyclerViewSeries);

        Picasso.get().load(Common.MOVIEDB_SMALL_POSTER_URL + people.getProfile_path()).into(image);
        name.setText(people.getName());
        departemnt.setText(people.getKnown_for_department());

        recyclerViewFilms.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSeries.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        MainActivity.apiClient.setActorFilms(requireView(), people.getId());
        MainActivity.apiClient.setActorSeries(requireView(), people.getId());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper.updateActores(people, FirebaseAuth.getInstance().getUid());
                Toast.makeText(getContext(), "ACTOR ADDED CORRECTLY", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

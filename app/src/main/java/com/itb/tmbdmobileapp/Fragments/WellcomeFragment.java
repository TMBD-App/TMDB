package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.itb.tmbdmobileapp.SupportFragmentManagement.AppFragmentPossibilities;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.SupportFragmentManagement.FragmentChanger;

public class WellcomeFragment extends Fragment {
    private NavigationView navigationView;

    private boolean isShowingNavDrawer = false;

    public static AppFragmentPossibilities currentFragment;
    public static Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tmbd_content, container, false);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentFragment = AppFragmentPossibilities.RecomendationsFragment;

        navigationView = view.findViewById(R.id.navigationView);
        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);

        toolbar.setOnClickListener(v -> changeDrawerVisibility());

        navigationView.setNavigationItemSelectedListener(item -> {
            if (currentFragment != AppFragmentPossibilities.RecomendationsFragment) {
                NavDirections navDirections = FragmentChanger.anyFragmentToRecomendations(currentFragment);
                Navigation.findNavController(fragment.getView()).navigate(navDirections);
            }

            switch (item.getItemId()){
                case R.id.item_peliculas:

                    break;
                case R.id.item_series:

                    break;
                case R.id.item_actores:

                    break;
                case R.id.item_favoritos:

                    break;
            }
            return false;
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            if (currentFragment != AppFragmentPossibilities.SearchFragment) {
                NavDirections navDirections = FragmentChanger.anyFragmentToSearch(currentFragment);
                Navigation.findNavController(fragment.getView()).navigate(navDirections); }
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeDrawerVisibility() {
        int visibility;
        if (isShowingNavDrawer) visibility = View.INVISIBLE;
        else visibility = View.VISIBLE;
        navigationView.setVisibility(visibility);
        isShowingNavDrawer = !isShowingNavDrawer;
    }
}

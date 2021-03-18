package com.pharos.android3lesson4weatherapi.ui.locations;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.pharos.android3lesson4weatherapi.R;
import com.pharos.android3lesson4weatherapi.databinding.FragmentLocationsBinding;
import com.pharos.android3lesson4weatherapi.ui.base.BaseFragment;
import com.pharos.android3lesson4weatherapi.ui.locations.adapter.LocationsAdapter;

import java.util.List;

public class LocationsFragment extends BaseFragment<FragmentLocationsBinding> {
    public LocationsFragment(){}

    @Override
    protected FragmentLocationsBinding bind() {
        return FragmentLocationsBinding.inflate(getLayoutInflater());
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> cityList = List.of(
                "Bishkek", "London",
                "Moscow", "Miami",
                "Montreal", "Munich",
                "Muscat", "Manila",
                "Tokyo", "Dubai",
                "Barcelona", "Rome",
                "Chicago", "Toronto");
        ui.recyclerLocation.setAdapter(new LocationsAdapter(cityList, city -> {
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            NavHostFragment navHostFragment =
                    (NavHostFragment)   requireActivity()
                            .getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.weatherFragment, bundle);

        }));
    }
}
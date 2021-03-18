package com.pharos.android3lesson4weatherapi.ui.weather;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.Glide;
import com.pharos.android3lesson4weatherapi.R;
import com.pharos.android3lesson4weatherapi.data.model.MyWeather;
import com.pharos.android3lesson4weatherapi.data.model.Weather;
import com.pharos.android3lesson4weatherapi.data.remote.WeatherStorage;
import com.pharos.android3lesson4weatherapi.databinding.FragmentWeatherBinding;
import com.pharos.android3lesson4weatherapi.ui.base.BaseFragment;
import com.pharos.android3lesson4weatherapi.ui.weather.adapter.WeatherAdapter;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint("SetTextI18n")
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private String city;

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){city = getArguments().getString("city");}
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ui.textCity.setOnClickListener(v -> navigate());
        ui.recyclerWeather.setAdapter(new WeatherAdapter());
        getWeather();
    }

    private void getWeather() {
        WeatherStorage.getWeather(city,
                "metric",
                "4bbf5a1ed98cd9f688ebb3651474cdd2",
                new WeatherStorage.GetWeatherByCity() {
                    @Override
                    public void onSuccess(MyWeather myWeather) {setValues(myWeather);}

                    @Override
                    public void onFailure(String error) {
                        Log.e("TAG", "onFailure: " + error );
                    }
                });
    }

    private void setValues(MyWeather myWeather) {

        String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
        ui.textDate.setText(date);

        String cityName =  myWeather.getName();
        String countryName = myWeather.getSys().getCountry();
        ui.textCity.setText(cityName + " " + countryName + "  ");

        ArrayList<Weather> cloud = (ArrayList<Weather>) myWeather.getWeather();
        Weather weather = cloud.get(0);
        ui.textCloud.setText(String.valueOf( weather.getMain()));

        String iconCode = weather.getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        Glide.with(getActivity()).load(iconUrl).into(ui.imageCloud);

        Double fahrenheit = myWeather.getMain().getTemp();
        ui.textCelsiusAmount.setText(String.valueOf(fahrenheit));

        Double fahrenheitUp = myWeather.getMain().getTempMax();
        ui.textCelsiusUp.setText(String.valueOf(fahrenheitUp));

        Double fahrenheitDown = myWeather.getMain().getTempMin();
        ui.textCelsiusDown.setText(String.valueOf(fahrenheitDown));

        Integer humidity  = myWeather.getMain().getHumidity();
        ui.textPercentHumidity.setText(humidity + "%");

        Integer pressure  = myWeather.getMain().getPressure();
        ui.textMBarPressure.setText(pressure + "mBar");

        Double wind  = myWeather.getWind().getSpeed();
        ui.textKMHWind.setText(wind + "km/h");

        int sunriseSecs =  myWeather.getSys().getSunrise();
        @SuppressLint("SimpleDateFormat")
        String dateRise = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date (sunriseSecs*1000));
        ui.textSunRiseTime.setText(dateRise);

        int sunsetSecs =  myWeather.getSys().getSunset();
        @SuppressLint("SimpleDateFormat")
        String dateSet = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date (sunsetSecs*1000));
        ui.textSunSetTime.setText(dateSet);

    }

    private void navigate() {
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        navController.navigate(R.id.locationsFragment);
    }
}
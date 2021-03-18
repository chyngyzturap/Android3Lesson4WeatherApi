package com.pharos.android3lesson4weatherapi.data.remote;

import androidx.annotation.NonNull;
import com.pharos.android3lesson4weatherapi.data.model.MyWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherStorage {
    public static void getWeather(String city,
                                  String units,
                                  String appId,
                                  GetWeatherByCity getWeatherByCity) {
        RetrofitBuilder
                .getInstance()
                .getWeather(city, units, appId)
                .enqueue(new Callback<MyWeather>() {
                    @Override
                    public void onResponse(@NonNull Call<MyWeather> call, @NonNull Response<MyWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            getWeatherByCity.onSuccess(response.body());
                        } else {
                            getWeatherByCity.onFailure(response.message() + "if else ");
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<MyWeather> call, @NonNull Throwable t) {
                        getWeatherByCity.onFailure(t.getLocalizedMessage() + "with t.get");
                    }
                });
    }

    public interface GetWeatherByCity {
        void onSuccess(MyWeather myWeather);
        void onFailure(String error);
    }
}
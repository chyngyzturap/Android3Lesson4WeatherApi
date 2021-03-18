package com.pharos.android3lesson4weatherapi.ui.weather.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.pharos.android3lesson4weatherapi.R;
import com.pharos.android3lesson4weatherapi.databinding.ListItemBinding;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.R)
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    List<Integer> list =  List.of(
            R.drawable.ic_sunny,
            R.drawable.ic_cloudy,
            R.drawable.ic_hazy,
            R.drawable.ic_storm,
            R.drawable.ic_tornado,
            R.drawable.ic_wind_tree);
    List<String> listDays =  List.of("Mon 21", "Tue 22", "Wed 25", "Thu 5", "Fri 2", "Sat 15");
    List<String> listCelsiusUp =  List.of("35°C↑", "30°C↑", "34°C↑", "7°C↑", "4°C↑", "8°C↑");
    List<String> listCelsiusDown = List.of("26°C↓",  "23°C↓", "29°C↓", "2°C↓", "1°C↓", "5°C↓");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding ui = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() { return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ListItemBinding ui;

        public ViewHolder(@NonNull ListItemBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            ui.imageWeather.setImageResource(list.get(position));
            ui.textDays.setText(String.valueOf(listDays.get(position)));
            ui.textCelsiusUp.setText(String.valueOf(listCelsiusUp.get(position)));
            ui.textCelsiusUp.setText(listCelsiusUp.get(position));
            ui.textCelsiusDown.setText(listCelsiusDown.get(position));
            ui.textCelsiusDown.setText(listCelsiusDown.get(position));
        }
    }
}

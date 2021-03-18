package com.pharos.android3lesson4weatherapi.ui.locations.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pharos.android3lesson4weatherapi.databinding.LocationItemBinding;
import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.CityHolder> {

    private final List<String> cities;
    private final LocationClick locationClick;

    public LocationsAdapter(List<String> cities, LocationClick locationClick) {
        this.cities = cities;
        this.locationClick = locationClick;
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationItemBinding ui =
                LocationItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CityHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        holder.onBind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {
        private final LocationItemBinding ui;

        public CityHolder(@NonNull LocationItemBinding ui ) {
            super(ui.getRoot());
            this.ui = ui;
            ui.getRoot().setOnClickListener(v -> locationClick.cityClick(cities.get(getAdapterPosition())));
        }

        public void onBind(String city) {
            ui.textTitle.setText(city);
        }
    }

    public interface LocationClick {
        void cityClick(String city);
    }
}
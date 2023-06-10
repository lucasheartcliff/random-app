package com.example.randomapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator3.R;
import com.example.randomapp.model.City;

import java.util.List;

public class ListAdapter extends ArrayAdapter<City> {

    public ListAdapter(Context context, List<City> items) {
        super(context, 0, items);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        City currentCity = getItem(position);


        TextView textName = listItemView.findViewById(R.id.text_name);
        TextView textState = listItemView.findViewById(R.id.text_state);
        TextView textPopulation = listItemView.findViewById(R.id.text_population);
        TextView textIncome = listItemView.findViewById(R.id.text_income);
        TextView textFoundationDate = listItemView.findViewById(R.id.text_foundation_date);
        TextView textAirports = listItemView.findViewById(R.id.text_airports);
        TextView textExtensionArea = listItemView.findViewById(R.id.text_extension_area);
        TextView textWeather = listItemView.findViewById(R.id.text_weather);
        TextView textTouristic = listItemView.findViewById(R.id.text_touristic);
        TextView textRate = listItemView.findViewById(R.id.text_rate);

        textName.setText(currentCity.getName());
        textState.setText(currentCity.getState());
        textPopulation.setText(String.valueOf(currentCity.getPopulation()));
        textIncome.setText(String.valueOf(currentCity.getPerCapitaIncome()));
        textFoundationDate.setText(currentCity.getFoundationDate().toString());
        textAirports.setText(String.valueOf(currentCity.hasAirPorts()));
        textExtensionArea.setText(String.valueOf(currentCity.getExtensionArea()));
        textWeather.setText(currentCity.getWeather().toString());
        textTouristic.setText(String.valueOf(currentCity.isTouristic()));
        textRate.setText(String.valueOf(currentCity.getRate()));

        return listItemView;
    }
}

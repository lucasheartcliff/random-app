package com.example.randomapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator3.R;
import com.example.randomapp.model.City;
import com.example.randomapp.views.FormView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class CityAdapter extends ArrayAdapter<City> {
    private Consumer<List<Object>> onClickRow;

    public CityAdapter(Context context, List<City> items,Consumer<List<Object>> onClickRow) {
        super(context, 0, items);
        this.onClickRow = onClickRow;
    }


    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
        textState.setText("Estado: "+currentCity.getState());
        textPopulation.setText("N° População: "+currentCity.getPopulation());
        textIncome.setText("Renda Per Capita: "+currentCity.getPerCapitaIncome());
        textFoundationDate.setText(currentCity.getFoundationDate()!=null? dateFormat.format(Date.from(currentCity.getFoundationDate() )) :null);
        textAirports.setText(currentCity.hasAirPorts() ? "Possui aeroporto":"Não possui aeroporto");
        textExtensionArea.setText("Area Municipal: "+currentCity.getExtensionArea());
        textWeather.setText("Tipo de clima: "+currentCity.getWeather().toString());
        textTouristic.setText(currentCity.isTouristic()? "Possui pontos turísticos":"Não possui ponto turístico");
        textRate.setText(String.format("N° de Estrela: %s/5.0", currentCity.getRate()));


        listItemView.setOnClickListener(v->{
            this.onClickRow.accept(Arrays.asList(v.getContext(),position,currentCity));
        });

        return listItemView;
    }
}

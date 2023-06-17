package com.example.randomapp.views;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.calculator3.R;
import com.example.randomapp.model.City;
import com.example.randomapp.model.WeatherOption;
import com.example.randomapp.service.CityService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

public class FormView extends View {
    private CityService cityService;
    private EditText editName;
    private EditText editState;
    private EditText editPopulation;
    private EditText editIncome;
    private EditText editFoundationDate;
    private CheckBox checkboxAirports;
    private EditText editExtensionArea;
    private Spinner spinnerWeather;
    private CheckBox checkboxTouristic;
    private RatingBar editRate;
    private Button btnSave;

    private SimpleDateFormat dateFormatter;

    private Integer idx;
    private boolean loaded;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        associateElements();
        cityService = new CityService(getSharedPreferences());
        Optional<Object[]> optional = getParamsObject();

        if(optional.isPresent()){
            idx = (Integer) optional.get()[0];
            btnSave.setOnClickListener(v->this.onClickToSave(v,idx));
            City city = (City)optional.get()[1];
            setViewFromCity(city);

            cityService.saveDraft(idx,city);
        }
        loaded = true;
    }

    @Override
    protected void associateElements() {
        editName = findViewById(R.id.edit_name);
        editState = findViewById(R.id.edit_state);
        editPopulation = findViewById(R.id.edit_population);
        editIncome = findViewById(R.id.edit_income);
        editFoundationDate = findViewById(R.id.edit_foundation_date);
        editFoundationDate.setOnFocusChangeListener(this::showDatePickerDialog);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        checkboxAirports = findViewById(R.id.checkbox_airports);
        editExtensionArea = findViewById(R.id.edit_extension_area);
        spinnerWeather = findViewById(R.id.spinner_weather);
        checkboxTouristic = findViewById(R.id.checkbox_touristic);
        editRate = findViewById(R.id.rate);
        editRate.setNumStars(5);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this::onClickToSave);
    }

    private void showDatePickerDialog(android.view.View view, boolean isFocused) {
        if(!isFocused) return;
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (v, year1, monthOfYear, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year1, monthOfYear, dayOfMonth);
                    String formattedDate = dateFormatter.format(selectedDate.getTime());
                    editFoundationDate.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
    private void onClickToSave(android.view.View view) {
    this.onClickToSave(view,null);

    }
    @SuppressLint("NewApi")
    private void onClickToSave(android.view.View view, Integer idx) {
        City city = getCityFromView();

        if(idx !=null) cityService.update(idx,city);
        else cityService.create(city);
        cityService.clearDraft();

        toView(view.getContext(), CitiesListView.class);

    }

    @SuppressLint("NewApi")
    public City getCityFromView() {
        City city = new City();

        city.setName(editName.getText().toString());
        city.setState(editState.getText().toString());
        city.setPopulation(editPopulation.getText().toString().equals("") ?0:Integer.parseInt(editPopulation.getText().toString()));
        city.setPerCapitaIncome(editIncome.getText().toString().equals("") ?null:Double.parseDouble(editIncome.getText().toString()));
        String foundationDateString = editFoundationDate.getText().toString();
        Instant foundationDate =foundationDateString.equals("")?null:City.parseDateString(foundationDateString);
        city.setFoundationDate(foundationDate);
        city.setHasAirPorts(checkboxAirports.isChecked());
        city.setExtensionArea(editExtensionArea.getText().toString().equals("") ?0:Double.parseDouble(editExtensionArea.getText().toString()));
        String selectedWeatherOption = spinnerWeather.getSelectedItem().toString();
        WeatherOption weatherOption = WeatherOption.valueOf(selectedWeatherOption.toUpperCase());
        city.setWeather(weatherOption);
        city.setTouristic(checkboxTouristic.isChecked());
        city.setRate(editRate.getRating());

        return city;
    }
    @SuppressLint("NewApi")
    public void setViewFromCity(City city) {
        editName.setText(city.getName());
        editState.setText(city.getState());
        editPopulation.setText(String.valueOf(city.getPopulation()));
        editIncome.setText(String.valueOf(city.getPerCapitaIncome()));
        // You need to convert the Instant to a string representation of the date
         String foundationDateString = dateFormatter.format(Date.from(city.getFoundationDate()));
        editFoundationDate.setText(foundationDateString);
        checkboxAirports.setChecked(city.hasAirPorts());
        editExtensionArea.setText(String.valueOf(city.getExtensionArea()));
        String weatherOptionString = city.getWeather().toString();
        int spinnerPosition = ((ArrayAdapter) spinnerWeather.getAdapter()).getPosition(weatherOptionString);
        spinnerWeather.setSelection(spinnerPosition);
        checkboxTouristic.setChecked(city.isTouristic());
        editRate.setRating(city.getRate());
    }


    @SuppressLint("NewApi")
    @Override
    protected void onPause(){
        super.onPause();
        City city = getCityFromView();
        cityService.saveDraft(idx,city);
        loaded=false;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume(){
        super.onResume();
        if(loaded)return;
        idx = cityService.getIdxFromDraft();
        City city = cityService.getCityFromDraft();
        if(city != null) setViewFromCity(city);
        cityService.clearDraft();
    }

}

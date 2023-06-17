package com.example.randomapp.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Optional;

public abstract class View extends Activity {
    protected static final String PREFS_NAME = "random_app";


    protected Double parseEditTextValueToDouble(EditText et) {
        return et.getText() == null ? null : et.getText().toString().equals("") ? null : Double.parseDouble(et.getText().toString());
    }

    protected Integer parseEditTextValueToInteger(EditText et) {
        Double value = parseEditTextValueToDouble(et);
        return value != null ? value.intValue() : null;
    }

    protected abstract void associateElements();

    protected void toLoadingView(Context ctx) {
        toView(ctx, LoadingView.class);
    }

    protected void toView(Context ctx, Class<? extends View> clazz) {
        toView(ctx, clazz, null, 0);
    }

    protected void toView(Context ctx, Class<? extends View> clazz, Serializable param, long delay) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ctx, clazz);
                if (param != null) intent.putExtra("params", param);
                startActivity(intent);
                finish();
            }
        }, Math.max(delay, 0));
    }

    @SuppressLint("NewApi")
    protected <T extends Serializable> Optional<T> getParamsObject() {
        return Optional.ofNullable((T) getIntent().getSerializableExtra("params") );
    }

    protected SharedPreferences getSharedPreferences(){
        return super.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }
}

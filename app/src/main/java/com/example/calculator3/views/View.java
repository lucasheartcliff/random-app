package com.example.calculator3.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.widget.EditText;

import java.io.Serializable;

public abstract class View extends Activity {

    protected Double parseEditTextValueToDouble(EditText et) {
        return et.getText() == null ? null : Double.parseDouble(et.getText().toString());
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
                intent.putExtra("params", param);
                startActivity(intent);
                finish();
            }
        }, Math.max(delay, 0));
    }

    protected Serializable getParamsObject() {
        return getIntent().getSerializableExtra("params");
    }
}

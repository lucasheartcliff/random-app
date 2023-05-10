package com.example.randomapp.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator3.R;
import com.example.randomapp.model.FormModel;
import com.example.randomapp.model.ZoneSituation;

public class ResultView extends View {
    private TextView resultMessage, recommendationMessage;
    private Button finishButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        associateElements();

        Object[] paramTuple = (Object[]) getParamsObject();
        FormModel formModel = (FormModel) paramTuple[0];
        ZoneSituation situation = (ZoneSituation) paramTuple[1];

        boolean isActive = formModel.isActive();
        String favoriteFood = formModel.getFavoriteFood();

        resultMessage.setText(buildResult(situation));
        recommendationMessage.setText(buildRecommendation(situation, favoriteFood, isActive));
    }

    @Override
    protected void associateElements() {
        resultMessage = (TextView) findViewById(R.id.resultTextView);
        recommendationMessage = (TextView) findViewById(R.id.recomendationTextView);
        finishButton = (Button) findViewById(R.id.resultFinishButton);
        finishButton.setOnClickListener(this::onClickToFinish);
    }

    private void onClickToFinish(android.view.View view) {
        toView(ResultView.this, ActivityMainView.class);
    }

    private String buildResult(ZoneSituation situation) {
        return "A pessoa avaliada está " + situation.getSituation() + ".";
    }

    private String buildRecommendation(ZoneSituation situation, String favoriteFood, boolean isActive) {
        String res = "";

        if (!ZoneSituation.NORMAL.equals(situation)) {
            res = "É necessário procurar um nutricionista para rever a alimentação, evitando comer " + favoriteFood;
            if (!isActive) res += " e procurar ser mais ativo físicamente";
            res += ".";
        }

        return res;
    }
}

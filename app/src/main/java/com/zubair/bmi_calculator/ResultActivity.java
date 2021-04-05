package com.zubair.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnBack;
    TextView txtMessage1, txtResult, txtMessage2, txtResultMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();

        Intent intent = getIntent();
        String getBMI = intent.getStringExtra("BMI");
        String getClassification = intent.getStringExtra("classification");

        setResults(getBMI, getClassification);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setResults(String getBMI, String classification){
        txtMessage1.setText("Your BMI Result");
        txtResult.setText(String.valueOf(getBMI));
        txtMessage2.setText(classification);
        txtResultMessage.setText("Normal BMI range is 18.5kg/m2 - 25kg/m2 Try to exercise more");
    }

    private void init() {
        imageView = findViewById(R.id.imageViewResult);
        btnBack = findViewById(R.id.btnBack);
        txtMessage1 = findViewById(R.id.txtMessage1);
        txtResult = findViewById(R.id.txtResult);
        txtMessage2 = findViewById(R.id.txtMessage2);
        txtResultMessage = findViewById(R.id.txtResultMessage);

    }
}
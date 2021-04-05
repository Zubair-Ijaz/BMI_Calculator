package com.zubair.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editTextName, editTextGender, editTextAge, editTextHeight, editTextWeight;
    Button btnCalculate;

    String path = String.valueOf(Environment.getExternalStoragePublicDirectory(
    Environment.DIRECTORY_MOVIES));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    String name = editTextName.getText().toString();
                    String gender = editTextGender.getText().toString();
                    String age = editTextAge.getText().toString();
                    float height = Float.valueOf(editTextHeight.getText().toString());
                    float weight = Float.valueOf(editTextWeight.getText().toString());
                    double result = calculate_BMI(height, weight);
                    String message = classification(result);
//                    String res = String.format("%.2f", result);

                    String data = name + " " + gender + " " + age + "" + height + " " + weight + " " + result;

                    try {
                        FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                        outputWriter.write(data);
                        outputWriter.close();
                        //display file saved message
                        Toast.makeText(getBaseContext(), "File saved successfully!",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    Environment.getExternalStorageState();
//                    generateNoteOnSD(MainActivity.this, "BMI_result.txt", data);

                    Intent intent = new Intent(MainActivity.this, com.zubair.bmi_calculator.ResultActivity.class);
                    intent.putExtra("BMI", String.valueOf(result));
                    intent.putExtra("classification", message);
                    startActivity(intent);
                }
            }
        });
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculate_BMI(float height, float weight){
        double BMI = weight/(height*height);
        return BMI;
    }

    private String classification(double BMI){
        String message = null;
        if (BMI < 18.5){
            message = "Body Mass Deficit";
        }else if (BMI == 18.5 || BMI < 24.9){
            message = "Normal Body Mass";
        }else if (BMI == 25.0 || BMI < 29.9){
            message = "Excessive Body Mass";
        }else if (BMI == 30.0 || BMI < 34.9){
            message = "Obesity 1st Degree";
        }else if (BMI == 35.0 || BMI < 39.9){
            message = "Obesity 2nd Degree";
        }else if (BMI >= 40.0){
            message = "Obesity 3rd Degree";
        }
        return message;
    }

    private boolean checkValidation() {
        boolean flag = true;
        if (editTextName.getText().toString().isEmpty()){
            editTextName.setError("Name can't be empty.");
            flag = false;
        }
        if (editTextGender.getText().toString().isEmpty()){
            editTextGender.setError("Gender can't be empty.");
            flag = false;
        }
        if (editTextAge.getText().toString().isEmpty()){
            editTextAge.setError("Password can't be empty.");
            flag = false;
        }
        if (editTextHeight.getText().toString().isEmpty()){
            editTextHeight.setError("Password can't be empty.");
            flag = false;
        }
        if (editTextWeight.getText().toString().isEmpty()){
            editTextWeight.setError("Password can't be empty.");
            flag = false;
        }
        return flag;
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        editTextName = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
    }
}
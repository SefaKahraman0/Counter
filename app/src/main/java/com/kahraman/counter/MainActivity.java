package com.kahraman.counter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewMainNumber;
    Button buttonMinus,buttonPlus,buttonDisableMinus,buttonDisableVibrate,buttonReset;
    int mainNumber = 0;
    Vibrator vibrator;
    boolean controlMinusClickable = true;
    boolean controlVibrateSelect=true;
    
    public void writeMainNumber(){
        textViewMainNumber.setText(String.valueOf(mainNumber));
    }
    public void alertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("The Counter Will Be Reset");
        alert.setMessage("Do You Accept?");
        alert.setCancelable(true);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainNumber=0;
                writeMainNumber();
                vibrateOn(500);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }
    public void callNumberPositive(){
        mainNumber+=1;
        writeMainNumber();
    }
    public void callNumberNegative(){
        mainNumber-=1;
        writeMainNumber();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        vibrator = (Vibrator) getSystemService(MainActivity.this.VIBRATOR_SERVICE);

        textViewMainNumber = findViewById(R.id.textViewMainNumber);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDisableMinus = findViewById(R.id.buttonDisableMinus);
        buttonDisableVibrate = findViewById(R.id.buttonDisableVibrate);


    buttonPlus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            callNumberPositive();
            vibrateOn(50);
        }
    });

    buttonMinus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            callNumberNegative();
            vibrateOn(50);
        }
    });

    buttonReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            alertDialog();
            vibrateOn(100);
        }
    });

    buttonDisableMinus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (controlMinusClickable==true){
                buttonMinus.setClickable(false);
                controlMinusClickable=false;
                vibrateOn(100);
                buttonMinus.setTextColor(Color.parseColor("#682B27"));
            }
            else {
                buttonMinus.setClickable(true);
                controlMinusClickable=true;
                vibrateOn(100);
                buttonMinus.setTextColor(Color.parseColor("#7B7982"));
            }
        }
    });
    buttonDisableVibrate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            vibrateOn(100);
        if (controlVibrateSelect==true){
            controlVibrateSelect=false;
        }
            else{
                controlVibrateSelect=true;
        }
        }
    });

    }
    public void vibrateOn(int vibrationValue){
        if(controlVibrateSelect==true){
            vibrator.vibrate(vibrationValue);
        }
        else{
            vibrator.cancel();
        }
    }
}
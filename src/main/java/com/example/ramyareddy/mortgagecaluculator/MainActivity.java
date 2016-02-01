package com.example.ramyareddy.mortgagecaluculator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Calculator");


        SeekBar seek=(SeekBar) findViewById(R.id.seek1);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView t=(TextView) findViewById(R.id.interestrate);
                t.setText(String.valueOf((float)progress/100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button calculate=(Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float amount;
                float interest;
                int months;
                double monthlypayment=0;
                double tax=0;
                amount=months=0;
                Vibrator vib;
                vib = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);



                EditText edittext1=(EditText)findViewById(R.id.amount);

                if((edittext1.getText().toString()).isEmpty()) {
                    edittext1.setText("0000");

                }
                    amount = Float.valueOf(edittext1.getText().toString());


                SeekBar seekbar=(SeekBar)findViewById(R.id.seek1);
                interest=seekbar.getProgress()/100;

                RadioGroup g=(RadioGroup)findViewById(R.id.radiogroup1);
                int id=g.getCheckedRadioButtonId();
                switch(id){
                    case R.id.radiobutton1:
                        RadioButton radio1=(RadioButton)findViewById(R.id.radiobutton1);
                        if(radio1.isChecked()) {
                            months = Integer.valueOf(radio1.getText().toString())*12;
                        }
                        break;
                    case R.id.radiobutton2:
                        RadioButton radio2=(RadioButton)findViewById(R.id.radiobutton2);
                        if(radio2.isChecked()) {
                            months = Integer.valueOf(radio2.getText().toString())*12;
                        }
                        break;
                    case R.id.radiobutton3:
                        RadioButton radio3=(RadioButton)findViewById(R.id.radiobutton3);
                        if(radio3.isChecked()) {
                            months = Integer.valueOf(radio3.getText().toString())*12;
                        }
                        break;


                }


                CheckBox checkbox=(CheckBox) findViewById(R.id.check1);
                if(checkbox.isChecked()){
                    tax=(0.001*amount);
                }
                TextView textview3=(TextView)findViewById(R.id.textview3);
                TextView payment=(TextView)findViewById(R.id.monthlypayment);


                if(amount!=0) {
                    float m = interest / 1200;
                    double factor = m / (1 - Math.pow((1 + m), -months));
                    monthlypayment = (amount * factor) + tax;
                    textview3.setTextColor(getResources().getColor(R.color.yellow));
                    textview3.setText("Your mothly payment is:");
                    payment.setText(String.valueOf(monthlypayment));
                }else {
                    textview3.setTextColor(getResources().getColor(R.color.red));
                    textview3.setText("Enter a valid amount");
                    payment.setText("");
                    //vib.vibrate(50);
                }

            }
        });

    }


}

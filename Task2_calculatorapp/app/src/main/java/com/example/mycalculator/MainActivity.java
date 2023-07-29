package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resulttv,solutiontv;
    MaterialButton buttonC,buttonBrackopen,getButtonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv = findViewById(R.id.result_tv);
        solutiontv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackopen,R.id.button_open_bracket);
        assignId(getButtonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonDot,R.id.button_dot);
    }
    void assignId(MaterialButton btn,int id){
     btn = findViewById(id);
     btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String datatocalculate = solutiontv.getText().toString();

        if (buttonText.equals("AC")) {
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutiontv.setText(resulttv.getText());
            return;
        }
        if (buttonText.equals("c")) {
            datatocalculate = datatocalculate.substring(0, datatocalculate.length() - 1);
        } else {
            datatocalculate = datatocalculate + buttonText;
        }
        solutiontv.setText(datatocalculate);
        String FinalResult = getResult(datatocalculate);

        if (!FinalResult.equals("ERR")){
            resulttv.setText(FinalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
          String FinalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if (FinalResult.endsWith(".0")){
               FinalResult = FinalResult.replace(".0","");
           }
            return  FinalResult;
        }catch (Exception e) {
            return "ERR";
        }
    }
    }

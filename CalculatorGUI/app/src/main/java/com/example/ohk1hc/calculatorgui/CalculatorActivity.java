package com.example.ohk1hc.calculatorgui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Bind(R.id.spinnerOperator)
    Spinner spinnerOperator;

    @Bind(R.id.calcBtn)
    Button calcBtn;

    @Bind(R.id.editTextNumber1)
    EditText editTextNumber1;

    @Bind(R.id.editTextNumber2)
    EditText editTextNumber2;

    @Bind(R.id.editTextResult)
    EditText editTextResult;

    ArrayAdapter<CharSequence> operatorsAdapter;

    int currentOperator = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        operatorsAdapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        operatorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperator.setAdapter(operatorsAdapter);
        spinnerOperator.setOnItemSelectedListener(this);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float number1 = Float.parseFloat(editTextNumber1.getText().toString());
                    float number2 = Float.parseFloat(editTextNumber2.getText().toString());

                    editTextResult.setText(String.format("%f", Calculator.calculate(number1, number2, currentOperator)));
                } catch (ArithmeticException e) {
                    editTextResult.setText("Cannot divide by Zero!");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String operator = (String) operatorsAdapter.getItem(position);
        if (operator.equals("+"))
            currentOperator = Calculator.SUMMATION;
        else if (operator.equals("-"))
            currentOperator = Calculator.SUBTRACTION;
        else if (operator.equals("*"))
            currentOperator = Calculator.MULTIPLICATION;
        else if (operator.equals("/"))
            currentOperator = Calculator.DIVISION;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

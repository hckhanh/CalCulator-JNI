package com.example.ohk1hc.calculatorgui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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

    @Bind(R.id.checkBoxAutoCalculate)
    CheckBox checkBoxAutoCalculate;

    ArrayAdapter<CharSequence> operatorsAdapter;

    int currentOperator = -1;

    CalculatorTextWatcher calculatorTextWatcher = new CalculatorTextWatcher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initSpinnerAdapter();

        calcBtn.setOnClickListener(this);

        calculatorTextWatcher.setOnReceiveResultListener(new OnReceiveResultListener() {
            @Override
            public void calculate() {
                onClick(calcBtn);
            }
        });

        checkBoxAutoCalculate.setOnCheckedChangeListener(this);
    }

    private void initSpinnerAdapter() {
        operatorsAdapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        operatorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperator.setAdapter(operatorsAdapter);
        spinnerOperator.setOnItemSelectedListener(this);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calcBtn) {
            try {
                String strNumber1 = editTextNumber1.getText().toString();
                String strNumber2 = editTextNumber2.getText().toString();

                if (strNumber1.isEmpty() && strNumber2.isEmpty())
                    return;

                strNumber1 = strNumber1.isEmpty() ? "0" : strNumber1;
                strNumber2 = strNumber2.isEmpty() ? "0" : strNumber2;

                editTextResult.setText(
                        String.format(
                                "%f",
                                Calculator.calculate(
                                        Float.parseFloat(strNumber1),
                                        Float.parseFloat(strNumber2),
                                        currentOperator
                                )
                        )
                );

            } catch (ArithmeticException e) {
                editTextResult.setText("Cannot divide by Zero!");
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        calcBtn.setEnabled(!isChecked);

        if (isChecked) {
            editTextNumber1.addTextChangedListener(calculatorTextWatcher);
            editTextNumber2.addTextChangedListener(calculatorTextWatcher);
            onClick(calcBtn);
        } else {
            editTextNumber1.removeTextChangedListener(calculatorTextWatcher);
            editTextNumber2.removeTextChangedListener(calculatorTextWatcher);
        }
    }

}

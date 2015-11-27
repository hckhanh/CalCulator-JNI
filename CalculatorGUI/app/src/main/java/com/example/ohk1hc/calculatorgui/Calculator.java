package com.example.ohk1hc.calculatorgui;

public class Calculator {

    public final static int SUMMATION = 0;
    public final static int SUBTRACTION = 1;
    public final static int MULTIPLICATION = 2;
    public final static int DIVISION = 3;

    static {
        System.loadLibrary("Calculator");
    }

    public static native float calculate(float number1, float number2, int operator);

}

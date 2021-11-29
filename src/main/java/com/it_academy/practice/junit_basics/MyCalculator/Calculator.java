package com.it_academy.practice.junit_basics.MyCalculator;

public class Calculator {

    private int a;
    private int b;

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public Calculator() {

    }



    public int getA() { return a;}

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public float calculate(char operation) {
        switch (operation) {
            default:
                return 0 ;

            case '-': {
                return a - b;
            }
            case '+': {
                return a + b;
            }
            case '/': {
                float result = (float) a / b;
            if (Float.isInfinite(result)){
                System.out.println("На ноль делить нельзя");
                return a;
            }

            else if (Float.isNaN(result)){
                System.out.println("На ноль делить нельзя");
                return a;
            }

            else {return result;}
            }


            case '*': {
                return a * b;
            }
            case '^': {
                return (float) Math.pow(a,b);
            }
            case '@': {
                return (float) Math.pow(a,(float)1/b);
            }
       }

    }

    int myCalculateAdd(int count, int... numbers){

        if (count < 0 ){
            System.out.println("Count  should be greater then 0." + " count =" + count);
        }

        final int length = count != 0 ? count : numbers.length;
        int num = 0;

        for (int i = 0; i < length; i++) {
            num += numbers[i];
        }
    return num;}


}

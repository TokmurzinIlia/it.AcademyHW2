package com.it_academy.practice.junit_basics.MyCalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.next());
        int n2 = Integer.parseInt(sc.next());

        Calculator calculator = new Calculator(n1, n2);

        calculator.calculate('/');
    }
}

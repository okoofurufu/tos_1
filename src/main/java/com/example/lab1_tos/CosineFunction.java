package com.example.lab1_tos;

public class CosineFunction {

    public static double cosSeries(double x) {
        double result = 0.0;
        int n = 10;

        for (int i = 0; i < n; i++) {
            double term = Math.pow(-1, i) * Math.pow(x, 2 * i) / factorial(2 * i);
            result += term;
        }

        return result;
    }

    private static double factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            double result = 1;
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
            return result;
        }
    }
}
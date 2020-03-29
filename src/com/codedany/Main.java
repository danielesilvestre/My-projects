package com.codedany;


import javax.swing.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float value = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) readNumber("Period (Years): ", 1, 30);
        float monthlyInterest = getMonthlyInterest(value);
        short numberOfPayments =  getNumberOfPayments(years);

        System.out.print("\nMORTGAGE: \n" + "---------\n" + "Monthly Payments: ");
        double mortgage = calculateMortgage(principal,monthlyInterest, numberOfPayments);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println(mortgageFormatted);

        System.out.println("\nPAYMENT SCHEDULE\n" + "-----------");
        calculateRemaining(principal,monthlyInterest, numberOfPayments);

    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static float getMonthlyInterest(float value) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        return value / PERCENT / MONTHS_IN_YEAR;
    }

    public static short getNumberOfPayments(byte years) {
        final byte MONTHS_IN_YEAR = 12;

        return (short)(years * MONTHS_IN_YEAR);
    }

    public static double calculateMortgage(
            int principal,
            float monthlyInterest,
            short numberOfPayments) {

        return  principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }

    public static void calculateRemaining(
            int principal,
            float monthlyInterest,
            short numberOfPayments){
        //B = L[(1 + c)n - (1 + c)p]/[(1 + c)n - 1]
        for (short i = 1; i <= numberOfPayments; i++) {
            double remaining = getRemaining(principal, monthlyInterest, numberOfPayments, i);
            String remainingFormat = NumberFormat.getCurrencyInstance().format(remaining);
            System.out.println(remainingFormat);
        }

    }

    private static double getRemaining(int principal, float monthlyInterest, short numberOfPayments, short numberOfPaymentsDone) {
        return principal
                        * ((Math.pow(1 + monthlyInterest, numberOfPayments))
                        - (Math.pow(1 + monthlyInterest, numberOfPaymentsDone)))
                        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }
}





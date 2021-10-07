package com.example.application.model;


import com.example.application.views.клиент.ClientView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LoanCalculator {


    public void loanCalculator(ClientView.CreditAmount creditAmount, ClientView.CreditTerm creditTerm, double entryInterestRate) {

        LocalDate date = LocalDate.now();

        double term = creditTerm.creditTerm();
        double amount = creditAmount.creditAmount();
        double calculatedInterestRate = entryInterestRate / 100 / 12;


        double monthlyPayment = amount * (calculatedInterestRate + (calculatedInterestRate / (Math.pow(1 + calculatedInterestRate, term) - 1)));
        double percent = amount * calculatedInterestRate;
        double mainDebt = monthlyPayment - percent;
        amount = (amount - mainDebt);

        for (int i = 1; i <= term; i++, percent = amount * calculatedInterestRate, mainDebt = monthlyPayment - percent, amount = (amount - mainDebt), date = date.plusMonths(1)) {

            String mainDebtFormat = String.format("%.2f", mainDebt);
            String remainder = String.format("%.2f", creditAmount);
            String percentFormat = String.format("%.2f", percent);
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM y");
            date.format(f);
            System.out.println(i);
            System.out.println(date);
            System.out.println(percentFormat);
            System.out.println(mainDebtFormat);
            System.out.println(remainder);

        }
        String monthlyPaymentFormat = String.format("%.2f", monthlyPayment);
        System.out.println(monthlyPaymentFormat);


    }
}


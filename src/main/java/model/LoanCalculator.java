package model;


import com.example.application.views.клиент.ClientView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LoanCalculator {


    public void LoanCalculator(ClientView.Credit_Amount credit_Amount, ClientView.Credit_Term credit_Term, double entry_Interest_Rate) {

        LocalDate date = LocalDate.now();

        Double term = credit_Term.credit_Term();
        Double amount = credit_Amount.credit_Amount();
        double calculated_Interest_Rate = entry_Interest_Rate / 100 / 12;


        double monthly_Payment = amount * (calculated_Interest_Rate + (calculated_Interest_Rate / (Math.pow(1 + calculated_Interest_Rate, term) - 1)));
        double percent = amount * calculated_Interest_Rate;
        double mainDebt = monthly_Payment - percent;
        amount = (amount - mainDebt);

        for (int i = 1; i <= term; i++, percent = amount * calculated_Interest_Rate, mainDebt = monthly_Payment - percent, amount = (amount - mainDebt), date = date.plusMonths(1)) {

            String mainDebt1 = String.format("%.2f", mainDebt);
            String credit_Amount1 = String.format("%.2f", credit_Amount);
            String percent1 = String.format("%.2f", percent);
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM y");
            date.format(f);
            System.out.println(i);
            System.out.println(date);
            System.out.println(percent1);
            System.out.println(mainDebt1);
            System.out.println(credit_Amount1);

        }
        String monthly_Payment1 = String.format("%.2f", monthly_Payment);
        System.out.println(monthly_Payment1);


    }
}


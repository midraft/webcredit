package model;

import com.example.application.views.клиент.ClientView;

public class LoanCalculator {





    void monthly_Payment( ClientView.Credit_Amount creditAmount, ClientView.credit_Term credit_term, double interest_rate, int day_month){

        double monthly_Payment = creditAmount.credit_Amount() *(interest_rate/(1-Math.pow(1+interest_rate,1-credit_term.creditMonth())));
        for (int i = 0; i < credit_term.creditMonth(); i++) {

            if (day_month == 30) {

                double interest = creditAmount.credit_Amount() * interest_rate *(30/365);
                double mainDebt = monthly_Payment - interest;
                double leftToPay = creditAmount.credit_Amount() - mainDebt;

            }
            if (day_month == 31) {

                double interest = creditAmount.credit_Amount() * interest_rate *(31/365);
                double mainDebt = monthly_Payment - interest;
                double leftToPay = creditAmount.credit_Amount() - mainDebt;
            }
            if (day_month == 28) {

                double interest = creditAmount.credit_Amount() * interest_rate *(28/365);
                double mainDebt = monthly_Payment - interest;
                double leftToPay = creditAmount.credit_Amount() - mainDebt;
            }

        }


    }

}

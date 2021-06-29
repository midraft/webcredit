package model;

//public class LoanCalculator {
//
//
//
//
//
//   void monthly_Payment(ClientView.Credit_Amount creditAmount, ClientView.creditTerm creditTerm, double interest_rate, String day_month){
//        LocalDate dayMonth = LocalDate.now();
//        double monthly_Payment = creditAmount.credit_Amount() *(interest_rate/(1-Math.pow(1+interest_rate,1-creditTerm.creditMonth())));
//        for (int i = 1; i < credit_term.creditMonth(); i++) {
//
//            if (day_month == 30) {
//
//                double interest = creditAmount.credit_Amount() * interest_rate *(30/365);
//                double mainDebt = monthly_Payment - interest;
//                double leftToPay = creditAmount.credit_Amount() - mainDebt;
//                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
//                dayMonth.format(f);
//                dayMonth.plusMonths(1);
//
//            }
//            if (day_month == 31) {
//
//                double interest = creditAmount.credit_Amount() * interest_rate *(31/365);
//                double mainDebt = monthly_Payment - interest;
//                double leftToPay = creditAmount.credit_Amount() - mainDebt;
//            }
//            if (day_month == 28) {
//
//                double interest = creditAmount.credit_Amount() * interest_rate *(28/365);
//                double mainDebt = monthly_Payment - interest;
//                double leftToPay = creditAmount.credit_Amount() - mainDebt;
//            }
//            if (day_month == 29) {
//
//                double interest = creditAmount.credit_Amount() * interest_rate *(28/366);
//                double mainDebt = monthly_Payment - interest;
//                double leftToPay = creditAmount.credit_Amount() - mainDebt;
//            }
//
//        }
//
//
//    }
//
//}

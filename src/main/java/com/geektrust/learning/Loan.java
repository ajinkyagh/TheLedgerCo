package com.geektrust.learning;

public class Loan {

    protected final String principal, years, rate;
    private final float totalAmountToPayPerMonth;
    private final float periodInMonths;
    private final float totalAmountTORepay;

    public Loan(String principal, String years, String rate, float totalAmountToPayPerMonth, float periodInMonths, float totalAmountToRepay) {
        this.principal = principal;
        this.years = years;
        this.rate = rate;
        this.totalAmountToPayPerMonth = totalAmountToPayPerMonth;
        this.periodInMonths = periodInMonths;
        this.totalAmountTORepay = totalAmountToRepay;
    }

    public float getTotalAmountToPayPerMonth() {
        return totalAmountToPayPerMonth;
    }

    public float getPeriodInMonths() {
        return periodInMonths;
    }

    public float getTotalAmountTORepay() {
        return totalAmountTORepay;
    }

    public PaymentReceipt payEMIWithLumpSum(float lumpSum, float emiNumber) {
        return createPaymentReceipt(lumpSum, emiNumber);
    }

    public PaymentReceipt payEMI(float emiNo) {
        return createPaymentReceipt(0, emiNo);
    }

    private PaymentReceipt createPaymentReceipt(float lumpsumAmount, float emiNo){
        float totalAmountPaidSoFar = lumpsumAmount + (totalAmountToPayPerMonth * emiNo);
        int emisLeft = (int) (getPeriodInMonths() - emiNo);
        return new PaymentReceipt(totalAmountPaidSoFar, emiNo, emisLeft);
    }
}

package com.geektrust.learning;

public class BorrowerDetails {

    protected final String principal, years, rate;
    private final float totalAmountToPayPerMonth;
    private final float periodInMonths;
    private final float totalAmountTORepay;

    public BorrowerDetails(String principal, String years, String rate, float totalAmountToPayPerMonth, float periodInMonths, float totalAmountToRepay) {
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

    public PaymentDetails payEMIWithLumpSum(float lumpSum, float emiNumber) {
        return createPaymentReceipt(lumpSum, emiNumber);
    }

    public PaymentDetails payEMI(float emiNo) {
        return createPaymentReceipt(0, emiNo);
    }

    private PaymentDetails createPaymentReceipt(float lumpsumAmount, float emiNo){
        float totalAmountPaidSoFar = lumpsumAmount + (totalAmountToPayPerMonth * emiNo);
        int emisLeft = (int) (getPeriodInMonths() - emiNo);
        return new PaymentDetails(totalAmountPaidSoFar, emiNo, emisLeft);
    }
}

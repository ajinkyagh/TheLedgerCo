package com.geektrust.learning;

import com.geektrust.learning.paymentCalculators.PaymentReceipt;

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

    public PaymentDetails calculatePayment(float lumpSum, float emiNumber) {
       float totalAmountWithLumpSum = lumpSum + (totalAmountToPayPerMonth * emiNumber);
       return new PaymentDetails(totalAmountWithLumpSum, emiNumber);
    }

    public PaymentReceipt makePayment(float emiNo) {

        float totalAmountPaidSoFar = emiNo * getTotalAmountToPayPerMonth();
        int emisLeft = (int) (getPeriodInMonths() - emiNo);
      return  new PaymentReceipt(totalAmountPaidSoFar, emisLeft);
    }
}

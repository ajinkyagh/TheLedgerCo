package com.geektrust.learning.paymentCalculators;

public class PaymentReceipt {


    private final float totalAmountPaidSoFar;
    private final int emisLeft;

    public PaymentReceipt(float totalAmountPaidSoFar, int emisLeft) {

        this.totalAmountPaidSoFar = totalAmountPaidSoFar;
        this.emisLeft = emisLeft;
    }

    public float getTotalAmountPaidSoFar() {
        return totalAmountPaidSoFar;
    }

    public int getEmisLeft() {
        return emisLeft;
    }
}

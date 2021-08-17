package com.geektrust.learning;

public class PaymentDetails {
    private final float emiNo;
    private final float LumpSum;

    public PaymentDetails(float totalAmountWithLumpSum, float emiNo) {
        this.LumpSum = totalAmountWithLumpSum;
        this.emiNo = emiNo;
    }

    public float getEmiNo() {
        return emiNo;
    }

    public float getLumpSum() {
        return LumpSum;
    }
}

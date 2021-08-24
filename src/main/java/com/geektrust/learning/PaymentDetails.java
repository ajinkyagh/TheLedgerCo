package com.geektrust.learning;

public class PaymentDetails {
    private final float emiNo;
    private final float totalAmountWithLumpSum;

    public PaymentDetails(float totalAmountWithLumpSum, float emiNo) {
        this.totalAmountWithLumpSum = totalAmountWithLumpSum;
        this.emiNo = emiNo;
    }

    public float getEmiNo() {
        return emiNo;
    }

    public float getTotalAmountWithLumpSum() {
        return totalAmountWithLumpSum;
    }
}

package com.geektrust.learning;

public class EMIPaymentReceipt {
    private final float emiNo;
    private final float totalAmountWithLumpSum;
    private final int emiLeft;

    public EMIPaymentReceipt(float totalAmountWithLumpSum, float emiNo, int emiLeft) {
        this.totalAmountWithLumpSum = totalAmountWithLumpSum;
        this.emiNo = emiNo;
        this.emiLeft = emiLeft;
    }

    public float getEmiNo() {
        return emiNo;
    }

    public float getTotalAmountWithLumpSum() {
        return totalAmountWithLumpSum;
    }

    public int getEmiLeft() {
        return emiLeft;
    }
}

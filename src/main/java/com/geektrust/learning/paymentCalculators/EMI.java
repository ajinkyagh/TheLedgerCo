package com.geektrust.learning.paymentCalculators;

public class EMI {
    float totalAmountToRepay,periodInMonths,totalAmountToRepayPerMonth;

    public EMI(float principal, float timePeriod, float rate) {

        float interest = principal * timePeriod * rate / 100;
        this.totalAmountToRepay = interest + principal;
        this.periodInMonths = timePeriod * 12;
        this.totalAmountToRepayPerMonth= (float) Math.ceil(totalAmountToRepay / periodInMonths);
    }

    public float getTotalAmountToRepay() {
        return totalAmountToRepay;
    }

    public float getPeriodInMonths() {
        return periodInMonths;
    }

    public float getTotalAmountToRepayPerMonth() {
        return totalAmountToRepayPerMonth;
    }
}

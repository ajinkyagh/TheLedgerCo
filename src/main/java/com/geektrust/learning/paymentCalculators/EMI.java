package com.geektrust.learning.paymentCalculators;

public class EMI {
    float totalAmountToRepay,periodInMonths,totalAmountToRepayPerMonth;

    public EMI(float totalAmountToRepay, float periodInMonths, float totalAmountToRepayPerMonth) {
        this.totalAmountToRepay = totalAmountToRepay;
        this.periodInMonths = periodInMonths;
        this.totalAmountToRepayPerMonth = totalAmountToRepayPerMonth;
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

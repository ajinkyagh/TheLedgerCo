package com.geektrust.learning.paymentCalculators;

public class EMICalculator {

    private final float principal;
    private final float timePeriod;
    private final float rate;
    private float interest;

    public EMICalculator(float principal, float timePeriod, float rate) {

        this.principal = principal;
        this.timePeriod = timePeriod;
        this.rate = rate;
    }

    public EMI calculate(){

        interest = principal * timePeriod * rate / 100;
        float totalAmountToRepay = interest + principal;
        float periodInMonths = timePeriod * 12;
        float totalAmountToRepayPerMonth= (float) Math.ceil(totalAmountToRepay / periodInMonths);
        return new EMI(totalAmountToRepay,periodInMonths,totalAmountToRepayPerMonth);
    }
}

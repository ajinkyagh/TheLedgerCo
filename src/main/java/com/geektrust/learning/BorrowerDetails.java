package com.geektrust.learning;

public class BorrowerDetails {

    protected final String principal, years, rate;
    private final float amountPerMonth;
    private final float periodInMonths;
    private final float amount;

    public BorrowerDetails(String principal, String years, String rate, float amountPerMonth, float periodInMonths, float amount) {
        this.principal = principal;
        this.years = years;
        this.rate = rate;
        this.amountPerMonth = amountPerMonth;
        this.periodInMonths = periodInMonths;
        this.amount = amount;
    }

    public float getAmountPerMonth() {
        return amountPerMonth;
    }

    public float getPeriodInMonths() {
        return periodInMonths;
    }

    public float getAmount() {
        return amount;
    }



}

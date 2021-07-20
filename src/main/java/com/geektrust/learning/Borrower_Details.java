package com.geektrust.learning;

public class Borrower_Details {

    private final String principal,years,rate;
    private final float amountPerMonth;
    private final float periodInMonths;
    private final float amount;


    public Borrower_Details(String s2, String s3, String s4,float s5, float periodInMonths, float amount) {
        this.principal=s2;
        this.years=s3;
        this.rate=s4;
        this.amountPerMonth=s5;
        this.periodInMonths=periodInMonths;
        this.amount=amount;
    }



    public String getPrincipal() {
        return principal;
    }

    public String getYears() {
        return years;
    }

    public String getRate() {
        return rate;
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

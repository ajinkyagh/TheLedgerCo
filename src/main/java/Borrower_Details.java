public class Borrower_Details {

    private final String principal,years,rate;
    private final float amountPerMonth;



    public Borrower_Details( String s2, String s3, String s4,float s5) {
        this.principal=s2;
        this.years=s3;
        this.rate=s4;
        this.amountPerMonth=s5;
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
}

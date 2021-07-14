public class CarryOperations {

    private final Borrower_Details borrower_details;
    double p,n,r,interest,amount;

    public CarryOperations(Borrower_Details borrower_details) {
        this.borrower_details=borrower_details;
    }

    public void calculateTotalAmount(){
         p=Double.parseDouble(borrower_details.getPrincipal());
         n=Double.parseDouble(borrower_details.getYears());
         r=Double.parseDouble(borrower_details.getRate());
         interest=(p*n*r)/100;
         amount=interest+p;
    }

    public void totalAmountPaid(String bankName, String eName, String emiNo){
        Double emiNoToDouble=Double.parseDouble(emiNo);
        Double totalInstallments=12.0*n;
        Double emiPerMonth=amount/totalInstallments;
        Double amountPaid=emiPerMonth*emiNoToDouble;
        Double noOfEmiLeft=totalInstallments-emiNoToDouble;
        System.out.println(bankName+" "+eName+" "+amountPaid+" "+noOfEmiLeft);

    }

    public void payment(String s, String s1){
        Double totalInstallments=12.0*n;
        double emiPerMonth=amount/totalInstallments;
        double total=emiPerMonth*Double.parseDouble(s1);
        double TotalAmountPaid=total+Double.parseDouble(s);
    }
}

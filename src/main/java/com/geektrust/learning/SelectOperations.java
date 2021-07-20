package com.geektrust.learning;

public class SelectOperations {
    CarryOperations carryOperations=new CarryOperations();

    public void bank_operations(String line)
    {
        String[] splitInput= line.split("\\s");
            switch (splitInput[0]) {
                case "LOAN" : carryOperations.takeLoan(splitInput);break;
                case "BALANCE" : carryOperations.showBalance(splitInput);break;
                case "PAYMENT" : carryOperations.makeLumpSumPayment(splitInput);break;
                default : throw new IllegalStateException("Unexpected value: " + splitInput[0]);
            }
    }

}

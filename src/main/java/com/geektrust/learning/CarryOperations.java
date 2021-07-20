package com.geektrust.learning;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CarryOperations {


    float interest,amount,periodInMonths,amountPerMonth,totalAmountWithLuumpsum,no,output;
    HashMap<String,Borrower_Details> borrower_detailsHashMap =new HashMap<>();
    HashMap<String,Payment_Details> payment_detailsHashMap=new HashMap<>();
    Boolean flag=true;
    OutputToConsole outputToConsole=new OutputToConsole();



    public void takeLoan(String[] splitInput){
        interest=Float.parseFloat(splitInput[3])*Float.parseFloat(splitInput[4])*Float.parseFloat(splitInput[5])/100;
        amount=interest+Float.parseFloat(splitInput[3]);
        periodInMonths=Float.parseFloat(splitInput[4])*12;
        amountPerMonth= (float) Math.ceil(amount/periodInMonths);
        borrower_detailsHashMap.put(splitInput[2],new Borrower_Details(splitInput[3],splitInput[4],splitInput[5],amountPerMonth,periodInMonths,amount));
    }

    public void makeLumpSumPayment(String[] splitInput){
        borrower_detailsHashMap.forEach((key, value) -> {
            if (key.contentEquals(splitInput[2])) {
                totalAmountWithLuumpsum = Float.parseFloat(splitInput[3]) + (value.getAmountPerMonth() * Float.parseFloat(splitInput[4]));
                no=Float.parseFloat(splitInput[4]);
                flag=false;
                payment_detailsHashMap.put(splitInput[2],new Payment_Details(totalAmountWithLuumpsum,no));
            }
        });

    }

    public void showBalance(String[] splitInput){
        output=0;
        AtomicInteger j= new AtomicInteger(1);

        if (borrower_detailsHashMap.containsKey(splitInput[2]) && payment_detailsHashMap.containsKey(splitInput[2]) )
        {
            Payment_Details key1 = payment_detailsHashMap.get(splitInput[2]);
            if (Float.parseFloat(splitInput[3])>key1.getNo()){
                borrower_detailsHashMap.forEach((key, value) -> {
                    if (key.contentEquals(splitInput[2])) {
                        for (int i = (int) key1.getNo(); i < Integer.parseInt(splitInput[3]); i++) {
                            output =  key1.getLumpsum() + value.getAmountPerMonth()* j.get();
                            j.getAndIncrement();
                        }
                        float val= value.getAmount()-output;
                        float emiLeft= (float) Math.ceil(val /value.getAmountPerMonth());
                        outputToConsole.writeToConsole(splitInput,(int) output,(int) emiLeft);
                    }

                });
            }
            else if (Float.parseFloat(splitInput[3])>=key1.getNo()){
                borrower_detailsHashMap.forEach((key, value) -> {

                    if (key.contentEquals(splitInput[2])) {
                        output = key1.getLumpsum();
                        float val= value.getAmount()-output;
                        float emiLeft= (float) Math.ceil(val /value.getAmountPerMonth());
                        outputToConsole.writeToConsole(splitInput,(int) output,(int) emiLeft);
                    }
                });
            }
            else {
                borrower_detailsHashMap.forEach((key, value) -> {
                    if (key.contentEquals(splitInput[2])) {
                        float emiLeft=value.getPeriodInMonths()-Float.parseFloat(splitInput[3]);
                        double output = Double.parseDouble(splitInput[3])*value.getAmountPerMonth();
                        outputToConsole.writeToConsole(splitInput,(int) output,(int) emiLeft);;
                    }
                });
            }
        }
        else {
            borrower_detailsHashMap.forEach((key, value) -> {
                if (key.contentEquals(splitInput[2])) {
                    float emiLeft = value.getPeriodInMonths() - Float.parseFloat(splitInput[3]);
                    double output = Double.parseDouble(splitInput[3]) * value.getAmountPerMonth();
                    outputToConsole.writeToConsole(splitInput,(int) output,(int) emiLeft);
                }
            });
        }

    }
}

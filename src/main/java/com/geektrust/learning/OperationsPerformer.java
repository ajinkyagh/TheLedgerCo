package com.geektrust.learning;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OperationsPerformer {
    float interest, amount, periodInMonths, amountPerMonth, totalAmountWithLumpSum, emiNo, output;
    HashMap<String, BorrowerDetails> borrowerDetailsHashMap = new HashMap<>();
    HashMap<String, PaymentDetails> paymentDetailsHashMap = new HashMap<>();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    public void takeLoan(String[] splitInput) {
        interest = Float.parseFloat(splitInput[3]) * Float.parseFloat(splitInput[4]) * Float.parseFloat(splitInput[5]) / 100;
        amount = interest + Float.parseFloat(splitInput[3]);
        periodInMonths = Float.parseFloat(splitInput[4]) * 12;
        amountPerMonth = (float) Math.ceil(amount / periodInMonths);
        String bankDetails = splitInput[1] + splitInput[2];
        borrowerDetailsHashMap.put(bankDetails, new BorrowerDetails(splitInput[3], splitInput[4], splitInput[5], amountPerMonth, periodInMonths, amount));
    }

    public void makeLumpSumPayment(String[] splitInput) {
        String bankDetails = splitInput[1] + splitInput[2];
        borrowerDetailsHashMap.forEach((key, value) -> {
            if (key.contentEquals(bankDetails)) {
                totalAmountWithLumpSum = Float.parseFloat(splitInput[3]) + (value.getAmountPerMonth() * Float.parseFloat(splitInput[4]));
                emiNo = Float.parseFloat(splitInput[4]);
                paymentDetailsHashMap.put(bankDetails, new PaymentDetails(totalAmountWithLumpSum, emiNo));
            }
        });
    }

    public void showBalance(String[] splitInput) {
        output = 0;
        AtomicInteger count = new AtomicInteger(1);
        String bankDetails = splitInput[1] + splitInput[2];
        if (borrowerDetailsHashMap.containsKey(bankDetails) && paymentDetailsHashMap.containsKey(bankDetails)) {
            PaymentDetails paymentDetails = paymentDetailsHashMap.get(bankDetails);
            if (Float.parseFloat(splitInput[3]) > paymentDetails.getEmiNo()) {
                borrowerDetailsHashMap.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        for (int i = (int) paymentDetails.getEmiNo(); i < Integer.parseInt(splitInput[3]); i++) {
                            output = paymentDetails.getLumpSum() + value.getAmountPerMonth() * count.get();
                            count.getAndIncrement();
                        }
                        float val = value.getAmount() - output;
                        float emiLeft = (float) Math.ceil(val / value.getAmountPerMonth());
                        if (output>value.getAmount()){
                            consoleWriter.writeToConsole(splitInput, (int) amount, (int) emiLeft);
                        }
                        else {
                            consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                        }
                    }
                });
            } else if (Float.parseFloat(splitInput[3]) >= paymentDetails.getEmiNo()) {
                borrowerDetailsHashMap.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        output = paymentDetails.getLumpSum();
                        float val = value.getAmount() - output;
                        float emiLeft = (float) Math.ceil(val / value.getAmountPerMonth());
                        consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                    }
                });
            } else {
                borrowerDetailsHashMap.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        float emiLeft = value.getPeriodInMonths() - Float.parseFloat(splitInput[3]);
                        float output = Float.parseFloat(splitInput[3]) * value.getAmountPerMonth();
                        consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                    }
                });
            }
        } else {
            borrowerDetailsHashMap.forEach((key, value) -> {
                if (key.contentEquals(bankDetails)) {
                    float emiLeft = value.getPeriodInMonths() - Float.parseFloat(splitInput[3]);
                    float output = Float.parseFloat(splitInput[3]) * value.getAmountPerMonth();
                    consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                }
            });
        }
    }
}

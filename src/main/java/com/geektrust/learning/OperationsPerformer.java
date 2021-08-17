package com.geektrust.learning;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OperationsPerformer {
    float interest, amount, periodInMonths, amountPerMonth, totalAmountWithLumpSum, emiNo, output;
    HashMap<String, BorrowerDetails> borrowerDetails = new HashMap<>();
    HashMap<String, PaymentDetails> paymentDetails = new HashMap<>();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    public void takeLoan(String[] splitInput) {
        String bankDetails = splitInput[1] + splitInput[2];
        float principal=Float.parseFloat(splitInput[3]),timePeriod=Float.parseFloat(splitInput[4]),rate=Float.parseFloat(splitInput[5]);
        interest = principal*timePeriod*rate / 100;
        amount = interest + principal;
        periodInMonths = timePeriod * 12;
        amountPerMonth = (float) Math.ceil(amount / periodInMonths);
        borrowerDetails.put(bankDetails, new BorrowerDetails(Float.toString(principal), Float.toString(timePeriod), Float.toString(rate), amountPerMonth, periodInMonths, amount));
    }

    public void makeLumpSumPayment(String[] splitInput) {
        String bankDetails = splitInput[1] + splitInput[2];
        float lumpSum=Float.parseFloat(splitInput[3]);
        float emiNumber=Float.parseFloat(splitInput[4]);

        borrowerDetails.forEach((key, value) -> {
            if (key.contentEquals(bankDetails)) {
                totalAmountWithLumpSum = lumpSum + (value.getAmountPerMonth() * emiNumber);
                paymentDetails.put(bankDetails, new PaymentDetails(totalAmountWithLumpSum, emiNumber));
            }
        });
    }

    public void showBalance(String[] splitInput) {
        output = 0;
        AtomicInteger count = new AtomicInteger(1);
        String bankDetails = splitInput[1] + splitInput[2];
        if (borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails)) {
            PaymentDetails paymentDetails = this.paymentDetails.get(bankDetails);
            if (Float.parseFloat(splitInput[3]) > paymentDetails.getEmiNo()) {
                borrowerDetails.forEach((key, value) -> {
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
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        output = paymentDetails.getLumpSum();
                        float val = value.getAmount() - output;
                        float emiLeft = (float) Math.ceil(val / value.getAmountPerMonth());
                        consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                    }
                });
            } else {
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        float emiLeft = value.getPeriodInMonths() - Float.parseFloat(splitInput[3]);
                        float output = Float.parseFloat(splitInput[3]) * value.getAmountPerMonth();
                        consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                    }
                });
            }
        } else {
            borrowerDetails.forEach((key, value) -> {
                if (key.contentEquals(bankDetails)) {
                    float emiLeft = value.getPeriodInMonths() - Float.parseFloat(splitInput[3]);
                    float output = Float.parseFloat(splitInput[3]) * value.getAmountPerMonth();
                    consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                }
            });
        }
    }
}

package com.geektrust.learning;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OperationsPerformer {
    float interest, amount, periodInMonths, amountPerMonth, totalAmountWithLumpSum, result;
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
        result = 0;
        AtomicInteger count = new AtomicInteger(1);
        String bankDetails = splitInput[1] + splitInput[2];
        float emiNo=Float.parseFloat(splitInput[3]);
        if (borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails)) {
            PaymentDetails paymentDetails = this.paymentDetails.get(bankDetails);
            if (emiNo > paymentDetails.getEmiNo()) {
                borrowerDetails.forEach((loanId, borrowerDetails) -> {
                    if (loanId.contentEquals(bankDetails)) {
                        for (int i = (int) paymentDetails.getEmiNo(); i < Integer.parseInt(splitInput[3]); i++) {
                            result = paymentDetails.getLumpSum() + borrowerDetails.getAmountPerMonth() * count.get();
                            count.getAndIncrement();
                        }
                        float val = borrowerDetails.getAmount() - result;
                        float emiLeft = (float) Math.ceil(val / borrowerDetails.getAmountPerMonth());
                        if (result >borrowerDetails.getAmount()){
                            consoleWriter.writeToConsole(splitInput, (int) amount, (int) emiLeft);
                        }
                        else {
                            consoleWriter.writeToConsole(splitInput, (int) result, (int) emiLeft);
                        }
                    }
                });
            } else if (emiNo >= paymentDetails.getEmiNo()) {
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        result = paymentDetails.getLumpSum();
                        float val = value.getAmount() - result;
                        float emiLeft = (float) Math.ceil(val / value.getAmountPerMonth());
                        consoleWriter.writeToConsole(splitInput, (int) result, (int) emiLeft);
                    }
                });
            } else {
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        float emiLeft = value.getPeriodInMonths() - emiNo;
                        float output = emiNo * value.getAmountPerMonth();
                        consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                    }
                });
            }
        } else {
            borrowerDetails.forEach((key, value) -> {
                if (key.contentEquals(bankDetails)) {
                    float emiLeft = value.getPeriodInMonths() - emiNo;
                    float output = emiNo * value.getAmountPerMonth();
                    consoleWriter.writeToConsole(splitInput, (int) output, (int) emiLeft);
                }
            });
        }
    }
}

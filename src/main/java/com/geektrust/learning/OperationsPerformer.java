package com.geektrust.learning;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OperationsPerformer {
    float interest, totalAmountToRepay, periodInMonths, totalAmountToPayPerMonth, totalAmountWithLumpSum, totalAmountPaidSoFar;
    HashMap<String, BorrowerDetails> borrowerDetails = new HashMap<>();
    HashMap<String, PaymentDetails> paymentDetails = new HashMap<>();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    public void takeLoan(String[] splitInput) {
        String bankName=splitInput[1],borrowerName=splitInput[2],bankDetails = bankName+borrowerName;
        float principal=Float.parseFloat(splitInput[3]),timePeriod=Float.parseFloat(splitInput[4]),rate=Float.parseFloat(splitInput[5]);
        interest = principal*timePeriod*rate / 100;
        totalAmountToRepay = interest + principal;
        periodInMonths = timePeriod * 12;
        totalAmountToPayPerMonth = (float) Math.ceil(totalAmountToRepay / periodInMonths);
        borrowerDetails.put(bankDetails, new BorrowerDetails(Float.toString(principal), Float.toString(timePeriod), Float.toString(rate), totalAmountToPayPerMonth, periodInMonths, totalAmountToRepay));
    }

    public void makeLumpSumPayment(String[] splitInput) {
        String bankName=splitInput[1],borrowerName=splitInput[2],bankDetails = bankName+borrowerName;;
        float lumpSum=Float.parseFloat(splitInput[3]),emiNumber=Float.parseFloat(splitInput[4]);

        borrowerDetails.forEach((loanId, borrowerDetails) -> {
            if (loanId.contentEquals(bankDetails)) {
                totalAmountWithLumpSum = lumpSum + (borrowerDetails.getTotalAmountToPayPerMonth() * emiNumber);
                paymentDetails.put(bankDetails, new PaymentDetails(totalAmountWithLumpSum, emiNumber));
            }
        });
    }

    public void showBalance(String[] splitInput) {
        totalAmountPaidSoFar = 0;
        AtomicInteger count = new AtomicInteger(1);
        String bankName=splitInput[1],borrowerName=splitInput[2],bankDetails = bankName+borrowerName;;
        float emiNo=Float.parseFloat(splitInput[3]);
        if (hasPaidInstallments(bankDetails)) {
            PaymentDetails paymentDetails = this.paymentDetails.get(bankDetails);
            if (isPaymentCalculationForFutureMonth(emiNo, paymentDetails.getEmiNo())) {
                borrowerDetails.forEach((loanId, borrowerDetails) -> {
                    if (loanId.contentEquals(bankDetails)) {
                        for (int i = (int) paymentDetails.getEmiNo(); i < Integer.parseInt(splitInput[3]); i++) {
                            totalAmountPaidSoFar = paymentDetails.getTotalAmountWithLumpSum() + borrowerDetails.getTotalAmountToPayPerMonth() * count.get();
                            count.getAndIncrement();
                        }
                        float totalAmountLeft = borrowerDetails.getTotalAmountTORepay() - totalAmountPaidSoFar;
                        float emiLeft = (float) Math.ceil(totalAmountLeft / borrowerDetails.getTotalAmountToPayPerMonth());
                        if (totalAmountPaidSoFar >borrowerDetails.getTotalAmountTORepay()){
                            consoleWriter.writeToConsole(splitInput, (int) totalAmountToRepay, (int) emiLeft);
                        }
                        else {
                            consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
                        }
                    }
                });
            } else if (isForCurrentMonth(emiNo, paymentDetails)) {
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        totalAmountPaidSoFar = paymentDetails.getTotalAmountWithLumpSum();
                        float totalAmountLeft = value.getTotalAmountTORepay() - totalAmountPaidSoFar;
                        float emiLeft = (float) Math.ceil(totalAmountLeft / value.getTotalAmountToPayPerMonth());
                        consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
                    }
                });
            } else {
                borrowerDetails.forEach((key, value) -> {
                    if (key.contentEquals(bankDetails)) {
                        float emiLeft = value.getPeriodInMonths() - emiNo;
                        float totalAmountPaidSoFar = emiNo * value.getTotalAmountToPayPerMonth();
                        consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
                    }
                });
            }
        } else {
            borrowerDetails.forEach((key, value) -> {
                if (key.contentEquals(bankDetails)) {
                    float emiLeft = value.getPeriodInMonths() - emiNo;
                    float totalAmountPaidSoFar = emiNo * value.getTotalAmountToPayPerMonth();
                    consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
                }
            });
        }
    }

    private boolean isForCurrentMonth(float emiNo, PaymentDetails paymentDetails) {
        return emiNo >= paymentDetails.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }

    private boolean hasPaidInstallments(String bankDetails) {
        return borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails);
    }
}

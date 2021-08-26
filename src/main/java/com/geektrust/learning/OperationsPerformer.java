package com.geektrust.learning;
import com.geektrust.learning.paymentCalculators.FuturePaymentCalculator;
import com.geektrust.learning.paymentCalculators.PaidInstallmentPaymentCalculator;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

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
        String bankName=splitInput[1],borrowerName=splitInput[2],bankDetails = bankName+borrowerName;;
        float emiNo=Float.parseFloat(splitInput[3]);
        BorrowerDetails currentBorrowerDetails = getCurrentBorrowerDetails(bankDetails);
        if (hasPaidInstallments(bankDetails)) {
            new PaidInstallmentPaymentCalculator(currentBorrowerDetails, this.paymentDetails.get(bankDetails),
                    emiNo, consoleWriter, splitInput).calculate();
        } else {
            float emiLeft = currentBorrowerDetails.getPeriodInMonths() - emiNo;
            float totalAmountPaidSoFar = emiNo * currentBorrowerDetails.getTotalAmountToPayPerMonth();
            consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
        }
    }

    private BorrowerDetails getCurrentBorrowerDetails(String bankDetails) {
        AtomicReference<BorrowerDetails> currentBorrower = new AtomicReference<>();
        borrowerDetails.forEach((loanId, currentBorrowerDetails) -> {
            if (loanId.contentEquals(bankDetails)) {
                currentBorrower.set(currentBorrowerDetails);
            }
        });
        return currentBorrower.get();
    }

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, PaymentDetails paymentDetails) {
        return emiNo >= paymentDetails.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }

    private boolean hasPaidInstallments(String bankDetails) {
        return borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails);
    }
}

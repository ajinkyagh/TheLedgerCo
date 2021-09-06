package com.geektrust.learning;

import com.geektrust.learning.paymentCalculators.EMI;
import com.geektrust.learning.paymentCalculators.EMICalculator;
import com.geektrust.learning.paymentCalculators.PaidInstallmentPaymentCalculator;
import com.geektrust.learning.paymentCalculators.PaymentReceipt;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class OperationsPerformer {
    float interest, totalAmountToRepay, periodInMonths, totalAmountToPayPerMonth, totalAmountWithLumpSum, totalAmountPaidSoFar;
    HashMap<String, BorrowerDetails> borrowerDetails = new HashMap<>();
    HashMap<String, PaymentDetails> paymentDetails = new HashMap<>();
    ConsoleWriter consoleWriter = new ConsoleWriter();

    public void takeLoan(String[] splitInput) {
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;
        float principal = Float.parseFloat(splitInput[3]), timePeriod = Float.parseFloat(splitInput[4]), rate = Float.parseFloat(splitInput[5]);
        EMI emi = new EMICalculator(principal, timePeriod, rate).calculate();
        borrowerDetails.put(bankDetails, new BorrowerDetails(Float.toString(principal), Float.toString(timePeriod), Float.toString(rate), emi.getTotalAmountToRepayPerMonth(), emi.getPeriodInMonths(), emi.getTotalAmountToRepay()));
    }


    public void makeLumpSumPayment(String[] splitInput) {
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;

        float lumpSum = Float.parseFloat(splitInput[3]), emiNumber = Float.parseFloat(splitInput[4]);

        borrowerDetails.forEach((loanId, borrowerDetails) -> {
            if (loanId.contentEquals(bankDetails)) {
                PaymentDetails value = borrowerDetails.calculatePayment(lumpSum, emiNumber);
                paymentDetails.put(bankDetails, value);
            }
        });
    }

    public void showBalance(String[] splitInput) {
        totalAmountPaidSoFar = 0;
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;
        ;
        float emiNo = Float.parseFloat(splitInput[3]);
        BorrowerDetails currentBorrowerDetails = getCurrentBorrowerDetails(bankDetails);
        int outputAmount, emisLeft;
        if (hasPaidInstallments(bankDetails)) {
            int finalEmiNumber = Integer.parseInt(splitInput[3]);
            float[] output = new PaidInstallmentPaymentCalculator(currentBorrowerDetails, this.paymentDetails.get(bankDetails),
                    emiNo, finalEmiNumber).calculate();
            outputAmount = (int) output[0];
            emisLeft = (int) output[1];
        } else {
            PaymentReceipt paymentReceipt = currentBorrowerDetails.makePayment(emiNo);
            outputAmount = (int) paymentReceipt.getTotalAmountPaidSoFar();
            emisLeft = paymentReceipt.getEmisLeft();
        }
        consoleWriter.writeToConsole(splitInput, outputAmount, emisLeft);
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

    private boolean hasPaidInstallments(String bankDetails) {
        return borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails);
    }
}

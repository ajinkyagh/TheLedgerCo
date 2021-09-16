package com.geektrust.learning;

import com.geektrust.learning.paymentCalculators.EMI;
import com.geektrust.learning.paymentCalculators.PaidInstallmentPaymentCalculator;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class OperationsPerformer {
    float totalAmountPaidSoFar;
    HashMap<String, Loan> borrowerDetails = new HashMap<>();
    HashMap<String, Receipt> paymentDetails = new HashMap<>();
    Console console = new Console();

    public void takeLoan(String[] splitInput) {
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;
        float principal = Float.parseFloat(splitInput[3]), timePeriod = Float.parseFloat(splitInput[4]), rate = Float.parseFloat(splitInput[5]);
        EMI emi = new EMI(principal, timePeriod, rate);
        borrowerDetails.put(bankDetails, new Loan(Float.toString(principal), Float.toString(timePeriod), Float.toString(rate), emi.getTotalAmountToRepayPerMonth(), emi.getPeriodInMonths(), emi.getTotalAmountToRepay()));
    }


    public void makeLumpSumPayment(String[] splitInput) {
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;

        float lumpSum = Float.parseFloat(splitInput[3]), emiNumber = Float.parseFloat(splitInput[4]);

        borrowerDetails.forEach((loanId, loan) -> {
            if (loanId.contentEquals(bankDetails)) {
                Receipt value = loan.payEMIWithLumpSum(lumpSum, emiNumber);
                paymentDetails.put(bankDetails, value);
            }
        });
    }

    public void showBalance(String[] splitInput) {
        totalAmountPaidSoFar = 0;
        String bankName = splitInput[1], borrowerName = splitInput[2], bankDetails = bankName + borrowerName;
        ;
        float emiNo = Float.parseFloat(splitInput[3]);
        Loan currentLoan = getCurrentBorrowerDetails(bankDetails);
        int outputAmount, emisLeft;
        if (hasPaidInstallments(bankDetails)) {
            int finalEmiNumber = Integer.parseInt(splitInput[3]);
            float[] output = new PaidInstallmentPaymentCalculator(currentLoan, this.paymentDetails.get(bankDetails),
                    emiNo, finalEmiNumber).calculate();
            outputAmount = (int) output[0];
            emisLeft = (int) output[1];
        } else {
            Receipt receipt = currentLoan.payEMI(emiNo);
            outputAmount = (int) receipt.getTotalAmountWithLumpSum();
            emisLeft =  receipt.getEmiLeft();
        }
        console.write(splitInput, outputAmount, emisLeft);
    }

    private Loan getCurrentBorrowerDetails(String bankDetails) {
        AtomicReference<Loan> currentBorrower = new AtomicReference<>();
        borrowerDetails.forEach((loanId, currentLoan) -> {
            if (loanId.contentEquals(bankDetails)) {
                currentBorrower.set(currentLoan);
            }
        });
        return currentBorrower.get();
    }

    private boolean hasPaidInstallments(String bankDetails) {
        return borrowerDetails.containsKey(bankDetails) && paymentDetails.containsKey(bankDetails);
    }
}

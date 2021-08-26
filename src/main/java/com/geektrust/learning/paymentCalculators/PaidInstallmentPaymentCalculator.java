package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.BorrowerDetails;
import com.geektrust.learning.ConsoleWriter;
import com.geektrust.learning.PaymentDetails;

public class PaidInstallmentPaymentCalculator {
    private final BorrowerDetails currentBorrowerDetails;
    private final PaymentDetails paymentDetails;
    private final float emiNo;
    private final ConsoleWriter consoleWriter;
    private final String[] splitInput;

    public PaidInstallmentPaymentCalculator(BorrowerDetails currentBorrowerDetails, PaymentDetails paymentDetails, float emiNo, ConsoleWriter consoleWriter, String[] splitInput) {
        this.currentBorrowerDetails = currentBorrowerDetails;
        this.paymentDetails = paymentDetails;
        this.emiNo = emiNo;
        this.consoleWriter = consoleWriter;
        this.splitInput = splitInput;
    }

    public void calculate() {
        if (isPaymentCalculationForFutureMonth(emiNo, paymentDetails.getEmiNo())) {
            new FuturePaymentCalculator(currentBorrowerDetails, paymentDetails, consoleWriter, splitInput).calculate();
        } else if (isPaymentCalculationForCurrentMonth(emiNo, paymentDetails)) {
            float totalAmountPaidSoFar = paymentDetails.getTotalAmountWithLumpSum();
            float totalAmountLeft = currentBorrowerDetails.getTotalAmountTORepay() - totalAmountPaidSoFar;
            float emiLeft = (float) Math.ceil(totalAmountLeft / currentBorrowerDetails.getTotalAmountToPayPerMonth());
            consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
        } else {
            float emiLeft = currentBorrowerDetails.getPeriodInMonths() - emiNo;
            float totalAmountPaidSoFar = emiNo * currentBorrowerDetails.getTotalAmountToPayPerMonth();
            consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
        }
    }

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, PaymentDetails paymentDetails) {
        return emiNo >= paymentDetails.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }
}

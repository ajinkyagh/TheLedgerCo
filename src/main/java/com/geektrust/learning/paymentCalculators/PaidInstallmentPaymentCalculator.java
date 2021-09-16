package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.Loan;
import com.geektrust.learning.Receipt;

public class PaidInstallmentPaymentCalculator {
    private final Loan currentLoan;
    private final Receipt receipt;
    private final float emiNo;
    private final int finalEmiNumber;

    public PaidInstallmentPaymentCalculator(Loan currentLoan, Receipt receipt,
                                            float emiNo, int finalEmiNumber) {
        this.currentLoan = currentLoan;
        this.receipt = receipt;
        this.emiNo = emiNo;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        if (isPaymentCalculationForFutureMonth(emiNo, receipt.getEmiNo())) {
            output = new Dues(currentLoan, receipt, finalEmiNumber).calculate();
        } else if (isPaymentCalculationForCurrentMonth(emiNo, receipt)) {
            float totalAmountPaidSoFar = receipt.getTotalAmountWithLumpSum();
            float totalAmountLeft = currentLoan.getTotalAmountTORepay() - totalAmountPaidSoFar;
            float emiLeft = (float) Math.ceil(totalAmountLeft / currentLoan.getTotalAmountToPayPerMonth());
            output[0] = totalAmountPaidSoFar;
            output[1] = emiLeft;
        } else {
            float emiLeft = currentLoan.getPeriodInMonths() - emiNo;
            float totalAmountPaidSoFar = emiNo * currentLoan.getTotalAmountToPayPerMonth();
            output[0] = totalAmountPaidSoFar;
            output[1] = emiLeft;
        }
        return output;
    }

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, Receipt receipt) {
        return emiNo >= receipt.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }
}

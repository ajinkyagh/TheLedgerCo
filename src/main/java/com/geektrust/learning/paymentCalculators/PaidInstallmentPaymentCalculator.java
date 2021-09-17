package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.EMIPaymentReceipt;
import com.geektrust.learning.Loan;

public class PaidInstallmentPaymentCalculator {
    private final Loan currentLoan;
    private final EMIPaymentReceipt EMIPaymentReceipt;
    private final float emiNo;
    private final int finalEmiNumber;

    public PaidInstallmentPaymentCalculator(Loan currentLoan, EMIPaymentReceipt EMIPaymentReceipt,
                                            float emiNo, int finalEmiNumber) {
        this.currentLoan = currentLoan;
        this.EMIPaymentReceipt = EMIPaymentReceipt;
        this.emiNo = emiNo;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        if (isPaymentCalculationForFutureMonth(emiNo, EMIPaymentReceipt.getEmiNo())) {
            output = new Dues(currentLoan, EMIPaymentReceipt, finalEmiNumber).calculate();
        } else if (isPaymentCalculationForCurrentMonth(emiNo, EMIPaymentReceipt)) {
            float totalAmountPaidSoFar = EMIPaymentReceipt.getTotalAmountWithLumpSum();
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

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, EMIPaymentReceipt EMIPaymentReceipt) {
        return emiNo >= EMIPaymentReceipt.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }
}

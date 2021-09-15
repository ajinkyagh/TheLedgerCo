package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.Loan;
import com.geektrust.learning.PaymentReceipt;

public class PaidInstallmentPaymentCalculator {
    private final Loan currentLoan;
    private final PaymentReceipt paymentReceipt;
    private final float emiNo;
    private final int finalEmiNumber;

    public PaidInstallmentPaymentCalculator(Loan currentLoan, PaymentReceipt paymentReceipt,
                                            float emiNo, int finalEmiNumber) {
        this.currentLoan = currentLoan;
        this.paymentReceipt = paymentReceipt;
        this.emiNo = emiNo;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        if (isPaymentCalculationForFutureMonth(emiNo, paymentReceipt.getEmiNo())) {
            output = new Dues(currentLoan, paymentReceipt, finalEmiNumber).calculate();
        } else if (isPaymentCalculationForCurrentMonth(emiNo, paymentReceipt)) {
            float totalAmountPaidSoFar = paymentReceipt.getTotalAmountWithLumpSum();
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

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, PaymentReceipt paymentReceipt) {
        return emiNo >= paymentReceipt.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }
}

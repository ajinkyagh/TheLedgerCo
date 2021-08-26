package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.BorrowerDetails;
import com.geektrust.learning.ConsoleWriter;
import com.geektrust.learning.PaymentDetails;

public class PaidInstallmentPaymentCalculator {
    private final BorrowerDetails currentBorrowerDetails;
    private final PaymentDetails paymentDetails;
    private final float emiNo;
    private final int finalEmiNumber;

    public PaidInstallmentPaymentCalculator(BorrowerDetails currentBorrowerDetails, PaymentDetails paymentDetails,
                                            float emiNo, int finalEmiNumber) {
        this.currentBorrowerDetails = currentBorrowerDetails;
        this.paymentDetails = paymentDetails;
        this.emiNo = emiNo;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        if (isPaymentCalculationForFutureMonth(emiNo, paymentDetails.getEmiNo())) {
            output = new FuturePaymentCalculator(currentBorrowerDetails, paymentDetails, finalEmiNumber).calculate();
        } else if (isPaymentCalculationForCurrentMonth(emiNo, paymentDetails)) {
            float totalAmountPaidSoFar = paymentDetails.getTotalAmountWithLumpSum();
            float totalAmountLeft = currentBorrowerDetails.getTotalAmountTORepay() - totalAmountPaidSoFar;
            float emiLeft = (float) Math.ceil(totalAmountLeft / currentBorrowerDetails.getTotalAmountToPayPerMonth());
            output[0] = totalAmountPaidSoFar;
            output[1] = emiLeft;
        } else {
            float emiLeft = currentBorrowerDetails.getPeriodInMonths() - emiNo;
            float totalAmountPaidSoFar = emiNo * currentBorrowerDetails.getTotalAmountToPayPerMonth();
            output[0] = totalAmountPaidSoFar;
            output[1] = emiLeft;
        }
        return output;
    }

    private boolean isPaymentCalculationForCurrentMonth(float emiNo, PaymentDetails paymentDetails) {
        return emiNo >= paymentDetails.getEmiNo();
    }

    private boolean isPaymentCalculationForFutureMonth(float emiNo, float emiNo2) {
        return emiNo > emiNo2;
    }
}

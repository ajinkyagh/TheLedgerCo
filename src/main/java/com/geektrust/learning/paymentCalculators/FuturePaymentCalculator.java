package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.Loan;
import com.geektrust.learning.PaymentReceipt;

import java.util.concurrent.atomic.AtomicInteger;

public class FuturePaymentCalculator {
    private final Loan loan;
    private final PaymentReceipt paymentReceipt;
    private final int finalEmiNumber;

    public FuturePaymentCalculator(Loan loan, PaymentReceipt paymentReceipt, int finalEmiNumber) {
        this.loan = loan;
        this.paymentReceipt = paymentReceipt;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        int totalAmountPaidSoFar = 0;
        AtomicInteger emiCounter = new AtomicInteger(1);
        for (int i = (int) paymentReceipt.getEmiNo(); i < finalEmiNumber; i++) {
            totalAmountPaidSoFar = (int) (paymentReceipt.getTotalAmountWithLumpSum() + loan.getTotalAmountToPayPerMonth() * emiCounter.get());
            emiCounter.getAndIncrement();
        }
        float totalAmountLeft = loan.getTotalAmountTORepay() - totalAmountPaidSoFar;
        float emiLeft = (float) Math.ceil(totalAmountLeft / loan.getTotalAmountToPayPerMonth());

        if (totalAmountPaidSoFar > loan.getTotalAmountTORepay()) {
            output[0] = loan.getTotalAmountTORepay();
        } else {
            output[0] = totalAmountPaidSoFar;
        }
        output[1] = emiLeft;
        return output;
    }
}

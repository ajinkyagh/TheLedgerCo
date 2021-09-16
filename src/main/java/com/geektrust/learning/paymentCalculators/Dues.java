package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.Loan;
import com.geektrust.learning.Receipt;

import java.util.concurrent.atomic.AtomicInteger;

public class Dues {
    private final Loan loan;
    private final Receipt receipt;
    private final int finalEmiNumber;

    public Dues(Loan loan, Receipt receipt, int finalEmiNumber) {
        this.loan = loan;
        this.receipt = receipt;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        int totalAmountPaidSoFar = 0;
        AtomicInteger emiCounter = new AtomicInteger(1);
        for (int i = (int) receipt.getEmiNo(); i < finalEmiNumber; i++) {
            totalAmountPaidSoFar = (int) (receipt.getTotalAmountWithLumpSum() + loan.getTotalAmountToPayPerMonth() * emiCounter.get());
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

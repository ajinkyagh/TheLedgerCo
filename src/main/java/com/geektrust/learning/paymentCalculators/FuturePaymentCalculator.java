package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.BorrowerDetails;
import com.geektrust.learning.ConsoleWriter;
import com.geektrust.learning.PaymentDetails;

import java.util.concurrent.atomic.AtomicInteger;

public class FuturePaymentCalculator {
    private final BorrowerDetails borrowerDetails;
    private final PaymentDetails paymentDetails;
    private final int finalEmiNumber;

    public FuturePaymentCalculator(BorrowerDetails borrowerDetails, PaymentDetails paymentDetails, int finalEmiNumber) {
        this.borrowerDetails = borrowerDetails;
        this.paymentDetails = paymentDetails;
        this.finalEmiNumber = finalEmiNumber;
    }

    public float[] calculate() {
        float[] output = new float[2];
        int totalAmountPaidSoFar = 0;
        AtomicInteger emiCounter = new AtomicInteger(1);
        for (int i = (int) paymentDetails.getEmiNo(); i < finalEmiNumber; i++) {
            totalAmountPaidSoFar = (int) (paymentDetails.getTotalAmountWithLumpSum() + borrowerDetails.getTotalAmountToPayPerMonth() * emiCounter.get());
            emiCounter.getAndIncrement();
        }
        float totalAmountLeft = borrowerDetails.getTotalAmountTORepay() - totalAmountPaidSoFar;
        float emiLeft = (float) Math.ceil(totalAmountLeft / borrowerDetails.getTotalAmountToPayPerMonth());

        if (totalAmountPaidSoFar > borrowerDetails.getTotalAmountTORepay()) {
            output[0] = borrowerDetails.getTotalAmountTORepay();
        } else {
            output[0] = totalAmountPaidSoFar;
        }
        output[1] = emiLeft;
        return output;
    }
}

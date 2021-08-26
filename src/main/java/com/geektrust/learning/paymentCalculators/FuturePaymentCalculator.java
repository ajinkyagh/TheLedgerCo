package com.geektrust.learning.paymentCalculators;

import com.geektrust.learning.BorrowerDetails;
import com.geektrust.learning.ConsoleWriter;
import com.geektrust.learning.PaymentDetails;

import java.util.concurrent.atomic.AtomicInteger;

public class FuturePaymentCalculator {
    private final BorrowerDetails borrowerDetails;
    private final PaymentDetails paymentDetails;
    private ConsoleWriter consoleWriter;
    private String[] splitInput;

    public FuturePaymentCalculator(BorrowerDetails borrowerDetails, PaymentDetails paymentDetails, ConsoleWriter consoleWriter, String[] splitInput) {
        this.borrowerDetails = borrowerDetails;
        this.paymentDetails = paymentDetails;
        this.consoleWriter = consoleWriter;
        this.splitInput = splitInput;
    }

    public void calculate() {
        int totalAmountPaidSoFar = 0;
        AtomicInteger emiCounter = new AtomicInteger(1);
        for (int i = (int) paymentDetails.getEmiNo(); i < Integer.parseInt(splitInput[3]); i++) {
            totalAmountPaidSoFar = (int) (paymentDetails.getTotalAmountWithLumpSum() + borrowerDetails.getTotalAmountToPayPerMonth() * emiCounter.get());
            emiCounter.getAndIncrement();
        }
        float totalAmountLeft = borrowerDetails.getTotalAmountTORepay() - totalAmountPaidSoFar;
        float emiLeft = (float) Math.ceil(totalAmountLeft / borrowerDetails.getTotalAmountToPayPerMonth());
        if (totalAmountPaidSoFar >borrowerDetails.getTotalAmountTORepay()){
            consoleWriter.writeToConsole(splitInput, (int) borrowerDetails.getTotalAmountTORepay(), (int) emiLeft);
        }
        else {
            consoleWriter.writeToConsole(splitInput, (int) totalAmountPaidSoFar, (int) emiLeft);
        }
    }
}

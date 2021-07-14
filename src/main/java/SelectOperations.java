import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectOperations {
    Scanner sc=new Scanner(System.in);


    float interest,amount,periodInMonths,amountPerMonth,totalAmountWithLuumpsum;
    HashMap<String,Borrower_Details> borrower_detailsHashMap =new HashMap<>();
    HashMap<String,Payment_Details> payment_detailsHashMap=new HashMap<>();
    Boolean flag=true;
    float no,output;
    public void bank_operations(){
        while (true){
            String input=sc.nextLine();
            String[] splitInput=input.split("\\s");
            switch (splitInput[0]){
                case "LOAN":
                     interest=Float.parseFloat(splitInput[3])*Float.parseFloat(splitInput[4])*Float.parseFloat(splitInput[5])/100;
                    amount=interest+Float.parseFloat(splitInput[3]);
                    periodInMonths=Float.parseFloat(splitInput[4])*12;
                    amountPerMonth= (float) Math.ceil(amount/periodInMonths);
                    //System.out.println(amountPerMonth);
                    borrower_detailsHashMap.put(splitInput[2],new Borrower_Details(splitInput[3],splitInput[4],splitInput[5],amountPerMonth));
                    break;
                case  "BALANCE":
                     output=0;
                     AtomicInteger j= new AtomicInteger(1);

                    if (borrower_detailsHashMap.containsKey(splitInput[2]) && payment_detailsHashMap.containsKey(splitInput[2]) )
                    {
                        Payment_Details key1 = payment_detailsHashMap.get(splitInput[2]);
                            if (Float.parseFloat(splitInput[3])>key1.getNo()){
                                borrower_detailsHashMap.forEach((key, value) -> {
                                    if (key.contentEquals(splitInput[2])) {
                                        for (int i = (int) key1.getNo(); i < Integer.parseInt(splitInput[3]); i++) {
                                            output =  key1.getLumpsum() + value.getAmountPerMonth()* j.get();
                                            j.getAndIncrement();
                                        }
                                        System.out.println(output);
                                    }
                                });
                            }
                            else if (Float.parseFloat(splitInput[3])>=key1.getNo()){
                                borrower_detailsHashMap.forEach((key, value) -> {
                                    if (key.contentEquals(splitInput[2])) {
                                        output = key1.getLumpsum();
                                        System.out.println(output);
                                    }
                                });
                            }
                            else {
                                borrower_detailsHashMap.forEach((key, value) -> {
                                    if (key.contentEquals(splitInput[2])) {
                                        double output = Double.parseDouble(splitInput[3])*value.getAmountPerMonth();
                                        System.out.println(output);
                                    }
                                });
                            }
                    }
                    else {
                        borrower_detailsHashMap.forEach((key, value) -> {
                            if (key.contentEquals(splitInput[2])) {
                                double output = Double.parseDouble(splitInput[3])*value.getAmountPerMonth();
                                System.out.println(output);
                            }
                        });
                    }


                    break;
                case  "PAYMENT":
                        borrower_detailsHashMap.forEach((key, value) -> {
                            if (key.contentEquals(splitInput[2])) {
                                totalAmountWithLuumpsum = Float.parseFloat(splitInput[3]) + (value.getAmountPerMonth() * Float.parseFloat(splitInput[4]));
                                no=Float.parseFloat(splitInput[4]);
                                flag=false;
                                //System.out.println(totalAmountWithLuumpsum);
                                payment_detailsHashMap.put(splitInput[2],new Payment_Details(totalAmountWithLuumpsum,no));
                            }
                        });
                        break;
            }


        }
    }
}

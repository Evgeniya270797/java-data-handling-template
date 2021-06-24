package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {


       return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),range, RoundingMode.HALF_UP);

    }

    private boolean isPrimary(int number) {
        if(number==2||number==3){
            return true;
        }

        for (int i = 2; i<=number/2; i++) {

            if(number%i==0){
                return false;
            }

        }

        return true;

    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {

        int number = 0;
        int i = 2;

        BigInteger bigInteger;

        while(true){
            if(isPrimary(i)) {
                number++;
            }

            if(range==number-1){
                bigInteger = BigInteger.valueOf(i);
                break;
            }

            i++;

        }


        return bigInteger;
    }
}

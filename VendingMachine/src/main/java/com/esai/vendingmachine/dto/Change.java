package com.esai.vendingmachine.dto;

import com.esai.vendingmachine.Coin;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Esai
 */
public class Change {
    
    // Change properties
    int quarterCount;
    int dimeCount;
    int nickelCount;
    int pennyCount;

    // return quarter count
    public int getQuarterCount() {
        return quarterCount;
    }

    // return dime count
    public int getDimeCount() {
        return dimeCount;
    }

    // return nickel count
    public int getNickelCount() {
        return nickelCount;
    }

    // return penny count
    public int getPennyCount() {
        return pennyCount;
    }

    // calculate change using Enum as cases
    // assign coins their respective counts based on change
    public void calculateChange(BigDecimal change) {
        BigDecimal newChange;

        while (!change.equals(new BigDecimal("0.00"))) {
            Coin userCase = determineUserCase(change);

            switch (userCase) {
                case QUARTER:
                    quarterCount = (change.divide(new BigDecimal(".25"))).setScale(2, RoundingMode.HALF_UP).intValue();
                    newChange = new BigDecimal(quarterCount * .25).setScale(2, RoundingMode.HALF_UP);
                    change = change.subtract(newChange);
                    break;
                case DIME:
                    dimeCount = (change.divide(new BigDecimal(".10"))).setScale(2, RoundingMode.HALF_UP).intValue();
                    newChange = new BigDecimal(dimeCount * .10).setScale(2, RoundingMode.HALF_UP);
                    change = change.subtract(newChange);
                    break;
                case NICKEL:
                    nickelCount = (change.divide(new BigDecimal(".05"))).setScale(2, RoundingMode.HALF_UP).intValue();
                    newChange = new BigDecimal(nickelCount * .05).setScale(2, RoundingMode.HALF_UP);
                    change = change.subtract(newChange);
                    break;
                case PENNY:
                    pennyCount = (change.multiply(new BigDecimal("100"))).setScale(2, RoundingMode.HALF_UP).intValue();
                    change = new BigDecimal("0.00");
                    break;
            }
        }

    }

    // determine case to use when calculating change 
    // return Enum Coin
    private Coin determineUserCase(BigDecimal change) {
        if (change.compareTo(new BigDecimal(.25)) > -1) {
            return Coin.QUARTER;
        } else if (change.compareTo(new BigDecimal(.10)) > -1) {
            return Coin.DIME;
        } else if (change.compareTo(new BigDecimal(.05)) > -1) {
            return Coin.NICKEL;
        } else {
            return Coin.PENNY;
        }
    }

}

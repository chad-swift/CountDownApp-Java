package org.Countdown.Number;

import java.util.ArrayList;
import java.util.List;

public class CountDownNumberCalculator {

    public static List<CountdownNumber> calculate(CountdownNumber a, CountdownNumber b) {

        ArrayList<CountdownNumber> list = new ArrayList<>();

        // only does a > b so that 7 x 2 and 2 x 7 don't both appear in the calculation history
        if (a.greaterThan(b)) {

            // math
            CountdownNumber sum = a.add(b);
            CountdownNumber product = a.multiply(b);
            CountdownNumber difference = a.subtract(b);

            // add to result list
            list.add(sum);
            list.add(product);
            list.add(difference);

            // record operation
            product.addToCalculationHistory(a + " x " + b + " = " + product);
            sum.addToCalculationHistory(a + " + " + b + " = " + sum);
            difference.addToCalculationHistory(a + " - " + b + " = " + difference);
        }

        // division works slightly differently since we also need to make sure the division will result in an integer
        if (a.greaterThan(b) && a.modulus(b) == 0) {
            // do math
            CountdownNumber quotient = a.divide(b);

            // add to list
            list.add(quotient);

            // record operation
            quotient.addToCalculationHistory(a + " / " + b + " = " + quotient);
        }

        // return a list of up to 4 answers ie [4, 3, 5]. In this example, there's no division since division failed
        return list;
    }
}

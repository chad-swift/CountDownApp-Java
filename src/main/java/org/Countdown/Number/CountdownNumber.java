package org.Countdown.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a number in the Countdown Game. Saves the operation history within the state so it is able to be returned.
 */
public class CountdownNumber extends Number {
    private final long value;
    private final List<String> calculationHistory = new ArrayList<>();

    public CountdownNumber(long value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    public CountdownNumber add(CountdownNumber y) {
        CountdownNumber sum = new CountdownNumber(this.longValue() + y.longValue());
        sum.addToCalculationHistory(y.calculationHistory);
        sum.addToCalculationHistory(this.calculationHistory);
        return sum;
    }

    public CountdownNumber subtract(CountdownNumber y) {
        CountdownNumber difference = new CountdownNumber(this.longValue() - y.longValue());
        difference.addToCalculationHistory(y.calculationHistory);
        difference.addToCalculationHistory(this.calculationHistory);
        return difference;
    }

    public CountdownNumber multiply(CountdownNumber y) {
        CountdownNumber product = new CountdownNumber(this.longValue() * y.longValue());
        product.addToCalculationHistory(y.calculationHistory);
        product.addToCalculationHistory(this.calculationHistory);
        return product;
    }

    public CountdownNumber divide(CountdownNumber y) {
        CountdownNumber quotient = new CountdownNumber(this.longValue() / y.longValue());
        quotient.addToCalculationHistory(y.calculationHistory);
        quotient.addToCalculationHistory(this.calculationHistory);
        return quotient;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        CountdownNumber other = (CountdownNumber) obj;

        return this.longValue() == other.longValue();

    }

    public boolean greaterThan(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        CountdownNumber other = (CountdownNumber) obj;

        return this.longValue() > other.longValue();

    }

    public boolean lessThan(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        CountdownNumber other = (CountdownNumber) obj;

        return this.longValue() < other.longValue();

    }

    public long modulus(CountdownNumber other) {
        return this.longValue() % other.longValue();
    }

    public List<String> getCalculationHistory() {
        return new ArrayList<>(calculationHistory);
    }

    public void addToCalculationHistory(String element) {
        this.calculationHistory.add(element);
    }

    public void addToCalculationHistory(List<String> elements) {
        this.calculationHistory.addAll(elements);
    }

    public String toString() {
        return ""+this.longValue();
    }

}

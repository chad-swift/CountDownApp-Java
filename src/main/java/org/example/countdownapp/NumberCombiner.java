package org.example.countdownapp;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.util.*;

public class NumberCombiner {
    static final private CountdownNumber
            a = new CountdownNumber(25),
            b = new CountdownNumber(4),
            c = new CountdownNumber(6),
            d = new CountdownNumber(8),
            e = new CountdownNumber(2),
            f = new CountdownNumber(10);
    static final private CountdownNumber target = new CountdownNumber(743);

    static void main() {

        // Permutations for the list of numbers. This is so they appear in any order and the math can happen in any order
        IGenerator<List<CountdownNumber>> permutations = Generator.permutation(a, b, c, d, e, f).simple();

        // For each permutation, we need to check each grouping pattern. More patterns can be added as needed
        for (List<CountdownNumber> list : permutations) {
            a_b_c_d_e_f(list);
            ab_cd_ef(list);
            a_bc_d_ef(list);
            ab_c_de_f(list);
            a_bc_de_f(list);
            ab_cd_e_f(list);
            ab_c_d_ef(list);
        }
    }

    /**
     * This is the "rolling total" pattern. The numbers snowball from right to left to find one big total,
     * and all math is done from the previous calculations
     * @param list This takes in a permutation list of the countdown numbers
     */
    public static void a_b_c_d_e_f(List<CountdownNumber> list) {

        for (List<CountdownNumber> firstLevel: reduce(list)) {
            for(List<CountdownNumber> secondLevel : reduce(firstLevel)) {
                for (List<CountdownNumber> thirdLevel: reduce(secondLevel)) {
                    for (List<CountdownNumber> fourthLevel : reduce(thirdLevel)) {
                        calculate(fourthLevel.getFirst(), fourthLevel.getLast());
                    }
                }
            }
        }

    }

    /**
     * This is the "All Twos" pattern. There are three separate calculations done,
     * and then each calculation is then combined from right to left
     * @param list This takes in a permutation list of the countdown numbers
     */
    public static void ab_cd_ef(List<CountdownNumber> list) {

        List<CountdownNumber> ab = new ArrayList<>();
        List<CountdownNumber> cd = new ArrayList<>();
        List<CountdownNumber> ef = new ArrayList<>();
        ab.add(list.getFirst());
        ab.add(list.get(1));
        cd.add(list.get(2));
        cd.add(list.get(3));
        ef.add(list.get(4));
        ef.add(list.get(5));

        List<CountdownNumber> firstGroupAnswers = calculate(ab.getFirst(), ab.get(1));
        List<CountdownNumber> secondGroupAnswers = calculate(cd.getFirst(), cd.get(1));
        List<CountdownNumber> thirdGroupAnswers = calculate(ef.getFirst(), ef.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();

        for (CountdownNumber answer1 : firstGroupAnswers) {
            for (CountdownNumber answer2 : secondGroupAnswers) {
                newFirstGroup.addAll(calculate(answer1, answer2));
            }
        }

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : thirdGroupAnswers) {
                calculate(answer1, answer2);
            }
        }

    }

    /** This is one of the "Offset Patterns". Two groups of two are combined with single outliers
     * @param list This is a permutation list of all the countdown numbers
     */
    public static void a_bc_d_ef(List<CountdownNumber> list) {
        List<CountdownNumber> bc = new ArrayList<>();
        List<CountdownNumber> ef = new ArrayList<>();
        bc.add(list.get(1));
        bc.add(list.get(2));
        ef.add(list.get(4));
        ef.add(list.get(5));

        List<CountdownNumber> firstGroup = calculate(bc.getFirst(), bc.get(1));
        List<CountdownNumber> secondGroup = calculate(ef.getFirst(), ef.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();
        List<CountdownNumber> newSecondGroup = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, a));
        }

        for (CountdownNumber answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, d));
        }

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                calculate(answer1, answer2);
            }
        }
    }

    /** This is one of the "Offset Patterns". Two groups of two are combined with single outliers
     * @param list This is a permutation list of all the countdown numbers
     */
    public static void ab_c_de_f(List<CountdownNumber> list) {
        List<CountdownNumber> ab = new ArrayList<>();
        List<CountdownNumber> de = new ArrayList<>();
        ab.add(list.get(1));
        ab.add(list.get(2));
        de.add(list.get(4));
        de.add(list.get(5));

        List<CountdownNumber> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<CountdownNumber> secondGroup = calculate(de.getFirst(), de.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();
        List<CountdownNumber> newSecondGroup = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, c));
        }

        for (CountdownNumber answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, f));
        }

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                calculate(answer1, answer2);
            }
        }
    }


    /**
     * This is the "doubles in the middle" pattern. Separate calculations are performed in the middle and combined
     * with singles as rolling totals on each end.
     * @param list This is a permutation list of all of the countdown numbers
     */
    public static void a_bc_de_f(List<CountdownNumber> list) {
        List<CountdownNumber> bc = new ArrayList<>();
        List<CountdownNumber> de = new ArrayList<>();
        bc.add(list.get(1));
        bc.add(list.get(2));
        de.add(list.get(3));
        de.add(list.get(4));

        List<CountdownNumber> firstGroup = calculate(bc.getFirst(), bc.get(1));
        List<CountdownNumber> secondGroup = calculate(de.getFirst(), de.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();
        List<CountdownNumber> newSecondGroup = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, a));
        }

        for (CountdownNumber answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, f));
        }

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                calculate(answer1, answer2);
            }
        }
    }


    /**
     * This is the "doubles in the front" pattern. Two doubles are put on the front and combined with
     * singles on the outside. This pattern exists because not all the numbers need to be used.
     * @param list This is a permutation list of all of the countdown numbers
     */
    public static void ab_cd_e_f(List<CountdownNumber> list) {
        List<CountdownNumber> ab = new ArrayList<>();
        List<CountdownNumber> cd = new ArrayList<>();
        ab.add(list.getFirst());
        ab.add(list.get(1));
        cd.add(list.get(2));
        cd.add(list.get(3));

        List<CountdownNumber> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<CountdownNumber> secondGroup = calculate(cd.getFirst(), cd.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();

        for (CountdownNumber answer1 : firstGroup) {
            for (CountdownNumber answer2 : secondGroup) {
                newFirstGroup.addAll(calculate(answer1, answer2));
            }
        }

        List<CountdownNumber> newSecondGroup = new ArrayList<>();

        for (CountdownNumber answer : newFirstGroup) {
            newSecondGroup.addAll(calculate(answer, e));
        }

        for (CountdownNumber answer: newSecondGroup) {
            calculate(answer, f);
        }
    }

    /**
     * This is the "doubles on the either end" pattern. Two doubles are put on the front and the end and combined with
     * singles on the inside. This pattern exists because not all the numbers need to be used.
     * @param list This is a permutation list of all of the countdown numbers
     */
    public static void ab_c_d_ef(List<CountdownNumber> list) {
        List<CountdownNumber> ab = new ArrayList<>();
        List<CountdownNumber> ef = new ArrayList<>();
        ab.add(list.getFirst());
        ab.add(list.get(1));
        ef.add(list.get(4));
        ef.add(list.getLast());

        List<CountdownNumber> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<CountdownNumber> lastGroup = calculate(ef.getFirst(), ef.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, c));
        }

        List<CountdownNumber> newSecondGroup = new ArrayList<>();

        for (CountdownNumber answer : newFirstGroup) {
            newSecondGroup.addAll(calculate(answer, d));
        }

        for (CountdownNumber answer1: newSecondGroup) {
            for (CountdownNumber answer2: lastGroup) {
                calculate(answer1, answer2);
            }
        }

    }

    /**
     * @param list Takes in a list that needs reduced
     * @return A list of lists that all have one less length than the one started with
     */
    public static List<List<CountdownNumber>> reduce(List<CountdownNumber> list) {
        List<CountdownNumber> listCopy = new ArrayList<>(list);

        List<CountdownNumber> firstGroup = calculate(listCopy.removeFirst(), listCopy.remove(1));

        List<List<CountdownNumber>> timelines = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            List<CountdownNumber> timeLine = new ArrayList<>();
            timeLine.add(answer);
            timeLine.addAll(listCopy);
            timelines.add(timeLine);
        }

        return timelines;

    }

    /**
     * @param a First operand
     * @param b Second operand
     * @return A list of answers for each of the possible successful operations
     */
    public static List<CountdownNumber> calculate(CountdownNumber a, CountdownNumber b) {

        ArrayList<CountdownNumber> list = new ArrayList<>();
        CountdownNumber sum = a.add(b);
        sum.addToCalculationHistory(a + " + " + b + " = " + sum);
        CountdownNumber product = a.multiply(b);
        product.addToCalculationHistory(a + " x " + b + " = " + product);

        list.add(sum);
        list.add(product);

        if (a.greaterThan(b)) {
            CountdownNumber difference = a.subtract(b);
            difference.addToCalculationHistory(a + " - " + b + " = " + difference);
            list.add(difference);
        }

        if (a.greaterThan(b) && a.intValue() % b.intValue() == 0) {
            CountdownNumber quotient = a.divide(b);
            quotient.addToCalculationHistory(a + " / " + b + " = " + quotient);
            list.add(quotient);
        }

        for (CountdownNumber answer : list) {
            if (answer.equals(target)) {
                System.out.println(answer.getCalculationHistory());
            }
        }

        return list;
    }
}


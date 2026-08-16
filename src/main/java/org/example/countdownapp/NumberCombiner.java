package org.example.countdownapp;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.util.*;

public class NumberCombiner {
    static private CountdownNumber
            a = new CountdownNumber(1),
            b = new CountdownNumber(2),
            c = new CountdownNumber(25),
            d = new CountdownNumber(100),
            e = new CountdownNumber(75),
            f = new CountdownNumber(50);
    static private CountdownNumber target = new CountdownNumber(940);

    public static void main(String[] args) {

        /*
        are there only ever actually seven patterns here?
        abcdef - rolling total
        ab cd ef - three separate transactions
        a bc d ef - two transactions and two rolling totals
        ab c de f - same, but opposite pattern
        a bc de f - transactions done in the middle with rolling totals on the outside
        ab cd e f - account for not using all the numbers
        ab c d ef - account for not using all the numbers

         */


        abcdef(a, b, c, d, e, f);
        ab_cd_ef(a, b, c, d, e, f);
        a_bc_d_ef(a, b, c, d, e, f);
        ab_c_de_f(a, b, c, d, e, f);
        a_bc_de_f(a, b, c, d, e, f);
        ab_cd_e_f(a, b, c, d, e, f);
        ab_c_d_ef(a, b, c, d, e, f);
    }

    public static void abcdef(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        IGenerator<List<CountdownNumber>> permutations = Generator.permutation(a, b, c, d, e, f).simple();

        for (List<CountdownNumber> list : permutations) {
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

    }

    public static void ab_cd_ef(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        IGenerator<List<CountdownNumber>> permutations = Generator.permutation(a, b, c, d, e, f).simple();

        for(List<CountdownNumber> permutation : permutations) {
            List<CountdownNumber> firstTwo = new ArrayList<>();
            List<CountdownNumber> secondTwo = new ArrayList<>();
            List<CountdownNumber> thirdTwo = new ArrayList<>();
            firstTwo.add(permutation.getFirst());
            firstTwo.add(permutation.get(1));
            secondTwo.add(permutation.get(2));
            secondTwo.add(permutation.get(3));
            thirdTwo.add(permutation.get(4));
            thirdTwo.add(permutation.get(5));

            List<CountdownNumber> reduced = reduce(firstTwo, secondTwo, thirdTwo);
        }
    }

    public static void a_bc_d_ef(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        for (List<CountdownNumber> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<CountdownNumber> bc = new ArrayList<>();
            List<CountdownNumber> ef = new ArrayList<>();
            bc.add(permutation.get(1));
            bc.add(permutation.get(2));
            ef.add(permutation.get(4));
            ef.add(permutation.get(5));

            List<CountdownNumber> reduced = reduce(permutation.getFirst(), bc, permutation.get(3), ef);
        }
    }

    public static void ab_c_de_f(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        for (List<CountdownNumber> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<CountdownNumber> ab = new ArrayList<>();
            List<CountdownNumber> de = new ArrayList<>();
            ab.add(permutation.get(1));
            ab.add(permutation.get(2));
            de.add(permutation.get(4));
            de.add(permutation.get(5));

            List<CountdownNumber> reduced = reduce(ab, permutation.get(2), de, permutation.getLast());
        }
    }

    public static void a_bc_de_f(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        for (List<CountdownNumber> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<CountdownNumber> bc = new ArrayList<>();
            List<CountdownNumber> de = new ArrayList<>();
            bc.add(permutation.get(1));
            bc.add(permutation.get(2));
            de.add(permutation.get(3));
            de.add(permutation.get(4));

            List<CountdownNumber> reduced = reduce(permutation.getFirst(), bc, de, permutation.getLast());
        }
    }

    public static void ab_cd_e_f(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        for (List<CountdownNumber> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<CountdownNumber> ab = new ArrayList<>();
            List<CountdownNumber> cd = new ArrayList<>();
            ab.add(permutation.getFirst());
            ab.add(permutation.get(1));
            cd.add(permutation.get(2));
            cd.add(permutation.get(3));

            List<CountdownNumber> reduced = reduce(ab, cd, permutation.get(4), permutation.getLast());
        }
    }

    public static void ab_c_d_ef(CountdownNumber a, CountdownNumber b, CountdownNumber c, CountdownNumber d, CountdownNumber e, CountdownNumber f) {
        for (List<CountdownNumber> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<CountdownNumber> ab = new ArrayList<>();
            List<CountdownNumber> ef = new ArrayList<>();
            ab.add(permutation.getFirst());
            ab.add(permutation.get(1));
            ef.add(permutation.get(4));
            ef.add(permutation.getLast());

            List<CountdownNumber> reduced = reduce(ab, permutation.get(2), permutation.get(3), ef);
        }
    }

    public static List<List<CountdownNumber>> reduce(List<CountdownNumber> list) {

        List<CountdownNumber> firstGroup = calculate(list.removeFirst(), list.remove(1));

        List<List<CountdownNumber>> timelines = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            List<CountdownNumber> timeLine = new ArrayList<>();
            timeLine.add(answer);
            timeLine.addAll(list);
            timelines.add(timeLine);
        }

        return timelines;

    }

    public static List<CountdownNumber> reduce(List<CountdownNumber> ab, List<CountdownNumber> cd, List<CountdownNumber> ef) {
        if (ab.size() > 2 || cd.size() > 2 || ef.size() > 2) {
            throw new ArrayIndexOutOfBoundsException("There should only be three lists containing two elements each");
        }

        List<CountdownNumber> firstGroupAnswers = calculate(ab.getFirst(), ab.get(1));
        List<CountdownNumber> secondGroupAnswers = calculate(cd.getFirst(), cd.get(1));
        List<CountdownNumber> thirdGroupAnswers = calculate(ef.getFirst(), ef.get(1));

        List<CountdownNumber> newFirstGroup = new ArrayList<>();

        for (CountdownNumber answer1 : firstGroupAnswers) {
            for (CountdownNumber answer2 : secondGroupAnswers) {
                newFirstGroup.addAll(calculate(answer1, answer2));
            }
        }

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : thirdGroupAnswers) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }

        return finalList;

    }

    public static List<CountdownNumber> reduce(CountdownNumber a, List<CountdownNumber> bc, CountdownNumber d, List<CountdownNumber> ef) {
        if (bc.size() > 2 || ef.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for bc and ef should be lists of size two");
        }

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

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<CountdownNumber> reduce(List<CountdownNumber> ab, CountdownNumber c, List<CountdownNumber> de, CountdownNumber f) {
        if (ab.size() > 2 || de.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and de should be lists of size two");
        }

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

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<CountdownNumber> reduce(CountdownNumber a, List<CountdownNumber> bc, List<CountdownNumber> de, CountdownNumber f) {
        if (bc.size() > 2 || de.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for bc and de should be lists of size two");
        }

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

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer1 : newFirstGroup) {
            for (CountdownNumber answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<CountdownNumber> reduce(List<CountdownNumber> ab, List<CountdownNumber> cd, CountdownNumber e, CountdownNumber f) {
        if (ab.size() > 2 || cd.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and cd should be lists of size two");
        }

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

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer: newSecondGroup) {
            finalList.addAll(calculate(answer, f));
        }

        return finalList;
    }

    public static List<CountdownNumber> reduce(List<CountdownNumber> ab, CountdownNumber c, CountdownNumber d, List<CountdownNumber> ef) {
        if (ab.size() > 2 || ef.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and ef should be lists of size two");
        }

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

        List<CountdownNumber> finalList = new ArrayList<>();

        for (CountdownNumber answer1: newSecondGroup) {
            for (CountdownNumber answer2: lastGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }

        return finalList;
    }


    public static List<CountdownNumber> calculate(CountdownNumber a, CountdownNumber b) {

        ArrayList<CountdownNumber> list = new ArrayList<>();
        CountdownNumber sum = a.add(b);
        sum.addToCalculationHistory(a + " + " + b + " = " + sum);
        CountdownNumber product = a.multiply(b);
        product.addToCalculationHistory(a + " x " + b + " = " + product);

        list.add(sum);
        list.add(product);

        if (a.longValue() > b.longValue()) {
            CountdownNumber difference = a.subtract(b);
            difference.addToCalculationHistory(a + " - " + b + " = " + difference);
            list.add(difference);
        }

        if (a.intValue() > b.intValue() && a.intValue() % b.intValue() == 0) {
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


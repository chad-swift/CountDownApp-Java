package org.example.countdownapp;

import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.util.*;

public class NumberCombiner {
    static private int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6;
    static private int target = 140;

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

        // abcdef(a, b, c, d, e, f);
        // ab_cd_ef(a, b, c, d, e, f);
        // a_bc_d_ef(a, b, c, d, e, f);
        // ab_c_de_f(a, b, c, d, e, f);
        // a_bc_de_f(a, b, c, d, e, f);
        // ab_cd_e_f(a, b, c, d, e, f);
        ab_c_d_ef(a, b, c, d, e, f);
    }

    public static void abcdef(int a, int b, int c, int d, int e, int f) {
        IGenerator<List<Integer>> permutations = Generator.permutation(a, b, c, d, e, f).simple();

        for (List<Integer> list : permutations) {
            for(List<Integer> step1Result : reduce(list)) {
                for (List<Integer> step2Result : reduce(step1Result)) {
                    for (List<Integer> step3Result : reduce(step2Result)) {
                        for (List<Integer> step4Result: reduce(step3Result)) {
                           for (int answer : calculate(step4Result.getFirst(), step4Result.get(1))) {
                               System.out.println(answer);
                            }
                        }
                    }
                }
            }
        }

    }

    public static void ab_cd_ef(int a, int b, int c, int d, int e, int f) {
        IGenerator<List<Integer>> permutations = Generator.permutation(a, b, c, d, e, f).simple();

        for(List<Integer> permutation : permutations) {
            List<Integer> firstTwo = new ArrayList<>();
            List<Integer> secondTwo = new ArrayList<>();
            List<Integer> thirdTwo = new ArrayList<>();
            firstTwo.add(permutation.getFirst());
            firstTwo.add(permutation.get(1));
            secondTwo.add(permutation.get(2));
            secondTwo.add(permutation.get(3));
            thirdTwo.add(permutation.get(4));
            thirdTwo.add(permutation.get(5));

            List<Integer> reduced = reduce(firstTwo, secondTwo, thirdTwo);
            System.out.println(reduced);
        }
    }

    public static void a_bc_d_ef(int a, int b, int c, int d, int e, int f) {
        for (List<Integer> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<Integer> bc = new ArrayList<>();
            List<Integer> ef = new ArrayList<>();
            bc.add(permutation.get(1));
            bc.add(permutation.get(2));
            ef.add(permutation.get(4));
            ef.add(permutation.get(5));

            List<Integer> reduced = reduce(permutation.getFirst(), bc, permutation.get(3), ef);
            System.out.println(reduced);
        }
    }

    public static void ab_c_de_f(int a, int b, int c, int d, int e, int f) {
        for (List<Integer> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<Integer> ab = new ArrayList<>();
            List<Integer> de = new ArrayList<>();
            ab.add(permutation.get(1));
            ab.add(permutation.get(2));
            de.add(permutation.get(4));
            de.add(permutation.get(5));

            List<Integer> reduced = reduce(permutation.get(1), ab, permutation.get(2), de);
            System.out.println(reduced);
        }
    }

    public static void a_bc_de_f(int a, int b, int c, int d, int e, int f) {
        for (List<Integer> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<Integer> bc = new ArrayList<>();
            List<Integer> de = new ArrayList<>();
            bc.add(permutation.get(1));
            bc.add(permutation.get(2));
            de.add(permutation.get(3));
            de.add(permutation.get(4));

            List<Integer> reduced = reduce(permutation.getFirst(), bc, de, permutation.getLast());
            System.out.println(reduced);
        }
    }

    public static void ab_cd_e_f(int a, int b, int c, int d, int e, int f) {
        for (List<Integer> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<Integer> ab = new ArrayList<>();
            List<Integer> cd = new ArrayList<>();
            ab.add(permutation.getFirst());
            ab.add(permutation.get(1));
            cd.add(permutation.get(2));
            cd.add(permutation.get(3));

            List<Integer> reduced = reduce(ab, cd, permutation.get(4), permutation.getLast());
            System.out.println(reduced);
        }
    }

    public static void ab_c_d_ef(int a, int b, int c, int d, int e, int f) {
        for (List<Integer> permutation: Generator.permutation(a, b, c, d, e, f).simple()) {
            List<Integer> ab = new ArrayList<>();
            List<Integer> cd = new ArrayList<>();
            ab.add(permutation.get(1));
            ab.add(permutation.get(2));
            cd.add(permutation.get(4));
            cd.add(permutation.get(5));

            List<Integer> reduced = reduce(ab, cd, permutation.get(4), permutation.getLast());
            System.out.println(reduced);
        }
    }

    public static List<List<Integer>> reduce(List<Integer> list) {
        List<Integer> listCopy = new ArrayList<>(list);
        List<List<Integer>> output = new ArrayList<>();

        int first = listCopy.getFirst();
        int second = listCopy.get(1);

        listCopy.removeFirst();
        listCopy.remove(1);

        List<Integer> calculatedPair = calculate(first, second);

        for (int result: calculatedPair) {
            List<Integer> timeline = new ArrayList<>(listCopy);
            timeline.add(1, result);
            output.add(timeline);
        }
        return output;
    }

    public static List<Integer> reduce(List<Integer> ab, List<Integer> cd, List<Integer> ef) {
        if (ab.size() > 2 || cd.size() > 2 || ef.size() > 2) {
            throw new ArrayIndexOutOfBoundsException("There should only be three lists containing two elements each");
        }

        List<Integer> firstGroupAnswers = calculate(ab.getFirst(), ab.get(1));
        List<Integer> secondGroupAnswers = calculate(cd.getFirst(), cd.get(1));
        List<Integer> thirdGroupAnswers = calculate(ef.getFirst(), ef.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();

        for (int answer1 : firstGroupAnswers) {
            for (int answer2 : secondGroupAnswers) {
                newFirstGroup.addAll(calculate(answer1, answer2));
            }
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer1 : newFirstGroup) {
            for (int answer2 : thirdGroupAnswers) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }

        return finalList;

    }

    public static List<Integer> reduce(int a, List<Integer> bc, int d, List<Integer> ef) {
        if (bc.size() > 2 || ef.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for bc and ef should be lists of size two");
        }

        List<Integer> firstGroup = calculate(bc.getFirst(), bc.get(1));
        List<Integer> secondGroup = calculate(ef.getFirst(), bc.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();
        List<Integer> newSecondGroup = new ArrayList<>();

        for (int answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, a));
        }

        for (int answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, d));
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer1 : newFirstGroup) {
            for (int answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<Integer> reduce(List<Integer> ab, int c, List<Integer> de, int f) {
        if (ab.size() > 2 || de.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and de should be lists of size two");
        }

        List<Integer> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<Integer> secondGroup = calculate(de.getFirst(), de.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();
        List<Integer> newSecondGroup = new ArrayList<>();

        for (int answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, c));
        }

        for (int answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, f));
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer1 : newFirstGroup) {
            for (int answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<Integer> reduce(int a, List<Integer> bc, List<Integer> de, int f) {
        if (bc.size() > 2 || de.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for bc and de should be lists of size two");
        }

        List<Integer> firstGroup = calculate(bc.getFirst(), bc.get(1));
        List<Integer> secondGroup = calculate(de.getFirst(), de.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();
        List<Integer> newSecondGroup = new ArrayList<>();

        for (int answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, a));
        }

        for (int answer: secondGroup) {
            newSecondGroup.addAll(calculate(answer, f));
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer1 : newFirstGroup) {
            for (int answer2 : newSecondGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }
        return finalList;
    }

    public static List<Integer> reduce(List<Integer> ab, List<Integer> cd, int e, int f) {
        if (ab.size() > 2 || cd.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and cd should be lists of size two");
        }

        List<Integer> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<Integer> secondGroup = calculate(cd.getFirst(), cd.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();

        for (int answer1 : firstGroup) {
            for (int answer2 : secondGroup) {
                newFirstGroup.addAll(calculate(answer1, answer2));
            }
        }

        List<Integer> newSecondGroup = new ArrayList<>();

        for (int answer : newFirstGroup) {
            newSecondGroup.addAll(calculate(answer, e));
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer: newSecondGroup) {
            finalList.addAll(calculate(answer, f));
        }

        return finalList;
    }

    public static List<Integer> reduce(List<Integer> ab, int c, int d, List<Integer> ef) {
        if (ab.size() > 2 || ef.size() > 2) {
            throw new IndexOutOfBoundsException("Arguments for ab and ef should be lists of size two");
        }

        List<Integer> firstGroup = calculate(ab.getFirst(), ab.get(1));
        List<Integer> lastGroup = calculate(ef.getFirst(), ef.get(1));

        List<Integer> newFirstGroup = new ArrayList<>();

        for (int answer : firstGroup) {
            newFirstGroup.addAll(calculate(answer, c));
        }

        List<Integer> newSecondGroup = new ArrayList<>();

        for (int answer : newFirstGroup) {
            newSecondGroup.addAll(calculate(answer, d));
        }

        List<Integer> finalList = new ArrayList<>();

        for (int answer1: newSecondGroup) {
            for (int answer2: lastGroup) {
                finalList.addAll(calculate(answer1, answer2));
            }
        }

        return finalList;
    }


    public static List<Integer> calculate(int a, int b) {

        ArrayList<Integer> list = new ArrayList<>();
        int sum = a + b;
        int product = a * b;

        list.add(sum);
        list.add(product);

        if (a > b) {
            int difference = a - b;
            list.add(difference);
        }

        if (a > b && a % b == 0) {
            int quotient = a / b;
            list.add(quotient);
        }

        if (list.contains(target)) {
            System.out.println("I found the target");
        }

        return list;
    }
}

package org.example.countdownapp;

import org.paukov.combinatorics3.CombinationGenerator;
import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.lang.reflect.Array;
import java.util.*;

public class NumberCombiner {
    static private int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6;

    public static void main(String[] args) {

        /*
        are there only ever actually four patterns here?
        abcdef
        ab cd ef
        a bc d ef
        ab c de f
        a bc de f
         */

        allTwosPattern(a, b, c, d, e, f);
    }

    public static void allTwosPattern(int a, int b, int c, int d, int e, int f) {
        IGenerator<List<Integer>> permutations = Generator.permutation(a, b, c, d, e, f).simple();
            List<List<Integer>> step1 = new ArrayList<>();

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

        return list;
    }
}

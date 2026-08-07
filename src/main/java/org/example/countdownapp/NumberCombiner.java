package org.example.countdownapp;

import org.paukov.combinatorics3.CombinationGenerator;
import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.lang.reflect.Array;
import java.util.*;

public class NumberCombiner {
    static private int a = 1, b = 2, c = 3, d = 4, e = 5, f = 6;

    public static void main(String[] args) {
        IGenerator<List<Integer>> twos = Generator.combination(a, b, c, d, e, f).simple(2);
        IGenerator<List<Integer>> threes = Generator.combination(a, b, c, d, e, f).simple(3);

        Map<String, List<Integer>> twoMap = new HashMap<>();

        for (List<Integer> pair : twos) {
            twoMap.put("" + pair.getFirst() + pair.get(1), calculate(pair));
        }

        for (List<Integer> value : twoMap.values()) {
            System.out.println(value);
        }
    }

    public static List<Integer> calculate(List<Integer> nums) {

        ArrayList<Integer> list = new ArrayList<>();

        int num1 = nums.getFirst();
        int num2 = nums.get(1);

        System.out.println("Checking " + num1 + " and " + num2 + "...");

        int sum = num1 + num2;
        System.out.println(" "+num1 + " + " + num2 + " = " + sum);
        int product = num1 * num2;
        System.out.println(" "+num1 + " x " + num2 + " = " + product);
        int difference = Math.abs(num1 - num2);
        System.out.println(" "+num1 + " - " + num2 + " = " + difference);

        list.add(sum);
        list.add(product);

        if (difference > 0) {
            list.add(difference);
        }

        if (num1 > num2) {
            if (num1 % num2 == 0) {
                int division = num1 / num2;
                System.out.println(" "+num1 + " / " + num2 + " = " + division);
                list.add(division);
            }
        } else {
            if (num2 % num1 == 0) {
                int division = num2 / num1;
                System.out.println(" "+num2 + " / " + num1 + " = " + division);
                list.add(division);
            }
        }
        return list;
    }
}

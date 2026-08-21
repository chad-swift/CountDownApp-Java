package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class a_bc_d_ef extends GroupingPatternOnSix {

    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        CountdownNumber a;
        List<CountdownNumber> bc = new ArrayList<>();
        CountdownNumber d;
        List<CountdownNumber> ef = new ArrayList<>();

        a = list.getFirst();
        bc.add(list.get(1));
        bc.add(list.get(2));
        d = list.get(3);
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

}

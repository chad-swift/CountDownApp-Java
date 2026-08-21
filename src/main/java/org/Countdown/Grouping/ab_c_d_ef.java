package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class ab_c_d_ef extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        List<CountdownNumber> ab = new ArrayList<>();
        CountdownNumber c;
        CountdownNumber d;
        List<CountdownNumber> ef = new ArrayList<>();

        ab.add(list.getFirst());
        ab.add(list.get(1));
        c = list.get(2);
        d = list.get(3);
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
}

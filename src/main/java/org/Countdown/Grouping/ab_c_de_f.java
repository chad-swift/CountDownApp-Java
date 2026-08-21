package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class ab_c_de_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        List<CountdownNumber> ab = new ArrayList<>();
        CountdownNumber c;
        List<CountdownNumber> de = new ArrayList<>();
        CountdownNumber f;

        ab.add(list.getFirst());
        ab.add(list.get(1));
        c = list.get(2);
        de.add(list.get(3));
        de.add(list.get(4));
        f = list.getLast();

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
}

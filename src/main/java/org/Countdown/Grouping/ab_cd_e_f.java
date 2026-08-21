package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class ab_cd_e_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        List<CountdownNumber> ab = new ArrayList<>();
        List<CountdownNumber> cd = new ArrayList<>();
        CountdownNumber e;
        CountdownNumber f;

        ab.add(list.getFirst());
        ab.add(list.get(1));
        cd.add(list.get(2));
        cd.add(list.get(3));
        e = list.get(4);
        f = list.getLast();

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
}

package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class a_bc_de_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        CountdownNumber a;
        List<CountdownNumber> bc = new ArrayList<>();
        List<CountdownNumber> de = new ArrayList<>();
        CountdownNumber f;

        a = list.getFirst();
        bc.add(list.get(1));
        bc.add(list.get(2));
        de.add(list.get(3));
        de.add(list.get(4));
        f = list.getLast();

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
}

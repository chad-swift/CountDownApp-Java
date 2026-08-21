package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class ab_cd_ef extends GroupingPatternOnSix {

    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

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
}

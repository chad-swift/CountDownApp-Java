package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class ab_c_d_ef extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        AnswerTuple<CountdownNumber> ab = calculateAndCheckForTarget(
                list.getFirst(),
                list.get(1)
        );
        CountdownNumber c =
                list.get(2);
        CountdownNumber d =
                list.get(3);
        AnswerTuple<CountdownNumber> ef = calculateAndCheckForTarget(
                list.get(4),
                list.getLast()
        );

        if (ab.isAnswerFound() || ef.isAnswerFound()) {
            return;
        }

        List<CountdownNumber> abc = new ArrayList<>();

        for (CountdownNumber answer : ab.getListAnswers()) {
            AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer, c);
            if (combined.isAnswerFound()) {
                return;
            }
            abc.addAll(combined.getListAnswers());
        }

        List<CountdownNumber> abcd = new ArrayList<>();

        for (CountdownNumber answer : abc) {
            AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer, d);
            if (combined.isAnswerFound()) {
                return;
            }
            abcd.addAll(combined.getListAnswers());
        }

        for (CountdownNumber answer1: abcd) {
            for (CountdownNumber answer2: ef.getListAnswers()) {
                calculateAndCheckForTarget(answer1, answer2);
            }
        }

    }
}

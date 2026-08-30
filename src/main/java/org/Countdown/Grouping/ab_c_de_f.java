package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class ab_c_de_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        AnswerTuple<CountdownNumber> ab = calculateAndCheckForTarget(
                list.getFirst(),
                list.get(1)
        );
        CountdownNumber c =
                list.get(2);
        AnswerTuple<CountdownNumber> de = calculateAndCheckForTarget(
                list.get(3),
                list.get(4)
        );
        CountdownNumber f =
                list.getLast();

        if (ab.isAnswerFound() || de.isAnswerFound()) {
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

        List<CountdownNumber> abcde = new ArrayList<>();

        for (CountdownNumber answer1: abc) {
            for (CountdownNumber answer2: de.getListAnswers()) {
                AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer1, answer2);
                if (combined.isAnswerFound()) {
                    return;
                }
                abcde.addAll(combined.getListAnswers());
            }
        }

        for (CountdownNumber answer : abcde) {
            calculateAndCheckForTarget(answer, f);
        }
    }
}

package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class ab_cd_e_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        AnswerTuple<CountdownNumber> ab = calculateAndCheckForTarget(
                list.getFirst(),
                list.get(1));
        AnswerTuple<CountdownNumber> cd = calculateAndCheckForTarget(
                list.get(2),
                list.get(3)
        );
        CountdownNumber e =
                list.get(4);
        CountdownNumber f =
                list.getLast();

        if (ab.isAnswerFound() || cd.isAnswerFound()) {
            return;
        }

        List<CountdownNumber> abcd = new ArrayList<>();

        for (CountdownNumber answer1 : ab.getListAnswers()) {
            for (CountdownNumber answer2 : cd.getListAnswers()) {
                AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer1, answer2);
                if (combined.isAnswerFound()) {
                    return;
                }
                abcd.addAll(combined.getListAnswers());
            }
        }

        List<CountdownNumber> abcde = new ArrayList<>();

        for (CountdownNumber answer : abcd) {
            AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer, e);
            if (combined.isAnswerFound()) {
                return;
            }
            abcde.addAll(combined.getListAnswers());
        }

        for (CountdownNumber answer: abcde) {
            calculateAndCheckForTarget(answer, f);
        }
    }
}

package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class ab_cd_ef extends GroupingPatternOnSix {

    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        AnswerTuple<CountdownNumber> ab = calculateAndCheckForTarget(
                list.getFirst(),
                list.get(1)
        );
        AnswerTuple<CountdownNumber> cd = calculateAndCheckForTarget(
                list.get(2),
                list.get(3)
        );
        AnswerTuple<CountdownNumber> ef = calculateAndCheckForTarget(
                list.get(4),
                list.getLast()
        );

        if (ab.isAnswerFound() || cd.isAnswerFound() || ef.isAnswerFound()) {
            return;
        }

        List<CountdownNumber> abcd = new ArrayList<>();

        for (CountdownNumber answer1 : ab.getListAnswers()) {
            for (CountdownNumber answer2 : cd.getListAnswers()) {
                AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer1, answer2);
                if (combined.isAnswerFound()){
                    return;
                }
                abcd.addAll(combined.getListAnswers());
            }
        }

        for (CountdownNumber answer1 : abcd) {
            for (CountdownNumber answer2 : ef.getListAnswers()) {
                calculateAndCheckForTarget(answer1, answer2);
            }
        }
    }
}

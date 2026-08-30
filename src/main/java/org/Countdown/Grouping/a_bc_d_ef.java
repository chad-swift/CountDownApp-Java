package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class a_bc_d_ef extends GroupingPatternOnSix {

    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        CountdownNumber a =
                list.getFirst();
        AnswerTuple<CountdownNumber> bc = calculateAndCheckForTarget(
                list.get(1),
                list.get(2));
        CountdownNumber d =
                list.get(3);
        AnswerTuple<CountdownNumber> ef = calculateAndCheckForTarget(
                list.get(4),
                list.getLast());

        if (bc.isAnswerFound() || ef.isAnswerFound()) {
            return;
        }

        List<CountdownNumber> abc = new ArrayList<>();

        for (CountdownNumber answer : bc.getListAnswers()) {
            AnswerTuple<CountdownNumber> firstGroupCalculations = calculateAndCheckForTarget(answer, a);
            if (firstGroupCalculations.isAnswerFound()) {
                return;
            }
            abc.addAll(firstGroupCalculations.getListAnswers());
        }

        List<CountdownNumber> abcd = new ArrayList<>();

        for (CountdownNumber answer: abc) {
            AnswerTuple<CountdownNumber> secondGroupCalculations = calculateAndCheckForTarget(answer, d);
            if (secondGroupCalculations.isAnswerFound()) {
                return;
            }
            abcd.addAll(secondGroupCalculations.getListAnswers());
        }

        for (CountdownNumber answer1 : abcd) {
            for (CountdownNumber answer2 : ef.getListAnswers()) {
                calculateAndCheckForTarget(answer1, answer2);
            }
        }
    }

}

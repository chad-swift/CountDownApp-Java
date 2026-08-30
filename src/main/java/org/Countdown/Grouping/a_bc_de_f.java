package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class a_bc_de_f extends GroupingPatternOnSix {
    @Override
    public void execute() {
        List<CountdownNumber> list = getSearchList();

        CountdownNumber a =
                list.getFirst();
        AnswerTuple<CountdownNumber> bc = calculateAndCheckForTarget(
                list.get(1),
                list.get(2));
        AnswerTuple<CountdownNumber> de = calculateAndCheckForTarget(
                list.get(3),
                list.get(4)
        );
        CountdownNumber f =
                list.getLast();

        if (bc.isAnswerFound() || de.isAnswerFound()) {
            return;
        }

        List<CountdownNumber> abc = new ArrayList<>();
        List<CountdownNumber> abcde = new ArrayList<>();

        for (CountdownNumber answer : bc.getListAnswers()) {
            AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer, a);
            if (combined.isAnswerFound()) {
                return;
            }
            abc.addAll(combined.getListAnswers());
        }

        for (CountdownNumber answer: abc) {
            for (CountdownNumber answer2: de.getListAnswers()) {
                AnswerTuple<CountdownNumber> combined = calculateAndCheckForTarget(answer, answer2);
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

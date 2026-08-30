package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public class a_b_c_d_e extends GroupingPatternOnSix {

    @Override
    public void execute() {

        AnswerTuple<List<CountdownNumber>> ab = reduce(getSearchList());
        if (ab.isAnswerFound()) {
            return;
        }
        for (List<CountdownNumber> firstLevel: ab.getListAnswers()) {
            AnswerTuple<List<CountdownNumber>> abc = reduce(firstLevel);
            if (abc.isAnswerFound()) {
                return;
            }
            for(List<CountdownNumber> secondLevel : abc.getListAnswers()) {
                AnswerTuple<List<CountdownNumber>> abcd = reduce(secondLevel);
                if (abc.isAnswerFound()) {
                    return;
                }
                for (List<CountdownNumber> thirdLevel: abcd.getListAnswers()) {
                    AnswerTuple<List<CountdownNumber>> abcde = reduce(thirdLevel);
                    if (abcd.isAnswerFound()) {
                        return;
                    }
                    for (List<CountdownNumber> fourthLevel : abcde.getListAnswers()) {
                        calculateAndCheckForTarget(fourthLevel.getFirst(), fourthLevel.getLast());
                    }
                }
            }
        }
    }

    private AnswerTuple<List<CountdownNumber>> reduce(List<CountdownNumber> list) {
        List<CountdownNumber> listCopy = new ArrayList<>(list);

        AnswerTuple<CountdownNumber> calculated = calculateAndCheckForTarget(listCopy.removeFirst(), listCopy.remove(1));

        List<List<CountdownNumber>> timelines = new ArrayList<>();

        for (CountdownNumber answer : calculated.getListAnswers()) {
            List<CountdownNumber> timeLine = new ArrayList<>();
            timeLine.add(answer);
            timeLine.addAll(listCopy);
            timelines.add(timeLine);
        }

        return new AnswerTuple<>(timelines, calculated.isAnswerFound());
    }
}

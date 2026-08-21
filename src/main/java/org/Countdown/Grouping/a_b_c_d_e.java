package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;
import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public class a_b_c_d_e extends GroupingPatternOnSix {

    @Override
    public void execute() {

        for (List<CountdownNumber> firstLevel: reduce(getSearchList())) {
            for(List<CountdownNumber> secondLevel : reduce(firstLevel)) {
                for (List<CountdownNumber> thirdLevel: reduce(secondLevel)) {
                    for (List<CountdownNumber> fourthLevel : reduce(thirdLevel)) {
                        calculate(fourthLevel.getFirst(), fourthLevel.getLast());
                    }
                }
            }
        }
    }

    private static List<List<CountdownNumber>> reduce(List<CountdownNumber> list) {
        List<CountdownNumber> listCopy = new ArrayList<>(list);

        List<CountdownNumber> firstGroup = calculate(listCopy.removeFirst(), listCopy.remove(1));

        List<List<CountdownNumber>> timelines = new ArrayList<>();

        for (CountdownNumber answer : firstGroup) {
            List<CountdownNumber> timeLine = new ArrayList<>();
            timeLine.add(answer);
            timeLine.addAll(listCopy);
            timelines.add(timeLine);
        }

        return timelines;

    }
}

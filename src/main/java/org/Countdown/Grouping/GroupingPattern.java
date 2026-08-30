package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

import static org.Countdown.Number.CountDownNumberCalculator.calculate;

public abstract class GroupingPattern implements CountDownExecutable {
    private final List<CountdownNumber> searchList = new ArrayList<>();
    protected final AnswerChecker checker;

    GroupingPattern() {
        this.checker = new AnswerChecker();
    }

    protected GroupingPattern(List<CountdownNumber> list, AnswerChecker checker) {
        this.searchList.addAll(list);
        this.checker = checker;
    }

    public List<CountdownNumber> getSearchList() {
        return new ArrayList<>(searchList);
    }

    public AnswerTuple<CountdownNumber> calculateAndCheckForTarget(CountdownNumber a, CountdownNumber b) {
        List<CountdownNumber> results = calculate(a, b);
        boolean bool = this.checker.checkAndAddAnswerToBank(results);

        return new AnswerTuple<>(results, bool);
    }

}

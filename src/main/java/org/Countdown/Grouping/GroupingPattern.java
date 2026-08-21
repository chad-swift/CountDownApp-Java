package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupingPattern implements CountDownExecutable {
    private final List<CountdownNumber> searchList = new ArrayList<>();
    private final List<List<CountdownNumber>> results = new ArrayList<>();

    GroupingPattern() {
    }

    protected GroupingPattern(List<CountdownNumber> list) {
        searchList.addAll(list);
    }

    public List<List<CountdownNumber>> getResults() {
        List<List<CountdownNumber>> resultsCopy = new ArrayList<>();

        for (List<CountdownNumber> list : results) {
            results.add(new ArrayList<>(list));
        }

        return resultsCopy;
    }

    public List<CountdownNumber> getSearchList() {
        return new ArrayList<>(searchList);
    }
}

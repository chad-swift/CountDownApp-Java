package org.Countdown.Grouping;

public abstract class GroupingPatternOnSix extends GroupingPattern {
    @Override
    public void checkList() throws CountdownException {
        if (getSearchList().size() > 6) {
            throw new CountdownException("This level of GroupingPattern must have 6 digits");
        }
    }
}

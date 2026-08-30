package org.Countdown.Grouping;

import java.util.ArrayList;
import java.util.List;

public class AnswerTuple<T> {
    private final List<T> listAnswers = new ArrayList<>();
    private final boolean answerFound;

    public AnswerTuple() {
        this.answerFound = false;
    }

    public AnswerTuple(List<T> list, boolean answerFound) {
        this.listAnswers.addAll(list);
        this.answerFound = answerFound;
    }

    public List<T> getListAnswers() {
         return new ArrayList<>(listAnswers);
    }

    public boolean isAnswerFound() {
        return answerFound;
    }
}

package org.Countdown.Grouping;

import java.util.ArrayList;
import java.util.List;

public class AnswerBank {
    List<List<String>> bank = new ArrayList<>();

    public AnswerBank() {}

    public void addToBank(List<String> calculationHistory) {
        bank.add(calculationHistory);
    }

    public List<List<String>> getBank() {
        return new ArrayList<>(bank);
    }

}

package org.Countdown.Grouping;

import org.Countdown.Number.CountdownNumber;

import java.util.List;

public class AnswerChecker {
    private final AnswerBank bank = new AnswerBank();
    private final CountdownNumber target;

    public AnswerChecker() {
        this.target = new CountdownNumber(100);
    }

    public AnswerChecker(CountdownNumber target) {
        this.target = target;
    }

    public boolean checkAndAddAnswerToBank(List<CountdownNumber> listOfPossibleAnswers) {
        for (CountdownNumber answer : listOfPossibleAnswers) {
            if (answer.equals(target)) {
                bank.addToBank(answer.getCalculationHistory());
                return true;
            }
        }
        return false;
    }
}

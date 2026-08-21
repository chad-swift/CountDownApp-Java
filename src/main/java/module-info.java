module org.example.countdownapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires combinatoricslib3;


    opens org.Countdown to javafx.fxml;
    exports org.Countdown;
    exports org.Countdown.Grouping;
    opens org.Countdown.Grouping to javafx.fxml;
    exports org.Countdown.Number;
    opens org.Countdown.Number to javafx.fxml;
}
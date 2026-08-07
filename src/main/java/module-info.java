module org.example.countdownapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires combinatoricslib3;


    opens org.example.countdownapp to javafx.fxml;
    exports org.example.countdownapp;
}
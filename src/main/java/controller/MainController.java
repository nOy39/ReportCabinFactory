package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXTimePicker startTimePeriod;

    @FXML
    private JFXButton buttnReport;

    @FXML
    void initialize() {
        buttnReport.setOnAction(event -> {
            System.out.println(startDate.getValue());
        });

    }
}


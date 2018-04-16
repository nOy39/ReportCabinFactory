package controller;

import anim.Shake;
import com.jfoenix.controls.*;
import dbhandler.ConnectDB;
import dbhandler.DBConnect;
import dbhandler.ReadBase;
import helpers.Person;
import helpers.Refrigirators;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TimeController implements Initializable {

    private String startPeriodDate;
    private String endPerirodDate;
    private String startPeriodTime;
    private String endPeriodTime;

    private String querry = "";

    private Connection con = null;
    private PreparedStatement prSt = null;
    private ResultSet resultSet = null;

    public Refrigirators ref;
    public ReadBase rb;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private ObservableList<Refrigirators> refList;

    private ObservableList<Person> personList;

    @FXML
    private JFXTimePicker startTime;


    @FXML
    private JFXTimePicker endTime;

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXButton btnOk;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<Refrigirators> tableOutput;

    @FXML
    private TableColumn<Refrigirators, String> columnId;

    @FXML
    private TableColumn<Refrigirators, String> columnModel;

    @FXML
    private TableColumn<Refrigirators, String> columnDate;

    @FXML
    private TableColumn<Refrigirators, String> columnTime;

    @FXML
        private TableColumn<Refrigirators, String> columnPolExpand;

    @FXML
    private TableColumn<Refrigirators, String> columnIsoExpand;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnExit;

    @FXML
    private Label lblTips;

    @FXML
    void btnOkHandler(ActionEvent event) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        startTime.setIs24HourView(true);
        endTime.setIs24HourView(true);

        int count = 0;
        btnOk.setOnAction(event -> {

            try {
                startPeriodDate = startDate.getValue().toString().trim();
            } catch (NullPointerException ex) {
                animShake(startDate);
                showTips("Выберите дату формирования отчета");
                startPeriodDate = "";
            }

            try {
                endPerirodDate = endDate.getValue().toString().trim();
            } catch (NullPointerException ex) {
                System.out.println(ex);
                endPerirodDate = "";
            }

            try {
                startPeriodTime = startTime.getValue().toString().trim();
            } catch (NullPointerException ex) {
                startPeriodTime = "";
            }

            try {
                endPeriodTime = endTime.getValue().toString().trim();
            } catch (NullPointerException ex) {
                endPeriodTime = "";
            }

            if (!startPeriodDate.equals("")) {
                sendQuerryMessage(startPeriodDate,endPerirodDate,startPeriodTime,endPeriodTime);
                ConnectDB.openConnection();
                try {
                    loadData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        btnCancel.setOnAction(event -> {

        });

        btnExit.setOnAction(event -> System.exit(3));
    }

    private String sendQuerryMessage(String startPeriodDay, String endPeriodDay,
                                     String startHour, String endHour) {


        if (!endPeriodDay.equals("")) {
            querry = "(date >= \""+startPeriodDay+"\" AND date<= \""+endPeriodDay+"\")";
        } else if (!startPeriodDay.equals("") && !startHour.equals("")
                && endHour.equals("") && endPeriodDay.equals("")) {
            querry = "(date = \""+startPeriodDay+"\" AND time >= \""+startHour+":00\")";

        } else if (!startPeriodDay.equals("") && !startHour.equals("")
                && !endHour.equals("") && endPeriodDay.equals("")) {
            querry = "(date = \""+startPeriodDay+"\" AND time >= \""+startHour+
                    ":00\" AND time <= \""+endHour+":00\")";

        } else {
            querry = "(date = \""+startPeriodDay+"\")";
        }

        System.out.println(querry);
        return querry;
    }

    private void setCellTable() {
        setCellTable();
        columnModel.setCellValueFactory(new PropertyValueFactory<>("modelBar"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnPolExpand.setCellValueFactory(new PropertyValueFactory<>("poliol_expand"));
        columnIsoExpand.setCellValueFactory(new PropertyValueFactory<>("iso_expand"));
    }

    private void loadData() throws SQLException {
        int count = 0;
        ConnectDB.openConnection();
        refList = FXCollections.observableArrayList();
        String sqlQuerry = "SELECT prog_code, date, time, " +
                "pol_expand, iso_expand FROM output WHERE"+querry;
        System.out.println(querry);
        try {
            ResultSet rs = ConnectDB.connection.createStatement().executeQuery("SELECT prog_code, date, time, " +
                    "pol_expand, iso_expand FROM output WHERE"+querry);
            while (rs.next()) {
                refList.add(new Refrigirators(++count,
                        rs.getString("prog_code"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("pol_expand"),
                        rs.getString("iso_expand")));
                System.out.println(refList.toString());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        columnId.setCellValueFactory(new PropertyValueFactory<Refrigirators, String>("id"));
        columnModel.setCellValueFactory(new PropertyValueFactory<Refrigirators, String>("modelBar"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Refrigirators, String>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<Refrigirators, String>("time"));
        columnPolExpand.setCellValueFactory(new PropertyValueFactory<Refrigirators,String>("poliol_expand"));
        columnIsoExpand.setCellValueFactory(new PropertyValueFactory<Refrigirators,String>("iso_expand"));
        tableOutput.setItems(refList);

        showTips("Всего: "+count+"шт.");
    }


    @FXML
    void showStartDateTips(MouseEvent event) {
        String msg = "Начало периода формирования отчета";
        showTips(msg);
    }

    @FXML
    void hideStartDateTips(MouseEvent event) {
        hideTips();
    }

    @FXML
    void showEndDateTips(MouseEvent event) {
        String msg = "Конец периода формирования отчета";
        showTips(msg);
    }

    @FXML
    void hideEndDateTips(MouseEvent event) {
        hideTips();
    }

    @FXML
    void showStartTimeTips(MouseEvent event) {
        String msg = "Начальное время для формирование почасового отчета";
        showTips(msg);
    }

    @FXML
    void hideStartTimeTips(MouseEvent event) {
        hideTips();
    }

    @FXML
    void showEndTimeTips(MouseEvent event) {
        String msg = "Конечное время для формирование почасового отчета";
        showTips(msg);
    }

    @FXML
    void hideEndTimeTips(MouseEvent event) {
        hideTips();
    }

    private void animShake(Node node) {
        Shake shake = new Shake(node);
        shake.playAnim();
    }

    public void showTips(String msg) {
        lblTips.setText(msg);
    }

    public void hideTips() {
        lblTips.setText("");
    }

}

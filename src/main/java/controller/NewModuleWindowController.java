package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewModuleWindowController {
    @FXML
    private TextField titleTF, creditTF, leaderTF, moduleTF;
    @FXML
    private ComboBox levelCB, startCB, endCB, courseCB;
    @FXML
    private TextArea descTA, aimTA;
    @FXML
    private Button saveBtn;

    public void createModule() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO modules(module_code, start_year, end_year, level, credits, title, leader, description, aims_and_objectives, course) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.setString(1, moduleTF.getText());
        preparedStatement.setString(2, startCB.getValue().toString());
        preparedStatement.setString(3, endCB.getValue().toString());
        preparedStatement.setString(4, levelCB.getValue().toString());
        preparedStatement.setString(5, creditTF.getText());
        preparedStatement.setString(6, titleTF.getText());
        preparedStatement.setString(7, leaderTF.getText());
        preparedStatement.setString(8, descTA.getText());
        preparedStatement.setString(9, aimTA.getText());
        preparedStatement.setString(10, courseCB.getValue().toString());
        preparedStatement.executeUpdate();
        System.out.println("Module created!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void populateCB() {
        levelCB.getItems().addAll("4", "5", "6");
        courseCB.getItems().addAll("CSY2028", "CSY9999");
        populateYearCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            startCB.getItems().addAll(String.valueOf(i));
            endCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void generateCode() {
        int code = (int) (Math.random()*3000);
        moduleTF.setText("CSY" + code);
    }
}

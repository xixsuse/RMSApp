package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class NewAssessmentWindowController {
    @FXML
    private TextField termTF, weightTF, briefTF;
    @FXML
    private ComboBox moduleCB, typeCB, statusCB, courseCB, yearCB;
    @FXML
    private Button saveBtn;

    public void createAssessment() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO assessments(module_code, type, term, weighting, brief, status, year, course_code) VALUES (?,?,?,?,?,?,?,?)";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.setString(1, moduleCB.getValue().toString());
        preparedStatement.setString(2, typeCB.getValue().toString());
        preparedStatement.setString(3, termTF.getText());
        preparedStatement.setString(4, weightTF.getText());
        preparedStatement.setString(5, briefTF.getText());
        preparedStatement.setString(6, statusCB.getValue().toString());
        preparedStatement.setString(7, yearCB.getValue().toString());
        preparedStatement.setString(8, courseCB.getValue().toString());
        preparedStatement.executeUpdate();
        System.out.println("Course created!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    public void populateCB() throws SQLException {
        populateYearCB();
        populateModuleCB();
        populateCourseCB();
        populateTypeCB();
        populateStatusCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            yearCB.getItems().addAll(String.valueOf(i));
        }
    }

    private void populateModuleCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules");
        while (result.next()) {
            moduleCB.getItems().addAll(result.getString("module_code"));
        }
    }

    private void populateTypeCB() {
        typeCB.getItems().addAll("TCA", "Essay", "Assignment");
    }

    private void populateCourseCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT course FROM modules");
        while (result.next()) {
            courseCB.getItems().addAll(result.getString("course"));
        }
    }

    private void populateStatusCB() {
        statusCB.getItems().addAll("Pending", "Released", "Terminated");
    }
}

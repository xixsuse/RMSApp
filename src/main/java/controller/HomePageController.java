package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Course;
import model.Session;
import model.Staff;
import view.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

public class HomePageController {
    @FXML
    private Text loginLabel;
    @FXML
    private Text lastLogLabel;
    @FXML
    private Text dateLabel;
    @FXML
    public TabPane homePageTabPane;
    @FXML
    private Button logoutBtn;
    private FXMLLoader loader;
    public ArrayList<Tab> tabArrayList = new ArrayList<>();
    private int currentTabView;


    public void setLoginUsername(String name) {
        loginLabel.setText(name);
    }
    public void setLastLogLabel(String timestamp) {
        lastLogLabel.setText(timestamp);
    }
    public void setDateLabel() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM-dd");
        String dateForLabel = formatDate.format(cal.getTime());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
                dateLabel.setText(dateForLabel + " " + string);
            }
        }, 0, 1000);
    }

    public void createHomeTab(Session session) throws IOException {
        Tab homeTab = new Tab();
        if (session.getAccessLevel().hasAccessToSysAdminView() || session.getAccessLevel().hasAccessToRecView()) {
            loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaffTab.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutorTab.fxml"));
        }
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void createStudentTab() throws IOException, SQLException {
        new StudentTab(homePageTabPane, loader);
    }

    public void createStaffTab() throws IOException, SQLException {
        new StaffTab(homePageTabPane, loader);
    }

    public void createModuleTab() throws IOException, SQLException {
        new ModuleTab(homePageTabPane, loader);
    }

    public void createCourseTab() throws IOException, SQLException {
        new CourseTab(homePageTabPane, loader);
    }

    public void createAssessmentTabRS() throws IOException, SQLException {
        new AssessmentTab(homePageTabPane, loader);
    }

    public void createAssessmentTabT() throws IOException {
        Tab assessmentTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AssessmentsTabTutor.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        homePageTabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
    }

    public void createGradesTabRS() throws IOException, SQLException {
        new GradesTab(homePageTabPane, loader);
    }

    public void createGradesTabT() throws IOException {
        Tab gradesTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/GradesTabTutor.fxml"));
        AnchorPane gradesTabContent = loader.load();
        homePageTabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
    }

    public void createAttendanceTabRS() throws IOException {
        Tab attendanceTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabRecordsStaff.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        homePageTabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
    }

    public void createAttendanceTabT() throws IOException {
        Tab attendanceTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabTutor.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        homePageTabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
    }

    public void createTutorshipTabRS() throws IOException {
        Tab tutorshipTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipRecordsStaffTab.fxml"));
        AnchorPane tutorshipTabContent = loader.load();
        homePageTabPane.getTabs().add(tutorshipTab);
        tutorshipTab.setText("Personal Tutorship");
        tutorshipTab.setContent(tutorshipTabContent);
    }

    public void createTutorshipTabT() throws IOException {
        Tab tutorshipTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipTutorTab.fxml"));
        AnchorPane tutorshipTabContent = loader.load();
        homePageTabPane.getTabs().add(tutorshipTab);
        tutorshipTab.setText("Personal Tutorship");
        tutorshipTab.setContent(tutorshipTabContent);
    }

    public void createNotifTab() throws IOException {
        Tab notificationTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/NotificationsTab.fxml"));
        AnchorPane notificationTabContent = loader.load();
        homePageTabPane.getTabs().add(notificationTab);
        notificationTab.setText("Notifications");
        notificationTab.setContent(notificationTabContent);
    }

    public void createDiaryTab() throws IOException {
        Tab diaryTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/DiaryTab.fxml"));
        AnchorPane diaryTabContent = loader.load();
        homePageTabPane.getTabs().add(diaryTab);
        diaryTab.setText("Diary");
        diaryTab.setContent(diaryTabContent);
    }

    public void createCalendar() throws IOException {
        FXMLLoader calendarLoader = new FXMLLoader();
        calendarLoader.setLocation(getClass().getResource("/FXMLview/CalendarView.fxml"));
        Scene scene = new Scene(calendarLoader.load(), 800,800);
        Stage stage = new Stage();
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Object temp = calendarLoader.getController();
        CalendarController controller = (CalendarController) temp;
        controller.calendarPane.getChildren().add(new CalendarView(YearMonth.now()).getView());
    }

    public void createHomeTabRS() throws IOException {
        Tab homeTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaffTab.fxml"));
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void createHomeTabCL() throws IOException {
        Tab homeTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutor.fxml"));
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void createHomeTabT() throws IOException {
        Tab homeTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutor.fxml"));
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void refresh() throws IOException, SQLException {
        System.out.println("Refresh method called.");
        for (int i = 0; i < homePageTabPane.getTabs().size(); i++) {
            tabArrayList.add(homePageTabPane.getTabs().get(i));
        }
        currentTabView = homePageTabPane.getSelectionModel().getSelectedIndex();
        homePageTabPane.getTabs().clear();
        for (Tab tab: tabArrayList) {
            if (tab instanceof StudentTab) {
                StudentTab st = new StudentTab(homePageTabPane, loader);
            } else if (tab instanceof StaffTab) {
                StaffTab staffTab = new StaffTab(homePageTabPane, loader);
            } else if (tab instanceof  ModuleTab) {
                ModuleTab moduleTab = new ModuleTab(homePageTabPane, loader);
            } else if (tab instanceof CourseTab) {
                CourseTab courseTab = new CourseTab(homePageTabPane, loader);
            } else if (tab instanceof AssessmentTab) {
                AssessmentTab assessmentTab = new AssessmentTab(homePageTabPane, loader);
            } else if (tab instanceof GradesTab) {
                GradesTab gradesTab = new GradesTab(homePageTabPane, loader);
            } else {
                homePageTabPane.getTabs().add(tab);
            }
            homePageTabPane.getSelectionModel().select(currentTabView);
        }
        tabArrayList.removeAll(tabArrayList);
    }

    public void endSession() throws IOException {
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
        Parent loginPage = FXMLLoader.load(getClass().getResource("/FXMLview/Login.fxml"));
        Scene scene = new Scene(loginPage);
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
        stage.setTitle("Woodlands RMS");
        stage.show();
    }
}


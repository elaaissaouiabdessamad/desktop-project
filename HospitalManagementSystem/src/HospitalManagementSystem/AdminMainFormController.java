/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class AdminMainFormController implements Initializable {

    
     @FXML
    private Button appointment_btn;

    @FXML
    private Label current_form;

    @FXML
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TD;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private BarChart<?, ?> dashboard_char_PD;

    @FXML
    private BarChart<?, ?> dashboard_chart_DD;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label date_time;

    @FXML
    private Button doctors_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_AdminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button patients_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Label top_username;
    
    @FXML
    private Button logout_btn;


    
  
    
   
  public void runTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    Thread thread = new Thread(() -> {
        try {
            while (true) {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    String formattedDateTime = now.format(formatter);
                    date_time.setText(formattedDateTime);
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
    thread.setDaemon(true);
    thread.start();
  
}
  
 

public void loadCrudPatientForm() throws IOException 
{
    Parent root = FXMLLoader.load(getClass().getResource("PatientCrudForm.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setTitle("Patient Crud Form");
    stage.setScene(scene);
   
     Image icon = new Image(getClass().getResourceAsStream("patient.png"));
     stage.getIcons().add(icon);
    stage.show();
}

public void loadCrudDoctorForm() throws IOException 
{
    Parent root = FXMLLoader.load(getClass().getResource("DoctorCrudForm.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setTitle("Doctor Crud Form");
    stage.setScene(scene);
   
     Image icon = new Image(getClass().getResourceAsStream("doctor.png"));
     stage.getIcons().add(icon);
     stage.show();
}

public void loadCrudAppointmentForm() throws IOException 
{
   
    Parent root = FXMLLoader.load(getClass().getResource("AppointmentCrudForm.fxml"));
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setTitle("Appointment Crud Form");
    stage.setScene(scene);
    Image icon = new Image(getClass().getResourceAsStream("appointment.png"));
    stage.getIcons().add(icon);
    stage.show();
}


public void logOut() throws IOException
{
    logout_btn.getScene().getWindow().hide();
    
    
    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setMinWidth(330);
    stage.setMinHeight(530);
    stage.setTitle("Hospital Management System");
    Image icon = new Image(getClass().getResourceAsStream("patient.png"));
    stage.getIcons().add(icon);
    stage.show();
    
    
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        runTime();
      
    }
    
    
    
}

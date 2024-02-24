/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

/**
 *
 * @author hp
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javax.management.remote.JMXConnectorFactory.connect;



public class AppointmentCrudControlller implements Initializable{
    
    
     @FXML
    private TextField app_ID;

    @FXML
    private Button app_addBtn;

    @FXML
    private Button app_clearBtn;

    @FXML
    private DatePicker app_date;

    @FXML
    private Button app_deleteBtn;

    @FXML
    private ComboBox<?> app_doctor_number;

    @FXML
    private ComboBox<?> app_patient_number;

    @FXML
    private TextField app_search;

    @FXML
    private TableView<AppointmentData> app_tableView;

    @FXML
    private ComboBox<?> app_time;

    @FXML
    private TextField app_treatment;

    @FXML
    private Button app_updateBtn;

    @FXML
    private TableColumn<AppointmentData, String> col_appID;

    @FXML
    private TableColumn<AppointmentData, String> col_date;

    @FXML
    private TableColumn<AppointmentData, String> col_doctorID;

    @FXML
    private TableColumn<AppointmentData, String> col_doctorName;

    @FXML
    private TableColumn<AppointmentData, String> col_patientID;

    @FXML
    private TableColumn<AppointmentData, String> col_patientName;

    @FXML
    private TableColumn<AppointmentData, String> col_time;

    @FXML
    private TableColumn<AppointmentData, String> col_treatment;
    
    
    private  ObservableList<AppointmentData> appointmentData ;
    
    private String[] timeList = {"8h - 9h","9h - 10h","10h - 11h","11h - 12h","15h - 16h","16h - 17h"};
    
    public void timeList()
    {
        List<String> tList = new ArrayList<>();
        for(String time : timeList)
        {
            tList.add(time);
        }
        
        ObservableList listData = FXCollections.observableArrayList(tList);
        app_time.setItems(listData);
    }
    
    public void patientsIdList()
    {
        List<String> tList = getPatientsID();
        ObservableList listData = FXCollections.observableArrayList(tList);
        app_patient_number.setItems(listData);
    }
    
     public void doctorsIdList()
    {
        List<String> tList = getDoctorsID();
        ObservableList listData = FXCollections.observableArrayList(tList);
        app_doctor_number.setItems(listData);
    }
    
    public List<String> getPatientsID() 
    {
        List<String> tList = new ArrayList<>();
        
        String selectData = "SELECT * FROM patient";
          
         connect = DataBase.connectDB(); 
         
            try
            {
                prepare = connect.prepareStatement(selectData);
                result = prepare.executeQuery();


                while(result.next())
                {
                    tList.add(String.valueOf(result.getInt("patient_number")));
                }
            }
            catch(Exception e){e.printStackTrace();}
         
        return tList ;
    }
    
    public List<String> getDoctorsID() 
    {
        List<String> tList = new ArrayList<>();
        
        String selectData = "SELECT * FROM doctor";
          
         connect = DataBase.connectDB(); 
         
            try
            {
                prepare = connect.prepareStatement(selectData);
                result = prepare.executeQuery();


                while(result.next())
                {
                    tList.add(String.valueOf(result.getInt("doctor_number")));
                }
            }
            catch(Exception e){e.printStackTrace();}
         
        return tList ;
    }
            
    private Connection connect ;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result; 
    
    
    public ObservableList<AppointmentData> appSearchListData()
    {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();
        
       
        connect = DataBase.connectDB();
        String selectData = "SELECT * FROM appointment WHERE appointment_id = ?"
        + " OR patient_id LIKE ?"
        + " OR patient_name LIKE ?"
        + " OR doctor_id = ?"
        + " OR doctor_name LIKE ?"
        + " OR treatment LIKE ?"
        //+ " OR date = ?"
        + " OR time LIKE ?";
       
        
        
        
        try
        {
             
            prepare = connect.prepareStatement(selectData);

            prepare.setString(1, app_search.getText());
            prepare.setString(2, "%" + app_search.getText() + "%");
            prepare.setString(3, "%" + app_search.getText() + "%");
            prepare.setString(4, app_search.getText());
            prepare.setString(5, "%" + app_search.getText() + "%");
            prepare.setString(6, "%" + app_search.getText() + "%");
           // prepare.setString(7, app_search.getText());
            prepare.setString(7, "%" + app_search.getText() + "%");
           
            result = prepare.executeQuery();
            
            AppointmentData appData ;
            
            while(result.next())
            {
                appData = new AppointmentData(result.getInt("appointment_id"),result.getInt("patient_id"),result.getString("patient_name"),result.getInt("doctor_id"),result.getString("doctor_name"),result.getString("treatment"),result.getDate("date"),result.getString("time"));  
                listData.add(appData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
    
    
    public void appSearchShowData()
    {
        appointmentData = appSearchListData();
        
        col_appID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        col_patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        col_doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
          
        
        app_tableView.setItems(appointmentData);
    }
    
    
    public void show()
    {
        if(app_search.getText().isEmpty())
        {
            appShowData();
        }
        else
        {
            appSearchShowData();
        }
    }
    
    
     public ObservableList<AppointmentData> appListData()
    {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();
        
        String selectData = "SELECT * FROM appointment";
        
        connect = DataBase.connectDB(); 
        try
        {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            
            AppointmentData appData ;
            
            while(result.next())
            {
                appData = new AppointmentData(result.getInt("appointment_id"),result.getInt("patient_id"),result.getString("patient_name"),result.getInt("doctor_id"),result.getString("doctor_name"),result.getString("treatment"),result.getDate("date"),result.getString("time"));  
                listData.add(appData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
     
     
       public void appShowData()
    {
        appointmentData = appListData();
        
        col_appID.setCellValueFactory(new PropertyValueFactory<>("appID"));
        col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        col_patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        col_doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
          
        
        app_tableView.setItems(appointmentData);
        
    
    }
    

       
       public void appAddBtn() 
       {
            
            connect = DataBase.connectDB();
            
            try 
            {
                if(app_ID.getText().isEmpty() || app_patient_number.getSelectionModel().getSelectedItem() == null
                        || app_doctor_number.getSelectionModel().getSelectedItem() == null
                        || app_treatment.getText().isEmpty() 
                        || app_date.getValue() == null || app_time.getSelectionModel().getSelectedItem() == null)
                {
                    new AlertMessage().errorMessage("Please Fill all bkank fields"); 
                }
                else
                    
                {
                    String checkData = "SELECT appointment_id FROM appointment WHERE appointment_id = ?";
                    
                    prepare = connect.prepareStatement(checkData);
                    prepare.setString(1, app_ID.getText());
                    result = prepare.executeQuery();
                    
                     if(result.next())
                     {
                        new AlertMessage().errorMessage("Appointment Number '" + app_ID.getText() + "' is Already Exist !"); 
                     }
                     else
                     {
                        checkData = "SELECT * FROM appointment WHERE (patient_id = ? OR doctor_id = ?) AND date = ? AND time = ?";
                        prepare = connect.prepareStatement(checkData);
                        
                        prepare.setString(1, (String) app_patient_number.getSelectionModel().getSelectedItem());
                        prepare.setString(2, (String) app_doctor_number.getSelectionModel().getSelectedItem());
                        prepare.setDate(3, java.sql.Date.valueOf(app_date.getValue()));
                        prepare.setString(4, (String) app_time.getSelectionModel().getSelectedItem());
               
                        result = prepare.executeQuery();
                        
                         if(result.next())
                         {
                           new AlertMessage().errorMessage("Add Appointment Failed !"); 
                         }
                         else
                         {
                             String getData = "SELECT * FROM patient WHERE patient_number = ?" ;
                             prepare = connect.prepareStatement(getData);
                             prepare.setString(1, (String) app_patient_number.getSelectionModel().getSelectedItem());
                             result = prepare.executeQuery();
                             
                             String patientFullName = "" ;
                             
                             while(result.next())
                             {
                                 patientFullName = result.getString("first_name") + " " + result.getString("last_name");
                             }
                             
                            
                             getData = "SELECT * FROM doctor WHERE doctor_number = ?" ;
                             prepare = connect.prepareStatement(getData);
                             prepare.setString(1, (String)app_doctor_number.getSelectionModel().getSelectedItem());
                             result = prepare.executeQuery();
                             String doctorFullName = "" ;
                             while(result.next())
                             {
                                 doctorFullName = result.getString("first_name") + " " + result.getString("last_name");
                             }
                             
            
                             
                             String insertData = "INSERT INTO appointment (appointment_id , patient_id , patient_name , doctor_id , doctor_name , treatment , date , time) " 
                              + "VALUES(?,?,?,?,?,?,?,?)";
                              
                            prepare = connect.prepareStatement(insertData);
                    
                            prepare.setString(1, app_ID.getText());
                            prepare.setString(2, (String) app_patient_number.getSelectionModel().getSelectedItem());
                            prepare.setString(3,patientFullName);
                            prepare.setString(4, (String) app_doctor_number.getSelectionModel().getSelectedItem());
                            prepare.setString(5,doctorFullName);
                            prepare.setString(6, app_treatment.getText());
                            prepare.setDate(7, java.sql.Date.valueOf(app_date.getValue()));
                            prepare.setString(8, (String) app_time.getSelectionModel().getSelectedItem());
                            

                            
                            prepare.executeUpdate();
                            new AlertMessage().successMessage("Appointment Added Successfully !");
                            appShowData();
                            appClearBtn();
                         }
                        
                     }
                    
                }
            }catch(Exception e){e.printStackTrace();}
            
        
       }
       
       
       
       public void appSelectData()
    {
        AppointmentData appData =  app_tableView.getSelectionModel().getSelectedItem();
        int num =  app_tableView.getSelectionModel().getSelectedIndex();
        
        if(num - 1 < -1) return ;
        
        app_ID.setText(String.valueOf(appData.getAppID()));
        app_treatment.setText(appData.getTreatment());

    }

        
        
      public void appUpdateBtn()
      {
           connect = DataBase.connectDB();
          try
          {
               if(app_ID.getText().isEmpty() || app_patient_number.getSelectionModel().getSelectedItem() == null
                        || app_doctor_number.getSelectionModel().getSelectedItem() == null
                        || app_treatment.getText().isEmpty() 
                        || app_date.getValue() == null || app_time.getSelectionModel().getSelectedItem() == null)
                {
                    new AlertMessage().errorMessage("Please Fill all bkank fields"); 
                }
               else
               {
                    String checkData = "SELECT appointment_id FROM appointment WHERE appointment_id = ?";
                    
                    prepare = connect.prepareStatement(checkData);
                    prepare.setString(1, app_ID.getText());
                    result = prepare.executeQuery();
                    
                     if(!result.next())
                     {
                        new AlertMessage().errorMessage("Appointment With Number '" + app_ID.getText() + "' is Not Exist !"); 
                     }
                     else
                     {
                         
                        checkData = "SELECT * FROM appointment WHERE (patient_id = ? OR doctor_id = ?) AND date = ? AND time = ? AND appointment_id <> ?";
                        prepare = connect.prepareStatement(checkData);
                        
                        prepare.setString(1, (String) app_patient_number.getSelectionModel().getSelectedItem());
                        prepare.setString(2, (String) app_doctor_number.getSelectionModel().getSelectedItem());
                        prepare.setDate(3, java.sql.Date.valueOf(app_date.getValue()));
                        prepare.setString(4, (String) app_time.getSelectionModel().getSelectedItem());
                        prepare.setString(5,app_ID.getText());
               
                        result = prepare.executeQuery();
                        
                         if(result.next())
                         {
                           new AlertMessage().errorMessage("Update Appointment Failed !"); 
                         }
                         
                         else
                         {
                              String getData = "SELECT * FROM patient WHERE patient_number = ?" ;
                             prepare = connect.prepareStatement(getData);
                             prepare.setString(1, (String) app_patient_number.getSelectionModel().getSelectedItem());
                             result = prepare.executeQuery();
                             
                             String patientFullName = "" ;
                             
                             while(result.next())
                             {
                                 patientFullName = result.getString("first_name") + " " + result.getString("last_name");
                             }
                             
                            
                             getData = "SELECT * FROM doctor WHERE doctor_number = ?" ;
                             prepare = connect.prepareStatement(getData);
                             prepare.setString(1, (String)app_doctor_number.getSelectionModel().getSelectedItem());
                             result = prepare.executeQuery();
                             String doctorFullName = "" ;
                             while(result.next())
                             {
                                 doctorFullName = result.getString("first_name") + " " + result.getString("last_name");
                             }
                             
                             String insertData = "UPDATE appointment SET "
                                                + "patient_id = ?,"
                                                + "patient_name = ?,"
                                                + "doctor_id = ?,"
                                                + "doctor_name = ?,"
                                                + "treatment = ?,"
                                                + "date = ?,"
                                                + "time = ? WHERE appointment_id = ?";
                           
                              
                           prepare = connect.prepareStatement(insertData);

                            
                            prepare.setString(1, (String) app_patient_number.getSelectionModel().getSelectedItem());
                            prepare.setString(2, patientFullName);
                            prepare.setString(3, (String) app_doctor_number.getSelectionModel().getSelectedItem());
                            prepare.setString(4, doctorFullName);
                            prepare.setString(5, app_treatment.getText());
                            prepare.setDate(6, java.sql.Date.valueOf(app_date.getValue()));
                            prepare.setString(7, (String) app_time.getSelectionModel().getSelectedItem());
                            prepare.setString(8, app_ID.getText());  // Définition de l'ID de rendez-vous dans la condition WHERE

                            prepare.executeUpdate();


                            
                           
                            new AlertMessage().successMessage("Appointment Updated Successfully !");
                            appShowData();
                            appClearBtn();
                             
                         }
                         
                     }
               }
          }catch(Exception e){e.printStackTrace();}
      }
      
      
      
      public void appClearBtn()
      {
           app_ID.setText("");
           app_treatment.setText("");
           app_patient_number.getSelectionModel().clearSelection();
           app_doctor_number.getSelectionModel().clearSelection();
           app_time.getSelectionModel().clearSelection();
           app_date.setValue(null);
           
      }
       
      
      
      public void appDeleteBtn()
      {
           connect = DataBase.connectDB();
           
           try
           {
                if(app_ID.getText().isEmpty())
                {
                     new AlertMessage().errorMessage("Please Enter The Appointment ID You Want to delete !"); 
                }
                else
                {
                    String checkData = "SELECT appointment_id FROM appointment WHERE appointment_id = ?";
                    
                    prepare = connect.prepareStatement(checkData);
                    prepare.setString(1, app_ID.getText());
                    result = prepare.executeQuery();
                    
                     if(!result.next())
                     {
                        new AlertMessage().errorMessage("the Appointment With Number '" + app_ID.getText() + "' is Not Exist !"); 
                     }
                     else
                     {
                        if(new AlertMessage().confirmMessage("Are You Sure you want to Delete the Appointment with ID '" + app_ID.getText() + "' ?"))
                        {
                            String deleteQuery = "DELETE FROM appointment WHERE appointment_id = ?" ;
                            prepare = connect.prepareStatement(deleteQuery);
                            prepare.setString(1, app_ID.getText());
                            prepare.executeUpdate();
                            new AlertMessage().successMessage("The Appointment Deleted Successfully !");
                            appShowData();
                            appClearBtn(); 
                        }
                        else
                        {
                             new AlertMessage().errorMessage("Cancelled !");
                             appClearBtn(); 
                        }
                         
                     }
                }
           }
           catch(Exception e){e.printStackTrace();}
      }
    
      
      
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
            timeList();
            patientsIdList();
            doctorsIdList();
            appShowData();
    }



}

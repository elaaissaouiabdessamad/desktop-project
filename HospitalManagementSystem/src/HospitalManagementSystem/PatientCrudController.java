/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HospitalManagementSystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author hp
 */
public class PatientCrudController implements Initializable{

    
    
      @FXML
    private TableColumn<PatientData, String> col_patientAddress;

    @FXML
    private TableColumn<?, ?> col_patientAge;

    @FXML
    private TableColumn<PatientData, String> col_patientContactNumber;

    @FXML
    private TableColumn<PatientData, String> col_patientFirstName;

    @FXML
    private TableColumn<PatientData, String> col_patientLastName;

    @FXML
    private TableColumn<PatientData, String> col_patientNumber;

    @FXML
    private Button patient_addBtn;

    @FXML
    private TextField patient_address;

    @FXML
    private TextField patient_age;

    @FXML
    private Button patient_clearBtn;

    @FXML
    private TextField patient_contactNumber;

    @FXML
    private Button patient_deleteBtn;

    @FXML
    private TextField patient_firstName;
    
    @FXML
    private TextField patient_search;

    @FXML
    private TextField patient_lastName;

    @FXML
    private TextField patient_number;

    @FXML
    private TableView<PatientData> patient_tableView;

    @FXML
    private Button patient_updateBtn;
    
    private  ObservableList<PatientData> patientData ;
    
    private Connection connect ;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result; 
    
   
    
    public void patientAddBtn()
    {
        
        
        connect = DataBase.connectDB();
        try
        {
            if(patient_number.getText().isEmpty() || patient_firstName.getText().isEmpty() || patient_lastName.getText().isEmpty() || 
               patient_age.getText().isEmpty() || patient_contactNumber.getText().isEmpty() || patient_address.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Fill all bkank fields"); 
            }
            else
            {
                String checkData = "SELECT patient_number FROM patient where patient_number = " + patient_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    new AlertMessage().errorMessage("Patient Number '" + patient_number.getText() + "' is Already Exist !"); 
                }
                else
                {
                    String insertData = "INSERT INTO patient (patient_number , first_name , last_name , age , contact_number , address) " 
                          + "VALUES(?,?,?,?,?,?)";
                    
                    prepare = connect.prepareStatement(insertData);
                    
                    prepare.setString(1, patient_number.getText());
                    prepare.setString(2, patient_firstName.getText());
                    prepare.setString(3, patient_lastName.getText());
                    prepare.setString(4, patient_age.getText());
                    prepare.setString(5, patient_contactNumber.getText());
                    prepare.setString(6, patient_address.getText());

                    prepare.executeUpdate();
                    new AlertMessage().successMessage("Patient Added Successfully !");
                    patientShowData();
                    patientClearBtn();
                }
            }
            
        }
        catch(Exception e){e.printStackTrace();}
        
        
    }
    
     
    
    
    
    
    public ObservableList<PatientData> patientListDataSearchResult()
    {
        ObservableList<PatientData> listData = FXCollections.observableArrayList();
        
        String selectData = "SELECT * FROM patient WHERE patient_number = '" + patient_search.getText() + "'"
        + " OR first_name LIKE '%" + patient_search.getText() + "%'"
        + " OR last_name LIKE '%" + patient_search.getText() + "%'"
        + " OR age = '" + patient_search.getText() + "'"
        + " OR contact_number LIKE '%" + patient_search.getText() + "%'"
        + " OR address LIKE '%" + patient_search.getText() + "%'";
        
        connect = DataBase.connectDB(); 
        try
        {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            
            PatientData pData ;
            
            while(result.next())
            {
                pData = new PatientData(result.getInt("patient_number"),result.getString("first_name"),result.getString("last_name"),result.getInt("age"),result.getString("contact_number"),result.getString("address"));
            
                listData.add(pData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
    
    
    
    
    public ObservableList<PatientData> patientListData()
    {
        ObservableList<PatientData> listData = FXCollections.observableArrayList();
        
        String selectData = "SELECT * FROM patient";
        
        connect = DataBase.connectDB(); 
        try
        {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            
            PatientData pData ;
            
            while(result.next())
            {
                pData = new PatientData(result.getInt("patient_number"),result.getString("first_name"),result.getString("last_name"),result.getInt("age"),result.getString("contact_number"),result.getString("address"));
            
                listData.add(pData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
    
    
    
    
    
    
    
    public void patientShowData()
    {
        patientData = patientListData();
        col_patientNumber.setCellValueFactory(new PropertyValueFactory<>("patientNumber"));
        col_patientFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_patientLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_patientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_patientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_patientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        patient_tableView.setItems(patientData);
        
    
    }
    
     public void SearchchResultpatientShowData()
    {
        patientData = patientListDataSearchResult();
        col_patientNumber.setCellValueFactory(new PropertyValueFactory<>("patientNumber"));
        col_patientFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_patientLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_patientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_patientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_patientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        patient_tableView.setItems(patientData);
        
    
    }
     
    public void show()
    {
        if(patient_search.getText().isEmpty())
        {
            patientShowData();
        }
        else
        {
            SearchchResultpatientShowData();
        }
    }
    
    public void patientSelectData()
    {
        PatientData pData =  patient_tableView.getSelectionModel().getSelectedItem();
        int num =  patient_tableView.getSelectionModel().getSelectedIndex();
        
        if(num - 1 < -1) return ;
        
        patient_number.setText(String.valueOf(pData.getPatientNumber()));
        patient_firstName.setText(pData.getFirstName());
        patient_lastName.setText(pData.getLastName());
        patient_age.setText(String.valueOf(pData.getAge()));
        patient_contactNumber.setText(pData.getContactNumber());
        patient_address.setText(pData.getAddress());
        
        
    }
    
    public void patientUpdateBtn()
    {
        connect = DataBase.connectDB();
        
        try
        {
            if(patient_number.getText().isEmpty() || patient_firstName.getText().isEmpty() || patient_lastName.getText().isEmpty() || 
               patient_age.getText().isEmpty() || patient_contactNumber.getText().isEmpty() || patient_address.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Fill all bkank fields"); 
            }
            else
            {
                String checkData = "SELECT patient_number FROM patient where patient_number = " + patient_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    
                    if(new AlertMessage().confirmMessage("Are You Sure you want to update the Patient with number '" + patient_number.getText() + "' ?"))
                    {
                         String updateData = "UPDATE patient SET "
                         + "first_name = '" + patient_firstName.getText()
                         + "', last_name = '" + patient_lastName.getText()
                         + "', age = '" + patient_age.getText()
                         + "', contact_number = '" + patient_contactNumber.getText()
                         + "', address = '" + patient_address.getText() + "' WHERE patient_number = " + patient_number.getText() ;
                 
                         prepare = connect.prepareStatement(updateData);
                         prepare.executeUpdate();
                         new AlertMessage().successMessage("The Patient Updated Successfully !");
                         patientShowData();
                         patientClearBtn(); 
                    }
                    else
                    {
                        new AlertMessage().errorMessage("Cancelled !");
                         patientClearBtn(); 
                        
                    }
                    
                    
                }
                else
                {
                     new AlertMessage().errorMessage("The Patient With Number '" + patient_number.getText() + "' is Not Exist !"); 
                }
                 
                
                 
                 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
     public void patientDeleteBtn()
    {
         connect = DataBase.connectDB();
        
        try
        {
            if(patient_number.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Enter The Patient Number You want To Delete !"); 
            }
            else
            {
                String checkData = "SELECT patient_number FROM patient where patient_number = " + patient_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    
                    if(new AlertMessage().confirmMessage("Are You Sure you want to Delete the Patient with number '" + patient_number.getText() + "' ?"))
                    {
                         String deleteData = "DELETE FROM patient WHERE patient_number = " + patient_number.getText() ;
                 
                         prepare = connect.prepareStatement(deleteData);
                         prepare.executeUpdate();
                         new AlertMessage().successMessage("The Patient Deleted Successfully !");
                         patientShowData();
                         patientClearBtn(); 
                    }
                    else
                    {
                        new AlertMessage().errorMessage("Cancelled !");
                         patientClearBtn(); 
                    }
                    
                    
                }
                else
                {
                     new AlertMessage().errorMessage("The Patient With Number '" + patient_number.getText() + "' is Not Exist !"); 
                }
                 
                
                 
                 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
     
      public void patientClearBtn()
    {
        patient_number.setText("");
        patient_firstName.setText("");
        patient_lastName.setText("");
        patient_age.setText("");
        patient_contactNumber.setText("");
        patient_address.setText("");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        patientShowData();
      
    }
    
    
    
    
}

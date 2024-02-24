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
public class DoctorCrudController implements Initializable{
    
      @FXML
    private TableColumn<DoctorData, String> col_doctorEmailAddress;

    @FXML
    private TableColumn<DoctorData, String> col_doctorContactNumber;

    @FXML
    private TableColumn<?, ?> col_doctorFirstName;

    @FXML
    private TableColumn<DoctorData, String> col_doctorLastName;

    @FXML
    private TableColumn<DoctorData, String> col_doctorNumber;

    @FXML
    private TableColumn<DoctorData, String> col_doctorSpecialization;

    @FXML
    private Button doctor_addBtn;

    @FXML
    private TextField doctor_emailAddress;

    @FXML
    private Button doctor_clearBtn;

    @FXML
    private TextField doctor_contactNumber;

    @FXML
    private Button doctor_deleteBtn;

    @FXML
    private TextField doctor_firstName;

    @FXML
    private TextField doctor_lastName;

    @FXML
    private TextField doctor_number;

    @FXML
    private TextField doctor_search;

    @FXML
    private TextField doctor_specialization;

    @FXML
    private TableView<DoctorData> doctor_tableView;

    @FXML
    private Button doctor_updateBtn;
    
    
    private  ObservableList<DoctorData> doctorData ;
    
    
    private Connection connect ;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result; 
    
     public ObservableList<DoctorData> doctorListData()
    {
        ObservableList<DoctorData> listData = FXCollections.observableArrayList();
        
        String selectData = "SELECT * FROM doctor";
        
        connect = DataBase.connectDB(); 
        
        try
        {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            
            DoctorData dData ;
            
            while(result.next())
            {
                dData = new DoctorData(result.getInt("doctor_number"),result.getString("first_name"),result.getString("last_name"),result.getString("specialization"),result.getString("contact_number"),result.getString("email_address"));
                listData.add(dData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
  
     
        public void doctorShowData()
       {
        doctorData = doctorListData();
        
        col_doctorNumber.setCellValueFactory(new PropertyValueFactory<>("doctorNumber"));
        col_doctorFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_doctorLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_doctorSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        col_doctorContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_doctorEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        
        doctor_tableView.setItems(doctorData);
      
       }
     
        
        
        public void doctorSelectData()
    {
        DoctorData dData = doctor_tableView.getSelectionModel().getSelectedItem();
        int num =  doctor_tableView.getSelectionModel().getSelectedIndex();
        
        if(num - 1 < -1) return ;
        
        doctor_number.setText(String.valueOf(dData.getDoctorNumber()));
        doctor_firstName.setText(dData.getFirstName());
        doctor_lastName.setText(dData.getLastName());
        doctor_specialization.setText(dData.getSpecialization());
        doctor_contactNumber.setText(dData.getContactNumber());
        doctor_emailAddress.setText(dData.getEmailAddress());
        
        
    }
      
        
         public void doctorAddBtn()
        {
        
        
        connect = DataBase.connectDB();
        try
        {
            if(doctor_number.getText().isEmpty() || doctor_firstName.getText().isEmpty() || doctor_lastName.getText().isEmpty() || 
               doctor_specialization.getText().isEmpty() || doctor_contactNumber.getText().isEmpty() || doctor_emailAddress.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Fill all bkank fields"); 
            }
            else
            {
                String checkData = "SELECT doctor_number FROM doctor where doctor_number = " + doctor_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    new AlertMessage().errorMessage("Doctor Number '" + doctor_number.getText() + "' is Already Exist !"); 
                }
                else
                {
                    String insertData = "INSERT INTO doctor (doctor_number , first_name , last_name , specialization , contact_number , email_address) " 
                          + "VALUES(?,?,?,?,?,?)";
                    
                    prepare = connect.prepareStatement(insertData);
                    
                    prepare.setString(1, doctor_number.getText());
                    prepare.setString(2, doctor_firstName.getText());
                    prepare.setString(3, doctor_lastName.getText());
                    prepare.setString(4, doctor_specialization.getText());
                    prepare.setString(5, doctor_contactNumber.getText());
                    prepare.setString(6, doctor_emailAddress.getText());

                    prepare.executeUpdate();
                    new AlertMessage().successMessage("Doctor Added Successfully !");
                    doctorShowData();
                    doctorClearBtn();
                }
            }
            
        }
        catch(Exception e){e.printStackTrace();}
        
        
    }
    
     
        
        public void doctorClearBtn()
        {
        doctor_number.setText("");
        doctor_firstName.setText("");
        doctor_lastName.setText("");
        doctor_specialization.setText("");
        doctor_contactNumber.setText("");
        doctor_emailAddress.setText("");
        }

           
           
    public void doctorUpdateBtn()
    {
        connect = DataBase.connectDB();
        
        try
        {
            if(doctor_number.getText().isEmpty() || doctor_firstName.getText().isEmpty() || doctor_lastName.getText().isEmpty() || 
               doctor_specialization.getText().isEmpty() || doctor_contactNumber.getText().isEmpty() || doctor_emailAddress.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Fill all bkank fields"); 
            }
            else
            {
                String checkData = "SELECT doctor_number FROM doctor where doctor_number = " + doctor_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    
                    if(new AlertMessage().confirmMessage("Are You Sure you want to update the Doctor with number '" + doctor_number.getText() + "' ?"))
                    {
                         String updateData = "UPDATE doctor SET "
                         + "first_name = '" + doctor_firstName.getText()
                         + "', last_name = '" + doctor_lastName.getText()
                         + "', specialization = '" + doctor_specialization.getText()
                         + "', contact_number = '" + doctor_contactNumber.getText()
                         + "', email_address = '" + doctor_emailAddress.getText() + "' WHERE doctor_number = " + doctor_number.getText() ;
                 
                         prepare = connect.prepareStatement(updateData);
                         prepare.executeUpdate();
                         new AlertMessage().successMessage("The Doctor Updated Successfully !");
                         doctorShowData();
                         doctorClearBtn(); 
                    }
                    else
                    {
                        new AlertMessage().errorMessage("Cancelled !");
                         doctorClearBtn(); 
                        
                    }
                    
                    
                }
                else
                {
                     new AlertMessage().errorMessage("The Doctor With Number '" + doctor_number.getText() + "' is Not Exist !"); 
                }
                 
                
                 
                 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
       public void doctorDeleteBtn()
    {
         connect = DataBase.connectDB();
        
        try
        {
            if(doctor_number.getText().isEmpty())
            {
                 new AlertMessage().errorMessage("Please Enter The Doctor Number You want To Delete !"); 
            }
            else
            {
                String checkData = "SELECT doctor_number FROM doctor where doctor_number = " + doctor_number.getText();
                prepare = connect.prepareStatement(checkData);
                result = prepare.executeQuery();

                if(result.next())
                {
                    
                    if(new AlertMessage().confirmMessage("Are You Sure you want to Delete the Doctor with number '" + doctor_number.getText() + "' ?"))
                    {
                         String deleteData = "DELETE FROM doctor WHERE doctor_number = " + doctor_number.getText() ;
                 
                         prepare = connect.prepareStatement(deleteData);
                         prepare.executeUpdate();
                         new AlertMessage().successMessage("The Doctor Deleted Successfully !");
                         doctorShowData();
                        doctorClearBtn(); 
                    }
                    else
                    {
                        new AlertMessage().errorMessage("Cancelled !");
                         doctorClearBtn(); 
                    }
                    
                    
                }
                else
                {
                     new AlertMessage().errorMessage("The Doctor With Number '" + doctor_number.getText() + "' is Not Exist !"); 
                }
                 
                
                 
                 
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
     
    
       public ObservableList<DoctorData> doctorListDataSearchResult()
    {
        ObservableList<DoctorData> listData = FXCollections.observableArrayList();
        
        String selectData = "SELECT * FROM doctor WHERE doctor_number = '" + doctor_search.getText() + "'"
        + " OR first_name LIKE '%" + doctor_search.getText() + "%'"
        + " OR last_name LIKE '%" + doctor_search.getText() + "%'"
        + " OR specialization = '" + doctor_search.getText() + "'"
        + " OR contact_number LIKE '%" + doctor_search.getText() + "%'"
        + " OR email_address LIKE '%" + doctor_search.getText() + "%'";
        
        connect = DataBase.connectDB(); 
        try
        {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            
            DoctorData dData ;
            
            while(result.next())
            {
                dData = new DoctorData(result.getInt("doctor_number"),result.getString("first_name"),result.getString("last_name"),result.getString("specialization"),result.getString("contact_number"),result.getString("email_address"));
            
                listData.add(dData);
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
   
        public void SearchchResultdoctorShowData()
    {
        doctorData = doctorListDataSearchResult();
        
        col_doctorNumber.setCellValueFactory(new PropertyValueFactory<>("doctorNumber"));
        col_doctorFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_doctorLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_doctorSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        col_doctorContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_doctorEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        
        doctor_tableView.setItems(doctorData);
        
    
    }
     
    public void show()
    {
        if(doctor_search.getText().isEmpty())
        {
            doctorShowData();
        }
        else
        {
            SearchchResultdoctorShowData();
        }
    }
       
       @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
       doctorShowData();
    }
           
           
           
           
}

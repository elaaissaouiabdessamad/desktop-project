package HospitalManagementSystem;

import java.net.URL; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.plugin2.message.PrintAppletMessage;



public class FXMLDocumentController implements Initializable{
	
	  @FXML
	    private CheckBox login_checkbox;

	    @FXML
	    private AnchorPane login_form;

	    @FXML
	    private Button login_loginBtn;

	    @FXML
	    private PasswordField login_password;

	    @FXML
	    private Hyperlink login_registerHere;

	    @FXML
	    private TextField login_showPassword;

	    @FXML
	    private ComboBox<?> login_user;

	    @FXML
	    private TextField login_username;

	    @FXML
	    private AnchorPane main_form;

	    @FXML
	    private CheckBox register_checkbox;

	    @FXML
	    private TextField register_email;

	    @FXML
	    private AnchorPane register_form;

	    @FXML
	    private Hyperlink register_loginHere;

	    @FXML
	    private PasswordField register_password;

	    @FXML
	    private TextField register_showPassword;

	    @FXML
	    private Button register_signBtn;

	    @FXML
	    private TextField register_username;
            
            
            private Connection connect ;
            private PreparedStatement prepare;
            private ResultSet result ;
	    
            private AlertMessage alert = new AlertMessage();
            
            public void registerAccount()
            {
                        
                
                 if(!register_showPassword.isVisible())
                {
                    if(!register_showPassword.getText().equals(register_password.getText()))
                    {
                        register_showPassword.setText(register_password.getText());
                    }
                }
                else
                {
                    if(!register_showPassword.getText().equals(register_password.getText()))
                    {
                        register_password.setText(register_showPassword.getText());
                    }
                }
                
                        
                if(register_email.getText().isEmpty() || register_username.getText().isEmpty() || register_password.getText().isEmpty())
                {
                    alert.errorMessage("Please Fill all blank fields");
                }
                else
                {
                    String checkUsername = "SELECT * FROM admin WHERE username = '" + register_username.getText() + "'";
                
                    connect = DataBase.connectDB();
                    
                    
                   
                    
                    try
                    {
                        
                        
                       
                        
                        prepare = connect.prepareStatement(checkUsername);
                        result = prepare.executeQuery();
                        
                        if(result.next())
                        {
                            alert.errorMessage(register_username.getText() + " is Already Exist !");
                        }
                        else if(register_password.getText().length() < 8)
                        {
                            alert.errorMessage("Invalid Password , at least 8 characters needed !");
                        }
                        else
                        {
                            String insertMessage = "INSERT INTO admin (email , username , password) VALUES (?,?,?)";
                            
    
                            prepare = connect.prepareStatement(insertMessage);
                            prepare.setString(1, register_email.getText());
                            prepare.setString(2, register_username.getText());
                            prepare.setString(3, register_password.getText());
                            
                            
                            prepare.executeUpdate();
                            alert.successMessage("Registred Successfully!");
                            registerClear();
                            
                           login_form.setVisible(true);
                           register_form.setVisible(false);
                            
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                
                }
            }
            
            
            public void loginAccount()
            {
                
                if(!login_showPassword.isVisible())
                {
                    if(!login_showPassword.getText().equals(login_password.getText()))
                    {
                        login_showPassword.setText(login_password.getText());
                    }
                }
                else
                {
                     if(!login_showPassword.getText().equals(login_password.getText()))
                    {
                        login_password.setText(login_showPassword.getText());
                    }
                }
                
                if(login_username.getText().isEmpty() || login_password.getText().isEmpty())
                {
                    alert.errorMessage("Please Fill all blank fields");
                }
                else
                {
                    String query = "SELECT * FROM admin WHERE username = ? AND password = ?" ;
                    
                    connect = DataBase.connectDB(); 
                    
                    try 
                    {
                         
                        prepare = connect.prepareStatement(query);
                        prepare.setString(1, login_username.getText());
                        prepare.setString(2, login_password.getText());
                        result = prepare.executeQuery();
                        
                        if(!result.next())
                        {
                            alert.errorMessage("Incorrect Username/Password !");
                        }
                        else
                        {
                            alert.successMessage("Login Successfully !");
                            
                            Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
                            Stage stage = new Stage();
                            
                            stage.setTitle("Medical Office Management System | Admin Portal");
                            stage.setScene(new Scene(root));
                            
                             Image icon = new Image(getClass().getResourceAsStream("hospital.png"));
                             stage.getIcons().add(icon);
                             stage.show();
                            
                            login_loginBtn.getScene().getWindow().hide();
                            
                        }
                    }catch(Exception e){e.printStackTrace();}
                }
            }
            
            
            public void loginShowPassword()
            {
                if(login_checkbox.isSelected())
                {
                    login_showPassword.setText(login_password.getText());
                    login_showPassword.setVisible(true);
                    login_password.setVisible(false);
                }
                else
                {
                    login_password.setText(login_showPassword.getText());
                    login_showPassword.setVisible(false);
                    login_password.setVisible(true);
                }
            }
            public void registerClear()
            {
                register_email.clear();
                register_username.clear();
                register_password.clear();
                register_showPassword.clear();
            }
            public void registerShowPassword()
            {
                if(register_checkbox.isSelected())
                {
                    register_showPassword.setText(register_password.getText());
                    register_showPassword.setVisible(true);
                    register_password.setVisible(false);
                    
                }
                else
                {
                    register_password.setText(register_showPassword.getText());
                    register_showPassword.setVisible(false);
                    register_password.setVisible(true);
                }
            }
	    public void switchForms(ActionEvent event)
	    {
	    	if(event.getSource() == login_registerHere)
	    	{
	    		login_form.setVisible(false);
	    		register_form.setVisible(true);
	    	}
	    	else if(event.getSource() == register_loginHere)
	    	{
	    		login_form.setVisible(true);
	    		register_form.setVisible(false);
	    	}
	    
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}
	
	

}

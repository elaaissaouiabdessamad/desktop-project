/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package HospitalManagementSystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class HospitalManagementSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
       
    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setMinWidth(330);
    stage.setMinHeight(530);
    stage.setTitle("Medical Office Management System");
    Image icon = new Image(getClass().getResourceAsStream("patient.png"));
    stage.getIcons().add(icon);
    stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

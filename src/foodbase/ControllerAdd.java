/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodbase;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Owner
 */
public class ControllerAdd implements Initializable {
    @FXML
    private Button confirmAdd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirmAdd.setOnMouseClicked((MouseEvent event2)-> {
            try {
                System.out.println("Confirm add");
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}

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
 * @author Owner
 */
public class Controller implements Initializable {
    @FXML
    private Label title, name, foodGroup, calories, edit, del;
    
    @FXML
    private TextField search;
    
    @FXML
    private Button addNew, confirmAdd;
    
    @FXML
    private ComboBox filter;
    
    @FXML
    void handleAdd(MouseEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("add food");
            stage.setScene(new Scene(root2));
            stage.show();
            handleAddConfirm();
            
        } catch (Exception e) {
            System.out.println(e + "2");
        }
    }
    
    void handleAddConfirm() throws Exception {
        confirmAdd.setOnMouseClicked((MouseEvent event2)-> {
            try {
                System.out.println("Confirm add");
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addNew.setOnMouseClicked((MouseEvent event)-> {    
            try {
                this.handleAdd(event);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}

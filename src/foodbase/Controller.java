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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

/**
 * @author Owner
 */
public class Controller implements Initializable {
    @FXML
    private Label title, name, foodGroup, calories, edit, del;
    
    @FXML
    private TextField search;
    
    @FXML
    private Button addNew;
    
    @FXML
    private ComboBox filter;
    
    @FXML
    private GridPane foodNodes;
    
    ArrayList<Food> dataFood = new ArrayList<Food>(); // Holds entire database from file
    ArrayList<Food> foodView = new ArrayList<Food>(); // Current view of appended food nodes the user sees on screen
    
    @FXML
    void handleAdd(MouseEvent event) throws Exception { // Open view to add new food
        try {
            ControllerAdd a = new ControllerAdd();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            loader.setController(a);
            Parent root2 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("add food");
            stage.setScene(new Scene(root2));
            stage.show();
            // Handle the adding of food to this controllers dataFood arraylist
            stage.setOnCloseRequest((WindowEvent event2)-> { 
                dataFood.add(a.consolidate());
                try {
                    this.appendFoodview(dataFood, a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Will append foods to the view for user to see 
    void appendFoodview(ArrayList<Food> data, ControllerAdd a) throws Exception {
        try {
            foodView.clear(); // clear foodview array before adding all food over
            for (Food food : data) {
                foodView.add(food);
            }
            foodNodes.getChildren().clear(); // Clears current children for food nodes view
            for (int i = 0; i < foodView.size(); i++) {
                Label label = new Label(foodView.get(i).getName()); // Create each node with data
                System.out.println(foodView.get(i).getName());
                label.setPrefSize(100, 20);
                GridPane.setRowIndex(label, i+7);
                GridPane.setColumnIndex(label, 1);
                System.out.println(GridPane.getRowIndex(label));
                foodNodes.getChildren().add(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    
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

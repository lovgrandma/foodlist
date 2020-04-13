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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
            ControllerAdd a = new ControllerAdd(false, null);
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
                    this.appendFoodview(dataFood);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    void handleDel(Food food, MouseEvent event, int i) {
        try {
            System.out.println(foodNodes.getChildren().get(i));
            System.out.println(i);
            // System.out.println(dataFood.remove(foodNodes.getChildren().get(foodNodes.getRowIndex(delBtn))));
            dataFood.remove(i);
            appendFoodview(dataFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    void handleEdit(Food food, MouseEvent event, int i) {
        try {
            ControllerAdd b = new ControllerAdd(true, food);
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Add.fxml"));
            loader2.setController(b);
            Parent root3 = (Parent) loader2.load();
            Stage stage2 = new Stage();
            stage2.setTitle("edit food");
            stage2.setScene(new Scene(root3));
            stage2.show();
            // Handle the adding of food to this controllers dataFood arraylist
            stage2.setOnCloseRequest((WindowEvent event2)-> { 
                System.out.println(i);
                dataFood.remove(i);
                dataFood.add(b.consolidate());
                try {
                    for (int j = 0; j < dataFood.size(); j++) {
                        System.out.println(dataFood.get(j).getName());
                    }
                    this.appendFoodview(dataFood);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Will append foods to the view for user to see 
    void appendFoodview(ArrayList<Food> data) throws Exception {
        try {
            foodView.clear(); // clear foodview array before adding all food over
            for (Food food : data) {
                foodView.add(food);
            }
            foodNodes.getChildren().clear(); // Clears current children for food nodes view
            for (int i = 0; i < foodView.size(); i++) {
                
                Label label = new Label(foodView.get(i).getName()); // Create each node with data
                Label fgLabel = new Label(String.valueOf(foodView.get(i).getFoodGroups()));
                Label caloriesLabel = new Label(String.valueOf(foodView.get(i).getCalories()));
                Button editBtn = new Button("edit");
                Button delBtn = new Button("del");
                
                System.out.println(foodView.get(i).getName());
                label.setPrefWidth(20);
                label.setAlignment(Pos.BASELINE_LEFT);
                delBtn.setPrefSize(50, 20);
                editBtn.setPrefSize(50, 20);
                label.setPrefSize(70, 20);
                fgLabel.setPrefSize(80, 20);
                caloriesLabel.setPrefSize(40, 20);
                foodNodes.setRowIndex(label, i);
                foodNodes.setColumnIndex(label, 1);
                foodNodes.setRowIndex(fgLabel, i);
                foodNodes.setColumnIndex(fgLabel, 2);
                foodNodes.setRowIndex(caloriesLabel, i);
                foodNodes.setColumnIndex(caloriesLabel, 3);
                foodNodes.setRowIndex(editBtn, i);
                foodNodes.setColumnIndex(editBtn, 4);
                foodNodes.setRowIndex(delBtn, i);
                foodNodes.setColumnIndex(delBtn, 5);
                
                // Delete method, gets row number of del button clicked and gets food based on row index -6 and deletes from data array
                delBtn.setOnMouseClicked((MouseEvent event)-> {
                    try {
                        this.handleDel(dataFood.get(foodNodes.getRowIndex(delBtn)), event, foodNodes.getRowIndex(delBtn));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });
                
                editBtn.setOnMouseClicked((MouseEvent event2)-> {
                    try {
                        System.out.println(foodNodes.getRowIndex(editBtn));
                        this.handleEdit(dataFood.get(foodNodes.getRowIndex(editBtn)), event2, foodNodes.getRowIndex(delBtn));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                foodNodes.getChildren().addAll(label,fgLabel,caloriesLabel,editBtn, delBtn);
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

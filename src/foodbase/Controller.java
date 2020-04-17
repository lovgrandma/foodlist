package foodbase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
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
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;


/**
 * @author Owner
 */
public class Controller implements Initializable {
    @FXML
    private Label title, name, foodGroup, calories, edit, del, nameDisp,
            uuidDisp, caloriesDisp, foodGroupDisp, descriptionDisp, servingDisp, 
            styleDisp, uuidDispName, caloriesDispName, foodGroupDispName, servingDispName,
            styleDispName;
    
    @FXML
    private TextField search;
    
    @FXML
    private Button addNew, searchBtn;
    
    @FXML
    private GridPane foodNodes;
    
    ArrayList<Food> dataFood = new ArrayList<Food>(); // Holds entire database from file
    ArrayList<Food> foodView = new ArrayList<Food>(); // Current view of appended food nodes the user sees on screen
    File file;
    FileOutputStream fo;
    FileInputStream fi;
    ObjectInputStream oi;
    ObjectOutputStream os; 
    
    @FXML
    void handleAdd(MouseEvent event) throws Exception { // Open view to add new food
        try {
            ControllerAdd a = new ControllerAdd(false, null, dataFood);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            loader.setController(a);
            Parent root2 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add food");
            stage.setScene(new Scene(root2));
            stage.show();
            // Handle the adding of food to this controllers dataFood arraylist
            // The food adding logic is in the ControllerAdd class. Only runs if the user clicks "Add" on add page
            stage.setOnCloseRequest((WindowEvent event2)-> { 
                try {
                    this.appendFoodview(a.getFoodData(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Handles deletion of foods
     * @param food
     * @param event
     * @param i 
     */
    void handleDel(Food food, MouseEvent event, int i) {
        if (food.getName().equals(nameDisp.getText())) {
            this.resetDisp(event);
        }
        try {
            dataFood.remove(food);
            appendFoodview(dataFood, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handles edit functionality. Creates controller needed to edit food with existing food object
     * @param food
     * @param event
     * @param i 
     */
    void handleEdit(Food food, MouseEvent event, int i) {
        try {
            ControllerAdd b = new ControllerAdd(true, food, dataFood);
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Add.fxml"));
            loader2.setController(b);
            Parent root3 = (Parent) loader2.load();
            Stage stage2 = new Stage();
            stage2.setTitle("edit food");
            stage2.setScene(new Scene(root3));
            stage2.show();
            // Handle the adding of food to this controllers dataFood arraylist
            stage2.setOnCloseRequest((WindowEvent event2)-> { 
                dataFood.remove(food);
                dataFood.add(b.consolidate());
                try {
                    this.appendFoodview(dataFood, false);
                    // if food name equals the food in the display area. Reset to show edited information.
                    if (food.getName().equals(nameDisp.getText())) {
                        this.resetDisp(event);
                        this.setDisp(event, dataFood.get(dataFood.size()-1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the display of the food information
     * @param event
     * @param food 
     */
    void setDisp(MouseEvent event, Food food) {
        nameDisp.setText(food.getName());
        uuidDisp.setText(food.getUuid());
        caloriesDisp.setText(String.valueOf(food.getCalories()));
        foodGroupDisp.setText(String.valueOf(food.getFoodGroups()));
        descriptionDisp.setText(food.getDescription());
        descriptionDisp.setWrapText(true);
        servingDisp.setText(food.getServingSize());
        servingDisp.setWrapText(true);
        servingDispName.setText("Serving:");
        styleDisp.setText(food.getStyle());
        styleDisp.setWrapText(true);
        styleDispName.setText("Style:");
        uuidDispName.setText("Uuid:");
        caloriesDispName.setText("Calories:");
        foodGroupDispName.setText("Food Groups:");
    }
    
    /**
     * Resets the display of the food information
     * @param event 
     */
    void resetDisp(MouseEvent event) {
        nameDisp.setText("");
        uuidDisp.setText("");
        caloriesDisp.setText("");
        foodGroupDisp.setText("");
        descriptionDisp.setText("");
        servingDisp.setText("");
        servingDispName.setText("");
        styleDisp.setText("");
        styleDispName.setText("");
        uuidDispName.setText("");
        caloriesDispName.setText("");
        foodGroupDispName.setText("");
    }
    
    /**
     * Will append food to the main view for user to see and interact with.
     * Takes a data parameter for food to append and a search boolean to determine if data should be saved or not.
     * @param data
     * @param search
     * @throws Exception 
     */
    void appendFoodview(ArrayList<Food> data, Boolean search) throws Exception {
        try {
            foodNodes.getChildren().clear(); // Clears current children for food nodes view
            for (int i = 0; i < data.size(); i++) {
                Food foodDispData = data.get(i);
                Label label = new Label(data.get(i).getName()); // Create each node with data
                label.getStyleClass().add("foodNameLabel");
                label.setOnMouseClicked(( MouseEvent event)-> {
                    this.setDisp(event, foodDispData);
                });
                
                // Create nodes for each object to be displayed in the view
                Label fgLabel = new Label(String.valueOf(data.get(i).getFoodGroups()));
                Label caloriesLabel = new Label(String.valueOf(data.get(i).getCalories()));
                Button editBtn = new Button("edit");
                Button delBtn = new Button("del");
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
                        this.handleDel(data.get(foodNodes.getRowIndex(delBtn)), event, foodNodes.getRowIndex(delBtn));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                });
                
                // Set edit button event. Will create edit dialogue on click.
                editBtn.setOnMouseClicked((MouseEvent event2)-> {
                    try {
                        System.out.println(foodNodes.getRowIndex(editBtn));
                        this.handleEdit(data.get(foodNodes.getRowIndex(editBtn)), event2, foodNodes.getRowIndex(delBtn));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                foodNodes.getChildren().addAll(label,fgLabel,caloriesLabel,editBtn, delBtn);
            }
            // If this appendFoodview method was not initiated after a search, it will save the data
            // to the file
            if (!search) {
                this.saveData(); // Save data to file after appending.
            }
        } catch (Exception e) {
            System.out.println("Foods did not append successfully to list ");
            e.printStackTrace();
        }
    };
    
    /**
     * Save functionality. Run anywhere where saving is necessary. Runs after every append
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    void saveData() throws FileNotFoundException, IOException, ClassNotFoundException {
        fo = new FileOutputStream("foodDb.dat"); // path
        os = new ObjectOutputStream(fo); // new output stream
        os.writeObject(dataFood); // write method to array
    }
    
    /**
     * Auto loads database on startup
     * Creates a database file that holds an array. Currently throws error if file is empty.
     * Creates input stream, object stream and loads object from file into memory. At this point it will run appendFoodView
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    void loadData() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        file = new File("foodDb.dat");
        file.createNewFile(); // Creates file if needed
        fi = new FileInputStream(file.getName()); // pass user file name 
        oi = new ObjectInputStream(fi); 
        dataFood = (ArrayList) oi.readObject(); // type cast read object as array list
        this.appendFoodview(dataFood, false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Loads database info on startup
        try {
            this.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addNew.setOnMouseClicked((MouseEvent event)-> {    
            try {
                this.handleAdd(event);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        
        /**
         * Search Functionality. Will search using text in search field. Regex will make view default back to showing all 
         * food objects if text is empty. If there are zero matches, a dialogue box will appear. 
         */
        searchBtn.setOnMouseClicked((MouseEvent event)-> {
            try {
                foodView.clear();
                String text = search.getText();
                int matches = 0;
                for (Food food : dataFood) {
                    if (food.getName().matches("(.*)" + text + "(.*)")) {
                        matches++;
                        foodView.add(food);
                    }
                }
                // If not matches found, produce information alert.
                if (matches == 0) {
                    Alert alert = new Alert(AlertType.NONE); 
                    alert.setAlertType(AlertType.INFORMATION); 
                    alert.setContentText("Search found zero matches"); 
                    alert.show(); 
                }
                this.appendFoodview(foodView, true);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}

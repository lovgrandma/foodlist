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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

/**
 *
 * @author Owner
 */
public class ControllerAdd implements Initializable {
    private Boolean edit = false;
    private Food food;
    private ArrayList<Food> dataFood;
    // Set edit to true if editing food instead of adding
    ControllerAdd(Boolean edit, Food food, ArrayList<Food> dataFood) {
        this.edit = edit;
        this.food = food;
        this.dataFood = dataFood;
    }
    
    @FXML
    Button confirmAdd, cancelAdd;
    
    @FXML
    CheckBox dairy, protein, vege, grain, fruit;
    
    @FXML
    TextField nameField, servingField, caloriesField, styleField;
            
    @FXML
    TextArea descField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descField.setWrapText(true);
        if (this.edit) {
            confirmAdd.setText("Edit Food");
            nameField.setText(food.getName());
            servingField.setText(food.getServingSize());
            caloriesField.setText(String.valueOf(food.getCalories()));
            descField.setText(food.getDescription());
            styleField.setText(food.getStyle());
            for (int i = 0; i < food.getFoodGroups().size(); i++) {
                System.out.println(food.getFoodGroups().get(i));
                if (food.getFoodGroups().get(i).toString().toUpperCase().equals("DAIRY")) {
                    dairy.setSelected(true);
                } else if (food.getFoodGroups().get(i).toString().toUpperCase().equals("PROTEIN")) {
                    protein.setSelected(true);
                } else if (food.getFoodGroups().get(i).toString().toUpperCase().equals("VEGETABLES")) {
                    vege.setSelected(true);
                } else if (food.getFoodGroups().get(i).toString().toUpperCase().equals("GRAINS")) {
                    grain.setSelected(true);
                } else if (food.getFoodGroups().get(i).toString().toUpperCase().equals("FRUIT")) {
                    fruit.setSelected(true);
                }
            }
        }
        confirmAdd.setOnMouseClicked((MouseEvent e2)-> {
            try {
                Stage stage = (Stage) confirmAdd.getScene().getWindow();
                // Include add food functionality within Controller add class, only runs if this is not an edit page
                if (!this.edit) {
                    this.dataFood.add(this.consolidate());
                }
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        cancelAdd.setOnMouseClicked((MouseEvent e3 )-> {
            try {
                Stage stage = (Stage) cancelAdd.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
    
    public ArrayList<Food> getFoodData() {
        return this.dataFood;
    }
    
    public Food consolidate() {
        try {
            String name = new String();
            String desc = new String();
            Boolean atleastOneFoodGroup = false;
            ArrayList<FoodGroup> foodGroups = new ArrayList<FoodGroup>();
            if (dairy.isSelected()) {
                foodGroups.add(FoodGroup.DAIRY);
            }
            if (protein.isSelected()) {
                foodGroups.add(FoodGroup.PROTEIN);
            }
            if (vege.isSelected()) {
                foodGroups.add(FoodGroup.VEGETABLES);
            }
            if (grain.isSelected()) {
                foodGroups.add(FoodGroup.GRAINS);
            }
            if (fruit.isSelected()) {
                foodGroups.add(FoodGroup.FRUITS);
            }
            if (foodGroups.size() > 0) {
                atleastOneFoodGroup = true;
            }
            if (nameField.getText().length() > 0 && descField.getText().length() > 0 && atleastOneFoodGroup) {
                name = nameField.getText();
                desc = descField.getText();
                Food food = new Food(name, desc, foodGroups);
                if (servingField.getText().length() > 0) {
                    food.setServingSize(servingField.getText());
                }
                // Required field, will fail if input not valid
                if (caloriesField.getText().length() > 0) {
                    try {
                        food.setCalories(Integer.parseInt(caloriesField.getText()));
                    } catch (NumberFormatException e) {
                        System.out.println("Please insert an appropriate calories value ");
                        return null;
                    }
                }
                if (styleField.getText().length() >0) {
                    food.setStyle(styleField.getText());
                }
                return food;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    } 
}

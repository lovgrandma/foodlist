/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodbase;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jesse
 * @author Tyler M
 */
public class Foodbase extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        stage.setTitle("Foodbase!");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
        // This array intializing functionality could go into GUI class. This array is
        // used to add foodgroup values to new food.
//        ArrayList<FoodGroup> foodgroups = new ArrayList();
//        foodgroups.add(FoodGroup.DAIRY);
//        foodgroups.add(FoodGroup.GRAINS);
//        Food a = new Food("Chicken", "A great dish!", foodgroups);
//        print(a.toString());
    }
        
}

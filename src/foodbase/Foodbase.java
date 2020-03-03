/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodbase;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jesse
 */
public class Foodbase {
   
    // Small updatae
    // Simple output method
    public static void print(String b) {
        System.out.println(b);
    }   
    
    public static void main(String[] args) {
        // This array intializing functionality could go into GUI class. This array is
        // used to add foodgroup values to new food.
        ArrayList<FoodGroup> foodgroups = new ArrayList();
        foodgroups.add(FoodGroup.DAIRY);
        foodgroups.add(FoodGroup.GRAINS);
        Food a = new Food("Chicken", "A great dish!", foodgroups);
        print(a.toString());
    }
        
}

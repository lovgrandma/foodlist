/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodbase;

/**
 *
 * @author Jesse
 */
public class Foodbase {
   
    // Simple output method
    public static void print(String b) {
        System.out.println(b);
    }   
    
    public static void main(String[] args) {
        // TODO code application logic here
        Food a = new Food("Chicken", "A great dish!");
        print(a.toString());
    }
        
}

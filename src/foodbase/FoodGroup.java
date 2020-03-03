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
// Food group enum for getting foodgroups belonging to Food class instance
public enum FoodGroup {
    DAIRY(1, "dairy"),
    GRAINS(2, "grains"),
    PROTEIN(3, "protein"),
    VEGETABLES(4, "vegetables"),
    FRUITS(5, "fruits");
    
    private int dispNum;
    private String dispName;
    
    FoodGroup(int dispNum, String dispName) {
        this.dispName = dispName;
        this.dispNum = dispNum;
    }
    
    public String getDispName() {
        return this.dispName;
    }
    
    public void setDispName(String dispName) {
        this.dispName = dispName;
    }
    
    public String toString() {
        return this.dispName;
    }
}

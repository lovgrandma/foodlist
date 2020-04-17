package foodbase;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;
import java.lang.Enum;

/**
 * 
 * @author Jesse
 */
public class Food implements java.io.Serializable {
    
    private UUID uuid;
    private String name = "";
    private String description = "";
    private ArrayList<FoodGroup> FoodGroups = new ArrayList<FoodGroup>();
    private String servingSize = "";
    private int calories = 0;
    private String style = "";
    
    // Initialize a basic food item with minimal required data fields.
    Food(String name, String description, ArrayList<FoodGroup> fg) {
        this.name = name; 
        this.description = description;
        this.uuid = setUuid(); // unique identifier for food instance
        for (int i = 0; i < fg.size(); i++) { // Iterates through fg array argument and adds to this foods foodgroups
            this.FoodGroups.add(FoodGroup.valueOf(fg.get(i).toString().toUpperCase()));
        }
    }

    /**
     * Return UUID
     * @return 
     */
    public String getUuid() {
            return this.uuid.toString();
    }

    /**
     * Set UUID
     * @return 
     */
    private UUID setUuid() {
            return this.uuid = UUID.randomUUID();
    }

    /**
     * Get name
     * @return 
     */
    public String getName() {
            return this.name;
    }

    /**
     * Set name
     * @param name 
     */
    public void setName(String name) {
            this.name = name;
    }

    /**
     * Get description of food
     * @return 
     */
    public String getDescription() {
        if (this.description.length() > 0) {
            return this.description;
        } else {
            return "";
        }
    }

    /**
     * Set description of food
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns arraylist of all foodgroups belonging to food
    public ArrayList<FoodGroup> getFoodGroups() {
            return this.FoodGroups;
    }

    /**
     * Return serving size
     * @return 
     */
    public String getServingSize() {
        if (this.servingSize.length() > 0) {
            return this.servingSize;
        } else {
            return "";
        }
    }

    /** 
     * Set serving size
     * @param servingSize 
     */
    public void setServingSize(String servingSize) {
            this.servingSize = servingSize;
    }

    /** 
     * Get calories
     * @return 
     */
    public int getCalories() {
        return this.calories;
    }

    /** 
     * Set calories
     * @param calories 
     */
    public void setCalories(int calories) {
            this.calories = calories;
    }

    /** 
     * Get style
     * @return 
     */
    public String getStyle() {
        return this.style;
    }

    /** 
     * Set style
     * @param style 
     */
    public void setStyle(String style) {
            this.style = style;
    }

    /**
     * Overridden toString method
     * @return 
     */
    @Override
    public String toString() {
        String output = this.getName() + "\nDescription: " + this.getDescription() + "\n";
        if (this.FoodGroups.size() > 0) {
            output += "Food groups: ";
            for (int i = 0; i < this.FoodGroups.size() ; i++) {
                output += this.FoodGroups.get(i);
                if (i != this.FoodGroups.size() -1) {
                    output += ", ";
                }
            }
        }
        return output;
    }
}
package foodbase;
import java.util.ArrayList;

public class Food {

	private String uuid;
	private String name;
	private String description;
	private ArrayList<FoodGroup> foodGroup;
	private String servingSize;
	private int calories;
	private String style;

	public String getUuid() {
		return this.uuid;
	}

	/**
	 * 
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<FoodGroup> getFoodGroup() {
		return this.foodGroup;
	}

	/**
	 * 
	 * @param foodGroup
	 */
	public void setFoodGroup(ArrayList<FoodGroup> foodGroup) {
		this.foodGroup = foodGroup;
	}

	public String getServingSize() {
		return this.servingSize;
	}

	/**
	 * 
	 * @param servingSize
	 */
	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public int getCalories() {
		return this.calories;
	}

	/**
	 * 
	 * @param calories
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getStyle() {
		return this.style;
	}

	/**
	 * 
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;
	}

}
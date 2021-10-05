import java.util.*;

public class Recipe {
    protected String name;
    protected String description;
    protected ArrayList<String> ingredient_list;
    protected ArrayList<String> instructions;

    public Recipe() {
        this.name = ""; 
        this.description = "";
        this.ingredient_list = null; 
        this.instructions = null;
    }

    public Recipe(String name, String description,  ArrayList<String> ingredient_list, ArrayList<String> instructions) {
        this.name = name; 
        this.description = description;
        this.ingredient_list = ingredient_list; 
        this.instructions = instructions;
    }

    public String getName() {
        return name; 
    }

    public void setName(String newName) {
        this.name = newName;
    }
    
    public String getDescription() {
        return description; 
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public ArrayList<String> getIngredient_List (){
        return ingredient_list;
    }

    public void setIngredient_List (ArrayList<String> newIngridentList) {
        this.ingredient_list = newIngridentList;
    }
    
}

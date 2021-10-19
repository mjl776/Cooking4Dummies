import java.util.*;

public class Recipe {
    protected String name;
    protected String favorite;
    protected String cat;
    protected String description;
    protected ArrayList<String> ingredient_list;
    protected ArrayList<String> instructions;
    
    public Recipe() {
        this.name = ""; 
        this.description = "";
        this.favorite = "";
        this.cat = "";
        this.ingredient_list = null; 
        this.instructions = null;
    }

    public Recipe(String name, String favorite, String cat, String description,  ArrayList<String> ingredient_list, ArrayList<String> instructions) {
        this.name = name; 
        this.description = description;
        this.favorite = favorite;
        this.cat = cat;
        this.ingredient_list = ingredient_list; 
        this.instructions = instructions;
    }

    public String getName() {
        return name; 
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String newFavorite) {
        this.favorite = newFavorite;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String newCat) {
        this.cat = newCat;
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

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInsructions(ArrayList<String> newInstructions) {
        this.instructions = newInstructions;
    }
    
}
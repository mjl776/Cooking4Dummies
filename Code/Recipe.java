public class Recipe {
    protected String name; 
    protected String description; 
    protected String[] ingredient_list;
    protected String[] instructions;

    public Recipe() {
        this.name = name; 
        this.description = description;
        this.ingredient_list = ingredient_list; 
        this.instructions = instructions;
    }

    public Recipe(String name, String description, String[] ingredient_list, String[] instructions) {
        this.name = name; 
        this.description = description;
        this.ingredient_list = ingredient_list; 
        this.instructions = instructions;
    }

}
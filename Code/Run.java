import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 

public class Run{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cooking4Dummies! Type 'c' to create a recipe or 'r' to retrieve a recipe.");
        System.out.println("You can type 'x' anytime to exit.");
        String ans = sc.nextLine().toLowerCase();

        ArrayList<String> recipe_names = new ArrayList<String>();
        //Invalid input
        ans = invalidInput(ans, "c", "r", "('c' to create or 'r' to retrieve)");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("Let's create a recipe for you...");
            createRecipe();
        }

        //Retrieve a recipe
        else if (ans.equals("r")){
            System.out.println("Type 's' to search by name, or 'b' to browse all recipes.");
            String recipeans = sc.nextLine().toLowerCase();
            
            //Invalid input
            recipeans = invalidInput(recipeans, "s", "b", "('s' to search or 'b' to browse)");

            //Search recipe by name
            if (recipeans.equals("s")){
                System.out.println("Searching a recipe by name...");
            }
            //Browse all recipes
            else if (recipeans.equals("b")){
                System.out.println("Browse all recipes");
            }
            System.out.println("Type 'a' to read a recipe all at once or 's' to read through the recipe step-by-step.");
            String readans = sc.nextLine().toLowerCase();
            
            //Invalid input
            readans = invalidInput(readans, "a", "s", "('a' to read all at once or 's' to read step-by-step)");
            
            //Read recipe all at once
            if (readans.equals("a")){
                System.out.println("Reading a recipe all at once...");
            }
            //Read recipe step-by-step
            else if (readans.equals("s")){
                System.out.println("Reading a recipe step-by-step...");
            }
        }
        sc.close();
    }

    // ************************************** //
    // ********** OTHER FUNCTIONS *********** //
    // ************************************** //

    // Helper function for invalid user inputs
    public static String invalidInput(String invalidString, String option1, String option2, String instructions){
        Scanner scf = new Scanner(System.in);
        while (!invalidString.equals(option1) && !invalidString.equals(option2)){
            if (invalidString.equals("x")){
                System.out.println("Exiting program...");
                System.exit(0);
            }
            System.out.println("Please enter a valid input" + instructions);
            String invalidString2 = scf.nextLine().toLowerCase();
            invalidString = invalidString2;
        }
        return invalidString;
    }

    // Create a recipe function
    public static void createRecipe(){
        Scanner sce = new Scanner(System.in);
        boolean recipe_not_finished = true;

        while(recipe_not_finished) {
            Recipe recipe = new Recipe();

            // Recipe Name
            System.out.println("Please enter a name for the recipe.");
            if (sce.nextLine().equals("x")){
                System.exit(0);
            }
            recipe.setName(sce.nextLine());

            // Recipe Description
            System.out.println("Please enter a description.");
            if (sce.nextLine().equals("x")){
                System.exit(0);
            }
            recipe.setDescription(sce.nextLine());

            // Recipe ingredients
            boolean ingredient_list_not_done = true; 
            ArrayList <String> ingredient_list = new ArrayList<String>();
            recipe.setIngredient_List(ingredient_list);
            
            while (ingredient_list_not_done) {
                String command = "";
                if (recipe.ingredient_list.size()==0) {
                    System.out.println("What ingredients would you like to add?");
                    String ingredient = sce.nextLine();
                    recipe.ingredient_list.add(ingredient);
                }
                else {
                    System.out.println("Would you like to add more ingredients? Type 'y' for yes and 'n' for no.");
                    command = invalidInput(sce.nextLine(), "y", "n", "('y' for yes or 'n' for no)");
                    if (command.equals("y")) {
                        System.out.println("What ingredients would you like to add ");
                        String ingredient = sce.nextLine();
                        recipe.ingredient_list.add(ingredient);
                    }
                    else { 
                        break; 
                    }
                }
            }
            // Write code for recipe instructions
            boolean instruction_list_not_done = true;
            ArrayList <String> instructions = new ArrayList<String>();
            recipe.setInsructions(instructions);
            while (instruction_list_not_done) {
                String command = "";
                if (recipe.instructions.size()==0) {
                    System.out.println("What is the first step in the instructions?");
                    String instruction = sce.nextLine();
                    recipe.instructions.add(instruction);
                }
                else {
                    System.out.println("Would you like to add more steps? Type 'y' for yes and 'n' for no.");
                    command = invalidInput(sce.nextLine(), "y", "n", "('y' for yes or 'n' for no)");
                    if (command.equals("y")) {
                        System.out.println("What is the next step?");
                        String instruction = sce.nextLine();
                        recipe.instructions.add(instruction);
                    }
                    else {
                        break;
                    }
                }
            }
            System.out.println(recipe.instructions.get(0));
            sce.close();
            break;
        }
    }
}

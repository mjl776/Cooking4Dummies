import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 

public class Run{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cooking4Dummies! Type 'c' to create a recipe and 'r' to retrieve a recipe.");
        String ans = sc.nextLine().toLowerCase();

        ArrayList<String> recipe_names = new ArrayList<String>();
        
        //Retrieve Recipes


        //Invalid input
        ans = invalidInput(ans, "c", "r");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("Let's create a recipe for you...");
            boolean recipe_not_finished = true;

            while(recipe_not_finished) {
            
                Recipe recipe = new Recipe();

                // Recipe Name
                System.out.println("Please enter a name for the recipe");
                recipe.setName(sc.nextLine());

                // Recipe Description
                System.out.println("Please enter a description");
                recipe.setDescription(sc.nextLine());

                // Recipe ingridient 
                boolean ingredient_list_not_done = true; 
                ArrayList <String> ingredient_list = new ArrayList<String>();
                recipe.setIngredient_List(ingredient_list);
                
                while (ingredient_list_not_done) {

                    String command = "";
                    if (recipe.ingredient_list.size()==0) {
                        System.out.println("What ingridients would you like to add ");
                        String ingredient = sc.nextLine();
                        recipe.ingredient_list.add(ingredient);
                    }
                    else {
                        System.out.println("Would you like to add more ingridients yes or no Type y or n");
                        command = invalidInput(sc.nextLine(), "y", "n");
                        if (command.equals("y")) {
                            System.out.println("What ingridients would you like to add ");
                            String ingredient = sc.nextLine();
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
                        System.out.println("What is the first step in the Instructions");
                        String instruction = sc.nextLine();
                        recipe.instructions.add(instruction);
                    }
                    else {
                        System.out.println("Would you like to add more steps yes or no Type y or n");
                        command = invalidInput(sc.nextLine(), "y", "n");
                        if (command.equals("y")) {
                            System.out.println("What is the next step ");
                            String instruction = sc.nextLine();
                            recipe.instructions.add(instruction);
                        }
                        else {
                            break;
                        }
                    }
                }
                System.out.println(recipe.instructions.get(0));
                break; 
 
                }
                
            }

        //Retrieve a recipe
        else if (ans.equals("r")){
            System.out.println("Type 's' to search by name, or 'b' to browse all recipes.");
            String recipeans = sc.nextLine().toLowerCase();
            
            //Invalid input
            recipeans = invalidInput(recipeans, "s", "b");

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
            readans = invalidInput(readans, "a", "s");
            
            //Read recipe all at once
            if (readans.equals("a")){
                System.out.println("Reading a recipe all at once...");
            }
            //Read recipe step-by-step
            else if (readans.equals("s")){
                System.out.println("Reading a recipe step-by-step...");
            }
        }
    }

    // Helper function for invalid user inputs
    public static String invalidInput(String invalidString, String option1, String option2){
        Scanner sc = new Scanner(System.in);
        while (!invalidString.equals(option1) && !invalidString.equals(option2)){
            System.out.println("Please enter a valid input.");
            String invalidString2 = sc.nextLine().toLowerCase();
            invalidString = invalidString2;
        }
        return invalidString;
    }
}

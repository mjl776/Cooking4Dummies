import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;   
import java.io.File;  
import java.io.*;  

public class Run{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cooking4Dummies! Type 'c' to create a recipe or 'r' to retrieve a recipe.");
        System.out.println("You can type 'x' anytime to exit.");
        String ans = sc.nextLine().toLowerCase();


        // Read file to get all recipe names
        ArrayList<String> recipe_names = new ArrayList<String>();

        try {
            File recipenames = new File("RecipeNames.txt");
            Scanner scanner = new Scanner(recipenames);
            while (scanner.hasNextLine()) {
              String recipe = scanner.nextLine();
              recipe_names.add(recipe);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //Invalid input
        ans = invalidInput(ans, "c", "r", "('c' to create or 'r' to retrieve)");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("Let's create a recipe for you...");
            String recipe = createRecipe();
            recipe_names.add(recipe);

            // Write this to the file for RecipeNames
            FileWriter recipeWriter = new FileWriter("RecipeNames.txt");
            try {
                recipeWriter.write("Names: ");
                recipeWriter.write("\n");
                for (int i = 0; i < recipe_names.size(); i++) {
                    recipeWriter.write(recipe_names.get(i));
                    recipeWriter.write("\n");
                }
                recipeWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


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
    public static String createRecipe() throws IOException {
        Scanner sce = new Scanner(System.in);
        boolean recipe_not_finished = true;
        Recipe recipe = new Recipe();

        while(recipe_not_finished) {
            // Recipe Name
            System.out.println("Please enter a name for the recipe.");
            recipe.setName(sce.nextLine());

            // Recipe Description
            System.out.println("Please enter a description.");
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


            sce.close();
            break;
        }

        System.out.println(recipe.instructions.get(0));

        // write to file
        try {
            FileWriter recipeWriter = new FileWriter(recipe.name + ".txt");

            // Recipe name
            recipeWriter.write("Name:");
            recipeWriter.write("\n");
            recipeWriter.write(recipe.name);
            recipeWriter.write("\n");

            // Description
            recipeWriter.write("Description:");
            recipeWriter.write("\n");
            recipeWriter.write(recipe.description);
            recipeWriter.write("\n");

            recipeWriter.write("Ingredient List:");
            recipeWriter.write("\n");
            for (int i = 0; i < recipe.ingredient_list.size(); i++) {
                recipeWriter.write(recipe.ingredient_list.get(i));
                recipeWriter.write("\n");
            }

            // Instructions 
            recipeWriter.write("Instructions:");
            recipeWriter.write("\n");
            for (int i = 0; i < recipe.instructions.size(); i++){
                recipeWriter.write("Step " + i + " "+ recipe.instructions.get(i));
                recipeWriter.write("\n");
            }
            recipeWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return recipe.name; 

    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// import jdk.internal.shellsupport.doc.resources.javadocformatter;

import java.io.FileWriter;   
import java.io.File;  
import java.io.*;  
import java.lang.String;

public class Run{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("*************************************************************************************************");
        System.out.println("***                                Welcome to Cooking4Dummies!                                ***");
        System.out.println("***-------------------------------------------------------------------------------------------***");
        System.out.println("***                                       INSTRUCTIONS                                        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                  Type 'c' to create a recipe or 'r' to retrieve a recipe.                 ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                            (You can type 'x' anytime to exit.)                            ***");
        System.out.println("*************************************************************************************************");
        System.out.printf(" >> Type your input here: ");
        String ans = sc.nextLine().toLowerCase();

        // blank recipe
        String recipe = null;

        // Read file to get all recipe names
        ArrayList<String> recipe_names = new ArrayList<String>();

        try {
            File recipenames = new File("RecipeNames.txt");
            Scanner scanner = new Scanner(recipenames);
            while (scanner.hasNextLine()) {
              String recipe_name = scanner.nextLine();
              recipe_names.add(recipe_name);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("*************************************************************************************************");
            System.out.println("***                                           ERROR!                                          ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                                   Something went wrong.                                   ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            e.printStackTrace();
        }


        //Invalid input
        ans = invalidInput(ans, "c", "r", "", "('c' to create or 'r' to retrieve)");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("*************************************************************************************************");
            System.out.println("***                              Let's create a recipe for you...                             ***");
            System.out.println("***-------------------------------------------------------------------------------------------***");
            recipe = createRecipe();
            recipe_names.add(recipe);


            // Write this to the file for RecipeNames
            FileWriter recipeWriter = new FileWriter("RecipeNames.txt");
            try {
                for (int i = 0; i < recipe_names.size(); i++) {
                    recipeWriter.write(recipe_names.get(i));
                    recipeWriter.write("\n");
                }
                recipeWriter.close();
            } catch (IOException e) {
                System.out.println("*************************************************************************************************");
                System.out.println("***                                           ERROR!                                          ***");
                System.out.println("***                                                                                           ***");
                System.out.println("***                                   Something went wrong.                                   ***");
                System.out.println("***                                                                                           ***");
                System.out.println("*************************************************************************************************");
                e.printStackTrace();
            }
            System.out.println("*************************************************************************************************");
            System.out.println("***                                          EXITING!                                         ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***      Please run the program again to either create another recipe, or browse recipes.     ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.exit(0);

        }

        //Retrieve a recipe
        else if (ans.equals("r")){
            System.out.println("*************************************************************************************************");
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***   Type 's' to search by name, 'b' to browse all recipes, and 'f' to browse by favorites.  ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            String recipeans = sc.nextLine().toLowerCase();
            //Invalid input
            recipeans = invalidInput(recipeans, "s", "b", "f", "('s' to search or 'b' to browse all or 'f' to browse by favorites)");

            //Search recipe by name
            if (recipeans.equals("s")){
                recipe = byName(recipe_names);
                if (recipe == null) {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                              No recipe matches your search.                               ***");
                    System.out.println("***                                          EXITING!                                         ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***      Please run the program again to either create a recipe, or browse another recipe.    ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.exit(0);
                } else {
                    System.out.println();
                    System.out.println("*************************************************************************************************");
                    System.out.println("                              Here is your recipe: " + recipe.toString());
                    System.out.println("*************************************************************************************************");
                }
            }

            //search by cat
            /* if (recipeans.equals("c")){
                System.out.println("Searching a recipe by category...");
                recipe = byCat(recipe_names);
                if (recipe == null) {
                    System.out.println("No recipe matches your search :(");
                } else {
                    System.out.println("Here are your recipe: \n" + recipe.toString());
                }
            } */

            //search by fav
            if (recipeans.equals("f")){
                System.out.println("*************************************************************************************************");
                System.out.println("***                             Searching a recipe by favorites...                            ***");
                System.out.println("***-------------------------------------------------------------------------------------------***");
                String favs = byFav(recipe_names);
                if (favs == null) {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                              No recipe matches your search.                               ***");
                    System.out.println("***                                          EXITING!                                         ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***      Please run the program again to either create a recipe, or browse another recipe.    ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.exit(0);
                }  
                else {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                                                                           ***");
                    System.out.println("                                  You selected: " + favs);
                    System.out.println("***                                                                                           ***");
                    System.out.println("***-------------------------------------------------------------------------------------------***");
                    recipe = favs;
                }
            }
            //Browse all recipes
            else if (recipeans.equals("b")){
                recipe = browseAll(recipe_names);
                if (recipe == null) {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                           That number is not in our catalogue.                            ***");
                    System.out.println("***                                          EXITING!                                         ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***      Please run the program again to either create a recipe, or browse another recipe.    ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.exit(0);
                } else {
                    // remember to make a toString method for the recipe in your Recipe class
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                                                                           ***");
                    System.out.println("                                  You selected: " + recipe.toString());
                    System.out.println("***                                                                                           ***");
                    System.out.println("***-------------------------------------------------------------------------------------------***");
                }

            }

            }
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***   Type 'a' to read a recipe all at once or 's' to read through the recipe step-by-step.   ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            String readans = sc.nextLine().toLowerCase();
            
            //Invalid input
            readans = invalidInput(readans, "a", "s", "", "('a' to read all at once or 's' to read step-by-step)");
            
            //Read recipe all at once
            if (readans.equals("a")){
                System.out.println();
                System.out.println("*************************************************************************************************");
                System.out.println("***                     Reading a recipe all at once...                                       ***");
                System.out.println("***-------------------------------------------------------------------------------------------***");
                wholeRecipePrint(recipe.toString() + ".txt");
                System.out.println("*************************************************************************************************");
            }
            //Read recipe step-by-step
            else if (readans.equals("s")){
                System.out.println();
                System.out.println("*************************************************************************************************");
                System.out.println("***                         Reading a recipe step-by-step...                                  ***");
                System.out.println("***-------------------------------------------------------------------------------------------***");
                stepRecipePrint(recipe.toString() + ".txt");
            }
            sc.close();
        }

    // ************************************** //
    // ********** OTHER FUNCTIONS *********** //
    // ************************************** //

    // Helper function for invalid user inputs
    public static String invalidInput(String invalidString, String option1, String option2, String option3, String instructions){
        Scanner scf = new Scanner(System.in);
        while (!invalidString.equals(option1) && !invalidString.equals(option2) && !invalidString.equals(option3) && !invalidString.equals("")){
            if (invalidString.equals("x")){
                System.out.println("*************************************************************************************************");
                System.out.println("***                                          EXITING!                                         ***");
                System.out.println("***                                                                                           ***");
                System.out.println("***         Please run the program again to either create a recipe, or browse recipes.        ***");
                System.out.println("***                                                                                           ***");
                System.out.println("*************************************************************************************************");
                System.exit(0);
            }
            System.out.println("*************************************************************************************************");
            System.out.println("***                                           ERROR!                                          ***");
            System.out.println("***                                                                                           ***");
            System.out.println("---> Please enter a valid input" + instructions);
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
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
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                            Please enter a name for the recipe.                            ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            String nameAns = sce.nextLine();
            if (nameAns.equals("x")){
                System.exit(0);
            }
            recipe.setName(nameAns);


            //Favorite
            System.out.println("*************************************************************************************************");
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***           Is this recipe a favorite of yours? Type 'y' for yes and 'n' for no.            ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            String favAns = sce.nextLine();
            if (favAns.equals("x")){
                System.exit(0);
            }
            recipe.setFavorite(favAns);
            
            //Cagtegory
            /* System.out.println("Is this meal a breakfast, lunch, or dinner?");
            recipe.setCat(sce.nextLine()); */

            // Recipe Description
            System.out.println("*************************************************************************************************");
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                                Please enter a description.                                ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            String descAns = sce.nextLine();
            if (descAns.equals("x")){
                System.exit(0);
            }
            recipe.setDescription(descAns);

            // Recipe ingredients
            boolean ingredient_list_not_done = true; 
            ArrayList <String> ingredient_list = new ArrayList<String>();
            recipe.setIngredient_List(ingredient_list);
            
            while (ingredient_list_not_done) {
                String command = "";
                if (recipe.ingredient_list.size()==0) {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                       INSTRUCTIONS                                        ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***                          What ingredients would you like to add?                          ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.out.printf(" >> Type your input here: ");
                    String ingredient = sce.nextLine();
                    recipe.ingredient_list.add(ingredient);
                }
                else {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                       INSTRUCTIONS                                        ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***         Would you like to add more ingredients? Type 'y' for yes and 'n' for no.          ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.out.printf(" >> Type your input here: ");
                    command = invalidInput(sce.nextLine(), "y", "n", "", "('y' for yes or 'n' for no)");
                    if (command.equals("y")) {
                        System.out.println("*************************************************************************************************");
                        System.out.println("***                                       INSTRUCTIONS                                        ***");
                        System.out.println("***                                                                                           ***");
                        System.out.println("***                          What ingredients would you like to add?                          ***");
                        System.out.println("***                                                                                           ***");
                        System.out.println("*************************************************************************************************");
                        System.out.printf(" >> Type your input here: ");
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
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                       INSTRUCTIONS                                        ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***                        What is the first step in the instructions?                        ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.out.printf(" >> Type your input here: ");
                    String instruction = sce.nextLine();
                    recipe.instructions.add(instruction);
                }
                else {
                    System.out.println("*************************************************************************************************");
                    System.out.println("***                                       INSTRUCTIONS                                        ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("***             Would you like to add more steps? Type 'y' for yes and 'n' for no.            ***");
                    System.out.println("***                                                                                           ***");
                    System.out.println("*************************************************************************************************");
                    System.out.printf(" >> Type your input here: ");
                    command = invalidInput(sce.nextLine(), "y", "n", "", "('y' for yes or 'n' for no)");
                    if (command.equals("y")) {
                        System.out.println("*************************************************************************************************");
                        System.out.println("***                                       INSTRUCTIONS                                        ***");
                        System.out.println("***                                                                                           ***");
                        System.out.println("***                                  What is the next step?                                   ***");
                        System.out.println("***                                                                                           ***");
                        System.out.println("*************************************************************************************************");
                        System.out.printf(" >> Type your input here: ");
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

            //Recipe fav
            recipeWriter.write("Favorite:");
            recipeWriter.write("\n");
            recipeWriter.write(recipe.favorite);
            recipeWriter.write("\n");
           
           
            //Recipe cat
           /*  recipeWriter.write("Category: ");
            recipeWriter.write("\n");
            recipeWriter.write(recipe.cat);
            recipeWriter.write("\n"); */

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
            System.out.println("*************************************************************************************************");
            System.out.println("***                                           ERROR!                                          ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                                   Something went wrong.                                   ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            e.printStackTrace();
        }
        return recipe.name;
    }

    public static String byName(ArrayList<String> recipes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("*************************************************************************************************");
        System.out.println("***                                       INSTRUCTIONS                                        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                            Type to search for a recipe by name.                           ***");
        System.out.println("***                                                                                           ***");
        System.out.println("*************************************************************************************************");
        System.out.printf(" >> Type your input here: ");
        String input = sc.nextLine().toLowerCase();
        for (String recipe : recipes) {
            if (recipe.toLowerCase().contains(input)) {
                return recipe;
            } 
        }
        return null;
    }

    /* public static String byCat(ArrayList<String> recipes){
        Scanner sc = new Scanner(System.in);
        System.out.println("search for a recipe by category: ");
        String input = sc.nextLine().toLowerCase();
    } */

    public static String byFav(ArrayList<String> recipes){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> favorites = new ArrayList<>();
        int counter = 0;
        while (counter < recipes.size()) {
            try {
                File wholerecipe = new File(recipes.get(counter).toString() +  ".txt");
                Scanner scanner = new Scanner(wholerecipe);
                while (scanner.hasNextLine()) {
                    String recipeline = scanner.nextLine().trim();
                    if (recipeline.equals("Favorite:")) {
                        String favoriteLine = scanner.nextLine();
                        if (favoriteLine.equals("y")) {
                            favorites.add(recipes.get(counter));
                        }
                        break;
                    }
                }
                counter++;
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("*************************************************************************************************");
                System.out.println("***                                           ERROR!                                          ***");
                System.out.println("***                                                                                           ***");
                System.out.println("***                                   Something went wrong.                                   ***");
                System.out.println("***                                                                                           ***");
                System.out.println("*************************************************************************************************");
                e.printStackTrace();
            }
        }

        for (int i = 0; i < favorites.size(); i++) {
            System.out.println("" + (i + 1) + ". " + favorites.get(i));
        }

        System.out.println("***                                       INSTRUCTIONS                                        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                             Browse favorite recipe by number:                             ***");
        System.out.println("***                                                                                           ***");
        System.out.println("*************************************************************************************************");
        System.out.printf(" >> Type your input here: ");
        int input = sc.nextInt();

        return favorites.get(input-1);
    }

    public static String browseAll(ArrayList<String> recipes) {
        System.out.println("*************************************************************************************************");
        System.out.println("***                                       INSTRUCTIONS                                        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                 Here are all of our recipes. Please select by number. :)                  ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***-------------------------------------------------------------------------------------------***");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println("" + (i + 1) + ". " + recipes.get(i));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("***-------------------------------------------------------------------------------------------***");
        System.out.println("***                                       INSTRUCTIONS                                        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***                                    Select your recipe:                                    ***");
        System.out.println("***                                                                                           ***");
        System.out.println("*************************************************************************************************");
        System.out.printf(" >> Type your input here: ");
        int input = sc.nextInt();
        return recipes.get(input - 1);
    }

    //Print Recipe all at once
    public static void wholeRecipePrint(String recipe_file){
        ArrayList<String> whole_recipe = new ArrayList<String>();

        try {
            File wholerecipe = new File(recipe_file);
            Scanner scanner = new Scanner(wholerecipe);
            while (scanner.hasNextLine()) {
              String recipeline = scanner.nextLine();
              whole_recipe.add(recipeline);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("*************************************************************************************************");
            System.out.println("***                                           ERROR!                                          ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                                   Something went wrong.                                   ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            e.printStackTrace();
        }

        for (int i = 0; i < whole_recipe.size(); i++) {
            System.out.println(whole_recipe.get(i));
        }
    }

    //Print recipe step by step
    public static void stepRecipePrint(String recipe_file) throws IOException {
        ArrayList<String> whole_recipe = new ArrayList<String>();
        try {
            File wholerecipe = new File(recipe_file);
            Scanner scanner = new Scanner(wholerecipe);
            while (scanner.hasNextLine()) {
              String recipeline = scanner.nextLine();
              whole_recipe.add(recipeline);
            }
            scanner.close();
          } catch (FileNotFoundException e) {
            System.out.println("*************************************************************************************************");
            System.out.println("***                                           ERROR!                                          ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***                                   Something went wrong.                                   ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            e.printStackTrace();
        }
        String dish = "";
        for (int i = 0; i < 2; i++) {
            dish = dish + whole_recipe.get(i) + " ";
        }
        int iterate = 2;
        String description = whole_recipe.get(iterate) + " ";
        for (int i = (iterate+1); i < whole_recipe.size(); i++) {
            if ((whole_recipe.get(i)).contains(":")){
                iterate = i;
                break;
            }
            else{
                description = description + whole_recipe.get(i) + " ";
            }
        }
        String ingredients = whole_recipe.get(iterate) + "\n";
        for (int i = (iterate+1); i < whole_recipe.size(); i++) {
            if ((whole_recipe.get(i)).contains(":")){
                iterate = i;
                break;
            }
            else{
                ingredients = ingredients + whole_recipe.get(i) + "\n";
            }
        }
        ArrayList<String> steps = new ArrayList<String>();
        steps.add(dish);
        steps.add(description);
        steps.add(ingredients);
        for (int i = (iterate+1); i < whole_recipe.size(); i++) {
            steps.add(whole_recipe.get(i));
        }

        Scanner sce = new Scanner(System.in);
        int j = 0;
        for (int i = 0; i < whole_recipe.size(); i++){
            String currLine = whole_recipe.get(i).trim();
            if (currLine.equals("Instructions:")){
                j = i + 1;
            }
        }
        while(j < whole_recipe.size()) {
            System.out.println();
            System.out.println("*************************************************************************************************");
            System.out.println("       --> " + whole_recipe.get(j));
            String step_print;
            System.out.println("***-------------------------------------------------------------------------------------------***");
            System.out.println("***                                       INSTRUCTIONS                                        ***");
            System.out.println("***                                                                                           ***");
            System.out.println("***              Enter 'n' to see the next step or 'p' to see the previous step.              ***");
            System.out.println("***                                                                                           ***");
            System.out.println("*************************************************************************************************");
            System.out.printf(" >> Type your input here: ");
            step_print = invalidInput(sce.nextLine(), "n", "p", "", "('n' for next or 'p' for prevous)");
            if(step_print.equals("n")){
                j++;
            }
            else if(step_print.equals("p")){
                j--;
            }
        }
        System.out.println("*************************************************************************************************");
        System.out.println("***                                      You're finished!                                     ***");
        System.out.println("***                                          EXITING!                                         ***");
        System.out.println("***                                                                                           ***");
        System.out.println("***         Please run the program again to either create a recipe, or browse recipes.        ***");
        System.out.println("***                                                                                           ***");
        System.out.println("*************************************************************************************************");
    }
}

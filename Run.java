import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

public class Run {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cooking4Dummies! Type 'c' to create a recipe and 'r' to retrieve a recipe.");
        String ans = sc.nextLine().toLowerCase();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>;

        //Invalid input
        ans = invalidInput(ans, "c", "r");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("Let's create a recipe for you...");
            // when a new recipe is created using a function in the Recipe class
            // it then gets pushed onto the ArrayList of recipes
            // don't forget to calculate the num object value by checking for the length of
            // the ArrayList and adding 1. 
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
                Recipe recipe = byName(recipes);
                if (recipe == null) {
                    System.out.println("No recipe matches your search :(");
                } else {
                    // remember to make a toString method for the recipe in your Recipe class
                    System.out.println("Here is your recipe: \n" + recipe.toString);
                }
                // add a loop if you want to make the user's life easy
                //rather than starting over again redo the search
            }
            //Browse all recipes
            else if (recipeans.equals("b")){
                System.out.println("Browse all recipes");
                Recipe recipe = browseAll(recipes);
                if (recipe == null) {
                    System.out.println("That number is not in our catalogue. ");
                } else {
                    // remember to make a toString method for the recipe in your Recipe class
                    System.out.println("Here is your recipe: \n" + recipe.toString);
                }
                // add a loop if you want to make the user's life easy
                // wrong input so that they can try again
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

    //Helper function for invalid user inputs
    public static String invalidInput(String invalidString, String option1, String option2){
        Scanner sc = new Scanner(System.in);
        while (!invalidString.equals(option1) && !invalidString.equals(option2)){
            System.out.println("Please enter a valid input.");
            String invalidString2 = sc.nextLine().toLowerCase();
            invalidString = invalidString2;
        }
        return invalidString;
    }

    public static Recipe byName(ArrayList<Recipe> recipes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("search for a recipe by name :) : ");
        String input = sc.nextLine().toLowerCase();
        for (Recipe recipe : recipes) {
            if (recipe.name.toLowerCase.contains(input)) {
                return recipe;
            } 
        }
        return null;
    }

    public static Recipe browseAll(ArrayList<Recipe> recipes) {
        System.out.println("Here are all of our recipes. Please select by number. :)");
        for (Recipe recipe : recipes) {
            System.out.println(recipe.num + " ." + recipe.name);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("select your recipe: ");
        Int input = sc.nextLine().toLowerCase();
        for (Recipe recipe : recipes) {
            if (recipe.num == input) {
                return recipe;
            }
        }
        return null;
}

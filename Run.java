import java.io.IOException;
import java.util.Scanner;

public class Run{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cooking4Dummies! Type 'c' to create a recipe and 'r' to retrieve a recipe.");
        String ans = sc.nextLine().toLowerCase();

        //Invalid input
        ans = invalidInput(ans, "c", "r");

        //Create a recipe
        if (ans.equals("c")) {
            System.out.println("Let's create a recipe for you...");
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
}

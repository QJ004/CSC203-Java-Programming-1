// We need to import the Scanner library to handle user input later in the program.
import java.util.Scanner;

// We are defining the main class for the Starbase 13 application.
// The class name must match the file name: StarBase13.java.
public class StarBase13 {
    // This is the main method, the entry point for the Java Virtual Machine (JVM) to start program execution.
    // This annotation suppresses the IDE warning about closing the Scanner without using a 'try-with-resources' block.
    @SuppressWarnings("ConvertToTryWithResources") 
    public static void main(String[] args) {

        // PART 1: COMPUTER GREETING AND STATION DESCRIPTION

        // Display the computer's initial greeting and prompt for the user's name.
        System.out.println("I am HAL, what is your name?");

        // Display a line break for better formatting.
        System.out.println("---");
        
        // This first line describes the colossal size of the space station.
        System.out.println("Ahead, the colossal structure of Starbase 13 fills your viewport, dwarfing your shuttle.");
        
        // This second line describes the exterior features, specifically the solar arrays.
        System.out.println("Miles of gleaming solar arrays radiate power, capturing the distant sun's energy.");
        
        // This third line describes the primary structure, the central docking ring.
        System.out.println("A massive, rotating central ring provides artificial gravity for the habitat modules.");
        
        // This fourth line describes the visible activity, such as cargo vessels.
        System.out.println("Dozens of smaller cargo vessels ferry supplies between the station and lunar mines.");
        
        // This fifth line concludes the description, focusing on the station's purpose.
        System.out.println("This is the main hub of the Acme Intergalactic Space Agency's operations in the outer system.");
        
        // Display another line break to visually separate the description from the user input prompt.
        System.out.println("---");

        // PART 2: USER INPUT AND WELCOME MESSAGE

        // 1. Create a Scanner object to read input from the keyboard (System.in).
        Scanner scanner = new Scanner(System.in);
        
        // 2. Declare a String variable to store the name the user enters.
        String userName;
        
        // 3. Read the entire line of text the user enters and store it in the userName variable.
        userName = scanner.nextLine();
        
        // 4. Display the personalized welcome message to the user, combining a string literal with the userName variable.
        System.out.println("Hi " + userName + ", welcome to Starbase 13.");

        // 5. Close the scanner object to free up system resources.
        scanner.close(); 
    } 
} 
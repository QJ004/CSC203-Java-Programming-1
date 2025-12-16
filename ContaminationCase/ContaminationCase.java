// Import Scanner for user input
import java.util.Scanner;

// Environmental Detective Game - Investigate water contamination in a small town
public class ContaminationCase {
    
    // Main method - controls the entire game flow
    public static void main(String[] args) {
        // Step 1: Display the introduction
        displayIntro();
        
        // Step 2: Get the player's name
        String playerName = getPlayerName();
        
        // Step 3: Let player choose a location to investigate
        int location = chooseLocation();
        
        // Step 4: Analyze the sample from chosen location
        analyzeSample(location);
        
        // Step 5: Player makes their conclusion
        makeConclusion(playerName);
        
        // Step 6: Display the ending message
        displayEnding();
    }
    
    // Display the introduction and story setup
    public static void displayIntro() {
        System.out.println("=== THE CONTAMINATION CASE ===");
        System.out.println();
        
        // Use a for loop to display loading animation
        System.out.print("Loading case files");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
        }
        System.out.println();
        System.out.println();
        
        System.out.println("You are an environmental scientist called to investigate");
        System.out.println("a mysterious water contamination in the town of Riverside.");
        System.out.println();
        System.out.println("Three locations have reported unusual water quality:");
        System.out.println("the industrial district, the farming area, and the residential zone.");
        System.out.println();
        System.out.println("Your mission: collect samples, analyze data, and find the source!");
        System.out.println("---");
    }
    
    // Get the player's name
    // Returns: String containing the player's name
    @SuppressWarnings("resource")
    public static String getPlayerName() {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Ask for player's name
        System.out.print("Enter your name, Detective: ");
        
        // Read and store the player's name
        String name = scanner.nextLine();
        
        // Welcome the player
        System.out.println("Welcome, Detective " + name + "!");
        System.out.println("---");
        
        // Return the name so other methods can use it
        return name;
    }
    
    // Show available locations and get player's choice
    // Returns: int representing the chosen location (1, 2, or 3)
    @SuppressWarnings("resource")
    public static int chooseLocation() {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Create an array to store location names
        String[] locations = new String[3];
        locations[0] = "Industrial District - Factory near the river";
        locations[1] = "Farming Area - Agricultural fields with irrigation";
        locations[2] = "Residential Zone - Neighborhood water supply";
        
        // Display the location options using a for loop
        System.out.println("Choose a location to investigate:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + locations[i]);
        }
        System.out.println();
        
        // Get player's choice
        System.out.print("Enter your choice (1, 2, or 3): ");
        int choice = scanner.nextInt();
        
        System.out.println("---");
        
        // Return the choice so main can use it
        return choice;
    }
    
    // Analyze the water sample from the chosen location
    // Takes: int locationChoice (which location the player selected)
    public static void analyzeSample(int locationChoice) {
        System.out.println("Collecting water sample...");
        
        // Use a for loop to show progress
        System.out.print("Running analysis");
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
        }
        System.out.println();
        System.out.println();
        
        // Create arrays to store contamination data for each location
        String[] locationNames = {"Industrial District", "Farming Area", "Residential Zone"};
        double[] phLevels = {3.2, 7.1, 6.8};
        String[] contaminationLevels = {"SEVERE", "SAFE", "SAFE"};
        
        // Use the arrays to display data based on player's choice
        // Array index is choice minus 1 (because arrays start at 0)
        int index = locationChoice - 1;
        
        System.out.println("LOCATION: " + locationNames[index]);
        System.out.println("pH Level: " + phLevels[index]);
        
        // Use if statements to show different details based on location
        if (locationChoice == 1) {
            System.out.println("Heavy Metals: DETECTED - Lead and Mercury present");
            System.out.println("Chemical Markers: Industrial waste compounds found");
        }
        
        if (locationChoice == 2) {
            System.out.println("Heavy Metals: Not detected");
            System.out.println("Chemical Markers: Mild fertilizer traces (within safe limits)");
        }
        
        if (locationChoice == 3) {
            System.out.println("Heavy Metals: Trace amounts only");
            System.out.println("Chemical Markers: Standard chlorine treatment detected");
        }
        
        System.out.println("Contamination Level: " + contaminationLevels[index]);
        System.out.println();
        System.out.println("---");
    }
    
    // Let the player make their final conclusion
    // Takes: String playerName (to personalize the final message)
    @SuppressWarnings("resource")
    public static void makeConclusion(String playerName) {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Create an array with the conclusion options
        String[] options = new String[3];
        options[0] = "Industrial District";
        options[1] = "Farming Area";
        options[2] = "Residential Zone";
        
        // Ask player to identify the contamination source
        System.out.println("Based on your analysis, where is the contamination coming from?");
        
        // Use a for loop to display options from the array
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println();
        
        System.out.print("Your conclusion (1, 2, or 3): ");
        int guess = scanner.nextInt();
        
        System.out.println("---");
        
        // Check if the player is correct
        if (guess == 1) {
            // Correct answer
            System.out.println("CORRECT, Detective " + playerName + "!");
            System.out.println("The industrial factory was illegally dumping toxic waste");
            System.out.println("into the river, contaminating the town's water supply.");
            System.out.println("Authorities have been notified. Case closed!");
        }
        
        if (guess == 2) {
            // Wrong answer
            System.out.println("Not quite, Detective " + playerName + ".");
            System.out.println("The farming area showed safe levels.");
            System.out.println("The real culprit was the industrial district.");
        }
        
        if (guess == 3) {
            // Wrong answer
            System.out.println("Not quite, Detective " + playerName + ".");
            System.out.println("The residential zone was actually safe.");
            System.out.println("The real culprit was the industrial district.");
        }
        
        System.out.println("---");
    }
    
    // Display the game ending message
    public static void displayEnding() {
        System.out.println("Thank you for playing THE CONTAMINATION CASE!");
        System.out.println("You helped protect the town's water supply.");
        
        // Use a for loop to display a closing message
        System.out.print("Stay vigilant");
        for (int i = 0; i < 3; i++) {
            System.out.print("!");
        }
        System.out.println();
    }
}
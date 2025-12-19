// Main.java - The main program that runs the UAT campus simulation
// Import Scanner for user input
import java.util.Scanner;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Display welcome message
        System.out.println("========================================");
        System.out.println("   WELCOME TO UAT CAMPUS SIMULATOR");
        System.out.println("   A Virtual Day at the University");
        System.out.println("========================================");
        System.out.println();
        
        // Get student information from user
        System.out.print("Enter your first name: ");
        String studentFirst = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        String studentLast = scanner.nextLine();
        
        // Create a Student object (inherits from Person)
        Student mainStudent = new Student();
        mainStudent.firstName = studentFirst;
        mainStudent.lastName = studentLast;
        mainStudent.title = "Computer Science Student";
        mainStudent.setLocation("UAT Campus");
        
        // Create a Professor object (inherits from Person)
        Professor prof1 = new Professor();
        prof1.firstName = "Dr. Sarah";
        prof1.lastName = "Johnson";
        prof1.title = "Java Programming";
        prof1.setLocation("Classroom 101");
        
        // Create another Professor object
        Professor prof2 = new Professor();
        prof2.firstName = "Dr. Michael";
        prof2.lastName = "Chen";
        prof2.title = "Game Design";
        prof2.setLocation("Lab 205");
        
        System.out.println();
        System.out.println("Great! Let's start your day at UAT, " + studentFirst + "!");
        System.out.println("---");
        System.out.println();
        
        // Morning - 8:00 AM
        System.out.println("=== 8:00 AM - MORNING ===");
        System.out.println("You arrive at " + mainStudent.getLocation());
        mainStudent.setLocation("Student Lounge");
        System.out.println(mainStudent.firstName + " heads to the " + mainStudent.getLocation());
        mainStudent.talk();
        System.out.println();
        
        // Pause for user
        System.out.print("Press Enter to continue to morning class...");
        scanner.nextLine();
        System.out.println();
        
        // 9:00 AM - First Class
        System.out.println("=== 9:00 AM - FIRST CLASS ===");
        mainStudent.setLocation(prof1.getLocation());
        System.out.println(mainStudent.firstName + " walks to " + mainStudent.getLocation());
        System.out.println();
        prof1.teach();
        System.out.println();
        mainStudent.study();
        System.out.println();
        
        // Pause for user
        System.out.print("Press Enter to continue to lunch...");
        scanner.nextLine();
        System.out.println();
        
        // 12:00 PM - Lunch Time
        System.out.println("=== 12:00 PM - LUNCH TIME ===");
        mainStudent.setLocation("UAT Cafeteria");
        System.out.println(mainStudent.firstName + " is hungry and heads to the " + mainStudent.getLocation());
        mainStudent.eat();
        prof1.setLocation("UAT Cafeteria");
        prof1.eat();
        System.out.println();
        
        // Pause for user
        System.out.print("Press Enter to continue to afternoon class...");
        scanner.nextLine();
        System.out.println();
        
        // 2:00 PM - Second Class
        System.out.println("=== 2:00 PM - AFTERNOON CLASS ===");
        mainStudent.setLocation(prof2.getLocation());
        System.out.println(mainStudent.firstName + " walks to " + mainStudent.getLocation());
        System.out.println();
        prof2.teach();
        System.out.println();
        mainStudent.study();
        System.out.println();
        
        // Pause for user
        System.out.print("Press Enter to continue to evening...");
        scanner.nextLine();
        System.out.println();
        
        // 5:00 PM - Evening Activities
        System.out.println("=== 5:00 PM - EVENING ===");
        mainStudent.setLocation("Library");
        System.out.println("Classes are over. " + mainStudent.firstName + " goes to the " + mainStudent.getLocation());
        mainStudent.study();
        System.out.println();
        
        // Pause for user
        System.out.print("Press Enter to end the day...");
        scanner.nextLine();
        System.out.println();
        
        // 9:00 PM - Going Home
        System.out.println("=== 9:00 PM - END OF DAY ===");
        System.out.println("What a productive day at UAT!");
        mainStudent.setLocation("Home");
        System.out.println(mainStudent.firstName + " heads " + mainStudent.getLocation());
        mainStudent.sleep();
        System.out.println();
        
        System.out.println("========================================");
        System.out.println("   Thanks for experiencing UAT!");
        System.out.println("   See you tomorrow, " + studentFirst + "!");
        System.out.println("========================================");
        
        // Close scanner
        scanner.close();
    }
}
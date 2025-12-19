// Student.java - Child class that extends Person
// Students inherit all properties and methods from Person
// The "extends" keyword creates the parent-child relationship

public class Student extends Person {
    
    // Student has its own special method that only students can do
    public void study() {
        System.out.println(firstName + " " + lastName + " is studying hard for the next exam.");
        System.out.println("Opening textbooks, reviewing notes, and practicing code...");
    }
}
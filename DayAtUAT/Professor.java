// Professor.java - Child class that extends Person
// Professors inherit all properties and methods from Person
// The "extends" keyword creates the parent-child relationship

public class Professor extends Person {
    
    // Professor has its own special method that only professors can do
    public void teach() {
        System.out.println("Professor " + lastName + " is teaching " + title + " class.");
        System.out.println("Students are taking notes and asking questions.");
    }
}
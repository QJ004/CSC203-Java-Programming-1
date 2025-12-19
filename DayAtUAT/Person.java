// Person.java - Parent class for all people at UAT
// This is the base class that Professor and Student will inherit from

public class Person {
    // Public properties - anyone can access these directly
    public String firstName;
    public String lastName;
    public String title;
    
    // Private property - only this class can directly access it
    // Other classes must use getter/setter methods
    private String location;
    
    // Public method - all people can talk
    public void talk() {
        System.out.println(firstName + " " + lastName + " is having a conversation.");
    }
    
    // Public method - all people can eat
    public void eat() {
        System.out.println(firstName + " is eating lunch at the UAT cafeteria.");
    }
    
    // Public method - all people can sleep
    public void sleep() {
        System.out.println(firstName + " is taking a power nap.");
    }
    
    // Getter method - allows other classes to READ the private location
    public String getLocation() {
        return location;
    }
    
    // Setter method - allows other classes to CHANGE the private location
    public void setLocation(String newLocation) {
        location = newLocation;
    }
}
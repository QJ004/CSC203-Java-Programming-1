// Token.java - Abstract base class for all design tokens
// This class uses ABSTRACTION - it defines the common structure for all tokens
// but leaves specific validation to the child classes

public abstract class Token {
    // Private instance variables (ENCAPSULATION)
    // These can only be accessed through getter/setter methods
    private String name;        // Token name (e.g., "primary-blue", "heading-large")
    private String value;       // Token value (e.g., "#3366FF", "24px")
    private String tokenType;   // Type of token ("Color" or "Font")
    
    // Constructor - initializes a token with name, value, and type
    public Token(String name, String value, String tokenType) {
        this.name = name;
        this.value = value;
        this.tokenType = tokenType;
    }
    
    // Getter method - returns the token name
    public String getName() {
        return name;
    }
    
    // Setter method - sets the token name
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter method - returns the token value
    public String getValue() {
        return value;
    }
    
    // Setter method - sets the token value
    public void setValue(String value) {
        this.value = value;
    }
    
    // Getter method - returns the token type
    public String getTokenType() {
        return tokenType;
    }
    
    // Setter method - sets the token type
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    // ABSTRACT METHOD - must be implemented by child classes
    // Each token type validates its value differently
    // ColorToken checks for valid hex codes, FontToken checks for valid font sizes
    public abstract boolean isValid();
    
    // Method to get a formatted display string for the token
    // This will be used to show tokens in the GUI list
    public String getDisplayString() {
        return tokenType + " | " + name + " | " + value;
    }
    
    // Override toString method for easy printing
    public String toString() {
        return getDisplayString();
    }
}
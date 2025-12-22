// FontToken.java - Child class that extends Token
// This class demonstrates INHERITANCE - it inherits all properties and methods from Token
// It also demonstrates POLYMORPHISM - it provides its own implementation of isValid()

public class FontToken extends Token {
    
    // Constructor - creates a FontToken with name and size value
    // Calls the parent class (Token) constructor using super()
    public FontToken(String name, String value) {
        // Call parent constructor with "Font" as the token type
        super(name, value, "Font");
    }
    
    // Implementation of abstract method from Token class
    // This validates that the value is a proper font size with units
    // POLYMORPHISM - FontToken has its own version of isValid()
    public boolean isValid() {
        // Get the value to validate
        String fontSize = getValue();
        
        // Check if value is null or empty
        if (fontSize == null || fontSize.isEmpty()) {
            return false;
        }
        
        // Remove spaces for easier validation
        fontSize = fontSize.trim();
        
        // Valid font sizes end with px, rem, or em (common CSS units)
        boolean hasPx = fontSize.endsWith("px");
        boolean hasRem = fontSize.endsWith("rem");
        boolean hasEm = fontSize.endsWith("em");
        
        // Must have one of the valid units
        if (!hasPx && !hasRem && !hasEm) {
            return false;
        }
        
        // Extract the numeric part by removing the unit
        String numericPart;
        if (hasPx) {
            numericPart = fontSize.substring(0, fontSize.length() - 2);
        } else if (hasRem) {
            numericPart = fontSize.substring(0, fontSize.length() - 3);
        } else { // hasEm
            numericPart = fontSize.substring(0, fontSize.length() - 2);
        }
        
        // Remove any remaining spaces
        numericPart = numericPart.trim();
        
        // Check if numeric part is empty
        if (numericPart.isEmpty()) {
            return false;
        }
        
        // Try to parse the numeric part as a number
        try {
            double size = Double.parseDouble(numericPart);
            
            // Font size should be positive
            if (size <= 0) {
                return false;
            }
            
            // Valid font size
            return true;
            
        } catch (NumberFormatException e) {
            // Not a valid number
            return false;
        }
    }
    
    // Additional method specific to FontToken
    // Returns just the numeric value without units
    public double getNumericValue() {
        String fontSize = getValue().trim();
        String numericPart;
        
        // Extract numeric part based on unit
        if (fontSize.endsWith("px")) {
            numericPart = fontSize.substring(0, fontSize.length() - 2);
        } else if (fontSize.endsWith("rem")) {
            numericPart = fontSize.substring(0, fontSize.length() - 3);
        } else { // em
            numericPart = fontSize.substring(0, fontSize.length() - 2);
        }
        
        // Parse and return
        try {
            return Double.parseDouble(numericPart.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
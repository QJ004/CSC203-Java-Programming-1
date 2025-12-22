// ColorToken.java - Child class that extends Token
// This class demonstrates INHERITANCE - it inherits all properties and methods from Token
// It also demonstrates POLYMORPHISM - it provides its own implementation of isValid()

public class ColorToken extends Token {
    
    // Constructor - creates a ColorToken with name and hex value
    // Calls the parent class (Token) constructor using super()
    public ColorToken(String name, String value) {
        // Call parent constructor with "Color" as the token type
        super(name, value, "Color");
    }
    
    // Implementation of abstract method from Token class
    // This validates that the value is a proper hex color code
    // POLYMORPHISM - ColorToken has its own version of isValid()
    public boolean isValid() {
        // Get the value to validate
        String hex = getValue();
        
        // Check if value is null or empty
        if (hex == null || hex.isEmpty()) {
            return false;
        }
        
        // Hex codes should start with #
        if (!hex.startsWith("#")) {
            return false;
        }
        
        // Remove the # symbol to check the rest
        String hexWithoutHash = hex.substring(1);
        
        // Valid hex codes are 3 or 6 characters long (e.g., #FFF or #FF00FF)
        if (hexWithoutHash.length() != 3 && hexWithoutHash.length() != 6) {
            return false;
        }
        
        // Check if all characters are valid hex digits (0-9, A-F, a-f)
        for (int i = 0; i < hexWithoutHash.length(); i++) {
            char c = hexWithoutHash.charAt(i);
            
            // Check if character is a valid hex digit
            boolean isDigit = (c >= '0' && c <= '9');
            boolean isUpperHex = (c >= 'A' && c <= 'F');
            boolean isLowerHex = (c >= 'a' && c <= 'f');
            
            if (!isDigit && !isUpperHex && !isLowerHex) {
                return false; // Invalid character found
            }
        }
        
        // All checks passed - this is a valid hex color code
        return true;
    }
    
    // Additional method specific to ColorToken
    // Returns the hex value in uppercase for consistency
    public String getNormalizedHex() {
        return getValue().toUpperCase();
    }
}
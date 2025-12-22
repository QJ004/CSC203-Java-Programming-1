// DesignTokenApp.java - Main GUI application for managing design tokens
// This is a UX Designer tool for managing color hex codes and font scaling units

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

// Main application class
@SuppressWarnings({"Convert2Lambda", "override"})
public class DesignTokenApp {
    
    // GUI components
    private JFrame frame;
    private JComboBox<String> typeSelector;
    private JTextField nameField;
    private JTextField valueField;
    private JTextArea tokenListArea;
    private JButton addButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JLabel statusLabel;
    private JLabel instructionsLabel;
    
    // Data storage - array of tokens (POLYMORPHISM - can hold ColorToken or FontToken)
    private Token[] tokens;
    private int tokenCount;
    private final int MAX_TOKENS = 100;
    
    // Constructor - initializes the application
    public DesignTokenApp() {
        // Initialize token array
        tokens = new Token[MAX_TOKENS];
        tokenCount = 0;
        
        // Create the GUI
        createGUI();
    }
    
    // Method to build the entire GUI
    private void createGUI() {
        // Create main window
        frame = new JFrame("Design Token Repository");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Color scheme for design app
        Color bgColor = new Color(248, 249, 250);
        Color accentColor = new Color(99, 102, 241); // Purple
        
        // TOP PANEL - Header with image and title
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(bgColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        // Load and display design icon
        try {
            ImageIcon icon = new ImageIcon("design-icon.png");
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(icon);
            headerPanel.add(imageLabel, BorderLayout.WEST);
        } catch (Exception e) {
            // If image not found, continue without it
        }
        
        // Title and description
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(bgColor);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        
        JLabel titleLabel = new JLabel("Design Token Repository");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(accentColor);
        
        JLabel descLabel = new JLabel("Manage your project's visual variables");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(Color.GRAY);
        
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlePanel.add(descLabel);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        // INSTRUCTIONS PANEL
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.setBackground(new Color(255, 251, 235));
        instructionsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(252, 211, 77), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel howToLabel = new JLabel("📖 How to Use:");
        howToLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        instructionsLabel = new JLabel("<html>" +
            "1. Select token type (Color or Font)<br>" +
            "2. Enter token name (e.g., 'primary-blue' or 'heading-large')<br>" +
            "3. Enter value - Colors: hex codes (#FF0000), Fonts: size with units (24px, 1.5rem)<br>" +
            "4. Click 'Add Token' to save<br>" +
            "5. Use 'Delete Last' to remove the most recent token<br>" +
            "6. Use 'Clear All' to start fresh" +
            "</html>");
        instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        
        instructionsPanel.add(howToLabel);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        instructionsPanel.add(instructionsLabel);
        
        // INPUT PANEL - Form to add new tokens
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(bgColor);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Add New Token"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Token Type selector
        JLabel typeLabel = new JLabel("Token Type:");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] types = {"Color", "Font"};
        typeSelector = new JComboBox<String>(types);
        typeSelector.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Token Name field
        JLabel nameLabel = new JLabel("Token Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Token Value field
        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        valueField = new JTextField();
        valueField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Example label
        JLabel exampleLabel = new JLabel("Example:");
        exampleLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        exampleLabel.setForeground(Color.GRAY);
        JLabel exampleValue = new JLabel("#3366FF or 16px");
        exampleValue.setFont(new Font("Arial", Font.ITALIC, 12));
        exampleValue.setForeground(Color.GRAY);
        
        // Add components to input panel
        inputPanel.add(typeLabel);
        inputPanel.add(typeSelector);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(valueLabel);
        inputPanel.add(valueField);
        inputPanel.add(exampleLabel);
        inputPanel.add(exampleValue);
        
        // DISPLAY PANEL - Shows all saved tokens
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(bgColor);
        displayPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Your Design Tokens"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Text area to display tokens
        tokenListArea = new JTextArea(12, 50);
        tokenListArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tokenListArea.setEditable(false);
        tokenListArea.setBackground(Color.WHITE);
        tokenListArea.setText("No tokens yet. Add your first token above!");
        
        JScrollPane scrollPane = new JScrollPane(tokenListArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        
        // BUTTON PANEL - Action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 15, 0));
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        
        // Add Token button
        addButton = new JButton("➕ Add Token");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(99, 102, 241));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        
        addButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                addToken();
            }
        });
        
        // Delete Last button
        deleteButton = new JButton("🗑️ Delete Last");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setBackground(new Color(239, 68, 68));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setEnabled(false);
        
        deleteButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                deleteLastToken();
            }
        });
        
        // Clear All button
        clearButton = new JButton("🔄 Clear All");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(new Color(245, 158, 11));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setEnabled(false);
        
        clearButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                clearAllTokens();
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        // STATUS PANEL - Shows messages to user
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(bgColor);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));
        
        statusLabel = new JLabel("Ready to add tokens!");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        statusLabel.setForeground(new Color(34, 197, 94));
        statusPanel.add(statusLabel);
        
        // Combine panels into main layout
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBackground(bgColor);
        topSection.add(headerPanel);
        topSection.add(instructionsPanel);
        topSection.add(Box.createRigidArea(new Dimension(0, 10)));
        topSection.add(inputPanel);
        
        JPanel bottomSection = new JPanel();
        bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.Y_AXIS));
        bottomSection.setBackground(bgColor);
        bottomSection.add(displayPanel);
        bottomSection.add(buttonPanel);
        bottomSection.add(statusPanel);
        
        frame.add(topSection, BorderLayout.NORTH);
        frame.add(bottomSection, BorderLayout.CENTER);
        
        // Make window visible
        frame.setVisible(true);
    }
    
    // Method to add a new token
    private void addToken() {
        // Check if array is full
        if (tokenCount >= MAX_TOKENS) {
            statusLabel.setText("❌ Token limit reached (100 tokens max)");
            statusLabel.setForeground(Color.RED);
            return;
        }
        
        // Get input values
        String type = (String) typeSelector.getSelectedItem();
        String name = nameField.getText().trim();
        String value = valueField.getText().trim();
        
        // Validate inputs - check for empty fields
        if (name.isEmpty()) {
            statusLabel.setText("❌ Error: Token name cannot be empty");
            statusLabel.setForeground(Color.RED);
            return;
        }
        
        if (value.isEmpty()) {
            statusLabel.setText("❌ Error: Token value cannot be empty");
            statusLabel.setForeground(Color.RED);
            return;
        }
        
        // Create token based on type (POLYMORPHISM)
        Token newToken = null;
        
        if (type.equals("Color")) {
            newToken = new ColorToken(name, value);
        } else if (type.equals("Font")) {
            newToken = new FontToken(name, value);
        }
        
        // Validate token value
        if (newToken != null && !newToken.isValid()) {
            // Show specific error message based on type
            if (type.equals("Color")) {
                statusLabel.setText("❌ Invalid hex code. Use format: #FFF or #FF00FF");
            } else {
                statusLabel.setText("❌ Invalid font size. Use format: 16px, 1.5rem, or 2em");
            }
            statusLabel.setForeground(Color.RED);
            return;
        }
        
        // Add token to array
        tokens[tokenCount] = newToken;
        tokenCount = tokenCount + 1;
        
        // Update display
        updateTokenDisplay();
        
        // Clear input fields
        nameField.setText("");
        valueField.setText("");
        nameField.requestFocus();
        
        // Enable delete and clear buttons
        deleteButton.setEnabled(true);
        clearButton.setEnabled(true);
        
        // Show success message
        statusLabel.setText("✅ Token added successfully! Total: " + tokenCount);
        statusLabel.setForeground(new Color(34, 197, 94));
        
        // Play success sound
        playSuccessSound();
    }
    
    // Method to delete the last added token
    private void deleteLastToken() {
        if (tokenCount > 0) {
            // Remove last token
            tokenCount = tokenCount - 1;
            tokens[tokenCount] = null;
            
            // Update display
            updateTokenDisplay();
            
            // Disable buttons if no tokens left
            if (tokenCount == 0) {
                deleteButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
            
            // Show message
            statusLabel.setText("🗑️ Last token deleted. Remaining: " + tokenCount);
            statusLabel.setForeground(new Color(239, 68, 68));
        }
    }
    
    // Method to clear all tokens
    private void clearAllTokens() {
        // Confirm with user
        int choice = JOptionPane.showConfirmDialog(
            frame,
            "Are you sure you want to delete all tokens?",
            "Clear All Tokens",
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            // Clear array using loop
            for (int i = 0; i < tokenCount; i++) {
                tokens[i] = null;
            }
            tokenCount = 0;
            
            // Update display
            updateTokenDisplay();
            
            // Disable buttons
            deleteButton.setEnabled(false);
            clearButton.setEnabled(false);
            
            // Show message
            statusLabel.setText("🔄 All tokens cleared!");
            statusLabel.setForeground(new Color(245, 158, 11));
        }
    }
    
    // Method to update the token list display
    private void updateTokenDisplay() {
        if (tokenCount == 0) {
            tokenListArea.setText("No tokens yet. Add your first token above!");
        } else {
            // Build display string using loop
            StringBuilder display = new StringBuilder();
            display.append("TYPE     | NAME                    | VALUE\n");
            display.append("---------|-------------------------|------------------\n");
            
            for (int i = 0; i < tokenCount; i++) {
                Token token = tokens[i];
                
                // Format: Type | Name | Value
                String type = token.getTokenType();
                String name = token.getName();
                String value = token.getValue();
                
                // Pad strings for alignment
                String formattedLine = String.format("%-8s | %-23s | %s\n", 
                    type, name, value);
                display.append(formattedLine);
            }
            
            display.append("\nTotal Tokens: " + tokenCount);
            tokenListArea.setText(display.toString());
        }
    }
    
    // Method to play success sound when token is added
    private void playSuccessSound() {
        try {
            // Load sound file
            File soundFile = new File("success.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            
            // Get clip and play
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
        } catch (Exception e) {
            // If sound fails, just continue (don't show error to user)
            System.out.println("Sound not found: " + e.getMessage());
        }
    }
    
    // Main method - entry point of the program
    public static void main(String[] args) {
        // Run GUI on event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
            public void run() {
                new DesignTokenApp();
            }
        });
    }
}
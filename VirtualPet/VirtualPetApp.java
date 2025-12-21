// VirtualPetApp.java - GUI application for a virtual pet
// Import statements - these bring in the GUI libraries we need
import javax.swing.*;  // Swing library for GUI components (JFrame, JButton, JLabel, etc.)
import java.awt.*;     // AWT library for layouts and colors
import java.awt.event.*; // Event handling for button clicks

// Main class that creates the virtual pet GUI
@SuppressWarnings({"Convert2Lambda", "override"})
public class VirtualPetApp {
    
    // Instance variables - these track the pet's status
    private int hunger;        // Hunger level (0-10, lower is better)
    private int happiness;     // Happiness level (0-10, higher is better)
    private int cleanliness;   // Cleanliness level (0-10, higher is better)
    
    // GUI components - these are the visual elements
    private JFrame frame;           // The main window
    private JLabel hungerLabel;     // Displays hunger level
    private JLabel happinessLabel;  // Displays happiness level
    private JLabel cleanLabel;      // Displays cleanliness level
    private JLabel petImageLabel;   // Displays the pet image
    private JLabel statusLabel;     // Displays status messages
    
    // Constructor - runs when we create a VirtualPetApp object
    public VirtualPetApp() {
        // Initialize pet attributes to starting values
        hunger = 5;        // Start at medium hunger
        happiness = 5;     // Start at medium happiness
        cleanliness = 5;   // Start at medium cleanliness
        
        // Create the GUI
        createGUI();
    }
    
    // Method that builds the entire GUI
    private void createGUI() {
        // Create the main window (JFrame)
        frame = new JFrame("Virtual Pet - Golden Retriever");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when X is clicked
        frame.setSize(500, 600); // Set window size (width, height)
        frame.setLayout(new BorderLayout()); // Use BorderLayout manager
        
        // Create the top panel for the pet image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(255, 248, 220)); // Light cream color
        
        // Load and display the pet image
        ImageIcon petIcon = new ImageIcon("dog.png"); // Load dog.png from same folder
        // Scale the image to fit nicely (250x250 pixels)
        Image scaledImage = petIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        petIcon = new ImageIcon(scaledImage);
        petImageLabel = new JLabel(petIcon);
        imagePanel.add(petImageLabel);
        
        // Create the center panel for status display
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, 10px spacing
        statusPanel.setBackground(new Color(255, 248, 220)); // Match background color
        statusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        
        // Create labels to show pet's current stats
        hungerLabel = new JLabel("🍖 Hunger Level: " + hunger + "/10", SwingConstants.CENTER);
        hungerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        happinessLabel = new JLabel("😊 Happiness Level: " + happiness + "/10", SwingConstants.CENTER);
        happinessLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        cleanLabel = new JLabel("🛁 Cleanliness Level: " + cleanliness + "/10", SwingConstants.CENTER);
        cleanLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        statusLabel = new JLabel("Welcome! Take care of your puppy!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        statusLabel.setForeground(new Color(0, 100, 0)); // Dark green text
        
        // Add all labels to the status panel
        statusPanel.add(hungerLabel);
        statusPanel.add(happinessLabel);
        statusPanel.add(cleanLabel);
        statusPanel.add(statusLabel);
        
        // Create the bottom panel for action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 15, 0)); // 1 row, 3 columns, 15px spacing
        buttonPanel.setBackground(new Color(255, 248, 220)); // Match background color
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // Add padding
        
        // Create the Feed button
        JButton feedButton = new JButton("🍖 Feed");
        feedButton.setFont(new Font("Arial", Font.BOLD, 16));
        feedButton.setBackground(new Color(255, 200, 100)); // Orange color
        feedButton.setFocusPainted(false); // Remove focus border
        
        // Add action listener - this code runs when Feed button is clicked
        feedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                feedPet(); // Call the feedPet method
            }
        });
        
        // Create the Play button
        JButton playButton = new JButton("🎾 Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setBackground(new Color(150, 200, 255)); // Light blue color
        playButton.setFocusPainted(false);
        
        // Add action listener for Play button
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playWithPet(); // Call the playWithPet method
            }
        });
        
        // Create the Groom button
        JButton groomButton = new JButton("🛁 Groom");
        groomButton.setFont(new Font("Arial", Font.BOLD, 16));
        groomButton.setBackground(new Color(200, 255, 200)); // Light green color
        groomButton.setFocusPainted(false);
        
        // Add action listener for Groom button
        groomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                groomPet(); // Call the groomPet method
            }
        });
        
        // Add all buttons to the button panel
        buttonPanel.add(feedButton);
        buttonPanel.add(playButton);
        buttonPanel.add(groomButton);
        
        // Add all panels to the main frame
        frame.add(imagePanel, BorderLayout.NORTH);    // Image at top
        frame.add(statusPanel, BorderLayout.CENTER);  // Status in center
        frame.add(buttonPanel, BorderLayout.SOUTH);   // Buttons at bottom
        
        // Make the window visible
        frame.setVisible(true);
    }
    
    // Method called when Feed button is clicked
    private void feedPet() {
        // Decrease hunger (but don't go below 0)
        if (hunger > 0) {
            hunger = hunger - 2; // Decrease by 2
            if (hunger < 0) {
                hunger = 0; // Make sure it doesn't go negative
            }
            statusLabel.setText("Yum! Your puppy enjoyed the meal! 🍖");
        } else {
            statusLabel.setText("Your puppy is already full! 😊");
        }
        
        // Update the display
        updateDisplay();
    }
    
    // Method called when Play button is clicked
    private void playWithPet() {
        // Increase happiness (but don't go above 10)
        if (happiness < 10) {
            happiness = happiness + 2; // Increase by 2
            if (happiness > 10) {
                happiness = 10; // Cap at 10
            }
            statusLabel.setText("Woof woof! Your puppy loves playing! 🎾");
            
            // Playing also increases hunger a bit
            hunger = hunger + 1;
            if (hunger > 10) {
                hunger = 10;
            }
        } else {
            statusLabel.setText("Your puppy is super happy already! 😄");
        }
        
        // Update the display
        updateDisplay();
    }
    
    // Method called when Groom button is clicked
    private void groomPet() {
        // Increase cleanliness (but don't go above 10)
        if (cleanliness < 10) {
            cleanliness = cleanliness + 3; // Increase by 3
            if (cleanliness > 10) {
                cleanliness = 10; // Cap at 10
            }
            statusLabel.setText("Sparkle! Your puppy is so clean now! 🛁");
        } else {
            statusLabel.setText("Your puppy is already spotless! ✨");
        }
        
        // Update the display
        updateDisplay();
    }
    
    // Method that updates all the status labels
    private void updateDisplay() {
        // Update hunger label with current value
        hungerLabel.setText("🍖 Hunger Level: " + hunger + "/10");
        
        // Update happiness label with current value
        happinessLabel.setText("😊 Happiness Level: " + happiness + "/10");
        
        // Update cleanliness label with current value
        cleanLabel.setText("🛁 Cleanliness Level: " + cleanliness + "/10");
        
        // Check if pet needs attention
        if (hunger > 7) {
            statusLabel.setText("Your puppy is getting very hungry! 🍖");
        } else if (happiness < 3) {
            statusLabel.setText("Your puppy seems sad. Play with them! 🎾");
        } else if (cleanliness < 3) {
            statusLabel.setText("Your puppy is getting dirty. Time for a bath! 🛁");
        }
    }
    
    // Main method - the entry point of the program
    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI is created on the correct thread
        SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
            public void run() {
                // Create a new VirtualPetApp object (this starts everything)
                new VirtualPetApp();
            }
        });
    }
}
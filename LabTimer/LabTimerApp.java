// LabTimerApp.java - GUI application for timing lab experiments
// Import statements for GUI and sound
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

// Main class for the Lab Timer application
@SuppressWarnings({"Convert2Lambda", "override"})
public class LabTimerApp {
    
    // GUI components
    private JFrame frame;              // Main window
    private JLabel imageLabel;         // Displays lab image
    private JLabel timerDisplay;       // Shows countdown time
    private JLabel statusLabel;        // Shows status messages
    private JButton startButton;       // Start timer button
    private JButton stopButton;        // Stop timer button
    private JButton resetButton;       // Reset timer button
    private JTextField minutesField;   // Input for minutes
    private JTextField secondsField;   // Input for seconds
    private JComboBox<String> soundSelector; // Dropdown to choose alert sound
    
    // Timer variables
    private Timer timer;               // Swing Timer for countdown
    private int totalSeconds;          // Total seconds for countdown
    private int remainingSeconds;      // Seconds remaining
    private boolean isRunning;         // Is timer currently running
    
    // Sound file names (stored in array)
    private String[] soundFiles = {
        "timerAlarm.wav",
        "alertBeep.wav",
        "beepSound.wav",
        "countdownBeep.wav",
        "notificationSound.wav"
    };
    
    // Constructor - initializes the application
    public LabTimerApp() {
        isRunning = false;
        totalSeconds = 0;
        remainingSeconds = 0;
        createGUI();
    }
    
    // Method to build the entire GUI
    private void createGUI() {
        // Create main window
        frame = new JFrame("Lab Timer - Experiment Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 700);
        frame.setLayout(new BorderLayout());
        
        // Set background color for science theme
        Color bgColor = new Color(240, 248, 255); // Light blue
        
        // TOP PANEL - Lab Image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(bgColor);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        // Load and display lab image
        try {
            ImageIcon labIcon = new ImageIcon("lab.png");
            // Scale image to fit nicely
            Image scaledImage = labIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            labIcon = new ImageIcon(scaledImage);
            imageLabel = new JLabel(labIcon);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            // If image not found, show text instead
            imageLabel = new JLabel("Lab Timer");
            imageLabel.setFont(new Font("Arial", Font.BOLD, 24));
            imagePanel.add(imageLabel);
        }
        
        // CENTER PANEL - Timer display and controls
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Timer display label (shows countdown)
        timerDisplay = new JLabel("00:00", SwingConstants.CENTER);
        timerDisplay.setFont(new Font("Monospaced", Font.BOLD, 72));
        timerDisplay.setForeground(new Color(0, 100, 0)); // Dark green
        timerDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Status label (shows messages)
        statusLabel = new JLabel("Set timer and click Start", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add spacing
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(timerDisplay);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(statusLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Input panel for setting time
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(bgColor);
        
        JLabel minutesLabel = new JLabel("Minutes:");
        minutesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        minutesField = new JTextField("0", 3);
        minutesField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel secondsLabel = new JLabel("  Seconds:");
        secondsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        secondsField = new JTextField("30", 3);
        secondsField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        inputPanel.add(minutesLabel);
        inputPanel.add(minutesField);
        inputPanel.add(secondsLabel);
        inputPanel.add(secondsField);
        
        centerPanel.add(inputPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Sound selector dropdown
        JPanel soundPanel = new JPanel();
        soundPanel.setLayout(new FlowLayout());
        soundPanel.setBackground(bgColor);
        
        JLabel soundLabel = new JLabel("Alert Sound:");
        soundLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Create dropdown with sound options
        String[] soundNames = {
            "Timer Alarm",
            "Alert Beep",
            "Beep Sound",
            "Countdown Beep",
            "Notification Sound"
        };
        soundSelector = new JComboBox<String>(soundNames);
        soundSelector.setFont(new Font("Arial", Font.PLAIN, 14));
        
        soundPanel.add(soundLabel);
        soundPanel.add(soundSelector);
        
        centerPanel.add(soundPanel);
        
        // BOTTOM PANEL - Control buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 15, 0));
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        
        // Create Start button
        startButton = new JButton("▶ Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(100, 200, 100)); // Light green
        startButton.setFocusPainted(false);
        
        // Add action listener for Start button
        startButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        
        // Create Stop button
        stopButton = new JButton("⏸ Stop");
        stopButton.setFont(new Font("Arial", Font.BOLD, 18));
        stopButton.setBackground(new Color(255, 200, 100)); // Light orange
        stopButton.setFocusPainted(false);
        stopButton.setEnabled(false); // Disabled initially
        
        // Add action listener for Stop button
        stopButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });
        
        // Create Reset button
        resetButton = new JButton("↻ Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBackground(new Color(200, 200, 255)); // Light blue
        resetButton.setFocusPainted(false);
        
        // Add action listener for Reset button
        resetButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        
        // Add buttons to panel
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        
        // Add all panels to main frame
        frame.add(imagePanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Make window visible
        frame.setVisible(true);
    }
    
    // Method called when Start button is clicked
    private void startTimer() {
        // If already running, do nothing
        if (isRunning) {
            statusLabel.setText("Timer is already running!");
            return;
        }
        
        // Get input values from text fields
        try {
            int minutes = Integer.parseInt(minutesField.getText());
            int seconds = Integer.parseInt(secondsField.getText());
            
            // Validate input
            if (minutes < 0 || seconds < 0) {
                statusLabel.setText("Please enter positive numbers!");
                return;
            }
            
            if (minutes == 0 && seconds == 0) {
                statusLabel.setText("Please set a time greater than 0!");
                return;
            }
            
            // Calculate total seconds
            totalSeconds = (minutes * 60) + seconds;
            remainingSeconds = totalSeconds;
            
            // Update display
            updateDisplay();
            
            // Set status
            isRunning = true;
            statusLabel.setText("Timer running...");
            timerDisplay.setForeground(new Color(0, 100, 0)); // Green while running
            
            // Enable/disable buttons
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            minutesField.setEnabled(false);
            secondsField.setEnabled(false);
            
            // Create and start Swing Timer (fires every 1000ms = 1 second)
            timer = new Timer(1000, new ActionListener() {
                @SuppressWarnings("unused")
                public void actionPerformed(ActionEvent e) {
                    tick();
                }
            });
            timer.start();
            
        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter valid numbers!");
        }
    }
    
    // Method called every second by the timer
    private void tick() {
        // Decrease remaining seconds
        remainingSeconds = remainingSeconds - 1;
        
        // Update display
        updateDisplay();
        
        // Check if timer finished
        if (remainingSeconds <= 0) {
            timerFinished();
        }
        
        // Change color when less than 10 seconds remain
        if (remainingSeconds <= 10 && remainingSeconds > 0) {
            timerDisplay.setForeground(new Color(200, 0, 0)); // Red warning
        }
    }
    
    // Method called when Stop button is clicked
    private void stopTimer() {
        if (isRunning && timer != null) {
            timer.stop();
            isRunning = false;
            statusLabel.setText("Timer paused");
            
            // Enable/disable buttons
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            minutesField.setEnabled(true);
            secondsField.setEnabled(true);
        }
    }
    
    // Method called when Reset button is clicked
    private void resetTimer() {
        // Stop timer if running
        if (timer != null) {
            timer.stop();
        }
        
        // Reset all values
        isRunning = false;
        totalSeconds = 0;
        remainingSeconds = 0;
        
        // Reset display
        timerDisplay.setText("00:00");
        timerDisplay.setForeground(new Color(0, 100, 0)); // Back to green
        statusLabel.setText("Set timer and click Start");
        
        // Reset input fields
        minutesField.setText("0");
        secondsField.setText("30");
        
        // Enable/disable buttons
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        minutesField.setEnabled(true);
        secondsField.setEnabled(true);
    }
    
    // Method called when timer reaches 0
    private void timerFinished() {
        // Stop the timer
        timer.stop();
        isRunning = false;
        
        // Update display
        timerDisplay.setText("00:00");
        timerDisplay.setForeground(new Color(200, 0, 0)); // Red
        statusLabel.setText("Time's up! Experiment complete!");
        
        // Enable/disable buttons
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        resetButton.setEnabled(true);
        
        // Play selected sound
        playSound();
    }
    
    // Method to update the timer display
    private void updateDisplay() {
        // Calculate minutes and seconds from remaining seconds
        int displayMinutes = remainingSeconds / 60;
        int displaySeconds = remainingSeconds % 60;
        
        // Format as MM:SS with leading zeros
        String timeString = String.format("%02d:%02d", displayMinutes, displaySeconds);
        
        // Update label
        timerDisplay.setText(timeString);
    }
    
    // Method to play the selected sound file
    private void playSound() {
        try {
            // Get selected sound index from dropdown
            int selectedIndex = soundSelector.getSelectedIndex();
            String soundFile = soundFiles[selectedIndex];
            
            // Load the sound file
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            // Get a clip to play the sound
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            // Play the sound
            clip.start();
            
            statusLabel.setText("Playing alert sound: " + soundSelector.getSelectedItem());
            
        } catch (Exception e) {
            // If sound fails to play, show error message
            statusLabel.setText("Error playing sound - check file is in folder");
            System.out.println("Sound error: " + e.getMessage());
        }
    }
    
    // Main method - entry point of the program
    public static void main(String[] args) {
        // Run GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
            public void run() {
                new LabTimerApp();
            }
        });
    }
}
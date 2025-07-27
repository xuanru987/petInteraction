
/**
 * A GUI that interacts with the user to allow them to create a pet and take care of it
 * Displays a welcome screen at first 
 * After pet selection the pet, its attributes, the player's resources and the pet's behaviour are displayed
 * @author Sydney Liu
 * @version 26/07/2025
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;

public class PetGUI extends JFrame{ //Inherit (gain access to all methods and fields) from JFrame, a class that helps make the app window
    JButton dogButton; //Button for creating dog
    JButton catButton; //Button for creating cat
    JButton otterButton; //Button for creating otter
    PetPanel petPanel;
    public static final int PANEL_WIDTH = 1500;
    public static final int PANEL_HEIGHT = 1500;
    /**
     * Constructor for objects of class PetGUI
     */
    public PetGUI()
    {
        // initialise GUI settings & instance variables
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //The program ends when one closes the window
        this.setTitle("Pet Interaction Game");
        this.setLocationRelativeTo(null); //Window centred relative to nothing, therefore centred on the screen
        this.setSize(1500, 1500);
        this.setLayout(null); //Prevent the "layout manager" from changing the positions of components, which initially happened before ChatGPT suggested adding this line
        this.setVisible(true);//Show the GUI window
        
        //Create the panel (which holds text and images) and add to frame (window)
        petPanel = new PetPanel();
        petPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(petPanel);
        
        //Initialise buttons
        dogButton = new JButton("Dog");
        catButton = new JButton("Cat");
        otterButton = new JButton("Otter");
        
        //Set the positions and sizes of each button and add them to window
        dogButton.setBounds(300, 250, 100, 70); 
        catButton.setBounds(600, 250, 100, 70);
        otterButton.setBounds(900, 250, 100, 70);
        this.add(dogButton);
        this.add(catButton);
        this.add(otterButton);
        
    }
    
    /**
     * Call the paint method from the JFrame superclass to initialise the GUI window
     * This method gets called automatically
     */
    public void paint(Graphics g){ //Method to override the paint method
        super.paint(g); //Call the original paint method
    }
    
    /**
     * Main method
     */
    public static void main(String [] args){
        PetGUI petGui = new PetGUI();
    }
}

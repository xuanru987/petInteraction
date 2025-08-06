
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
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class PetGUI extends JFrame{ //Inherit (gain access to all methods and fields) from JFrame, a class that helps make the "frame", in this case i.e. app window
    JButton dogButton; //Button for creating dog
    JButton catButton; //Button for creating cat
    JButton otterButton; //Button for creating otter
    HomePanel homePanel;
    public static final int PANEL_WIDTH = 1500;
    public static final int PANEL_HEIGHT = 1500;
    PetPanel petPanel;
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
        this.setLayout(new FlowLayout()); //
        this.setVisible(true);//Show the GUI window
        
        //Create the panel which holds welcome message and add to frame (window)
        homePanel = new HomePanel();
        homePanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.add(homePanel);
        homePanel.setVisible(true);//Show the welcome message panel
        
        //Initialise buttons
        dogButton = new JButton("Dog");
        catButton = new JButton("Cat");
        otterButton = new JButton("Otter");
        
        //add buttons to panel and show them
        this.add(dogButton);
        this.add(catButton);
        this.add(otterButton);
        dogButton.setSize(100, 50); 
        catButton.setSize(100, 50);
        otterButton.setSize(100, 50);
        
        //Add action listeners to buttons so that the progrqam responds appropriately to button clicks
        dogButton.addActionListener(new ActionListener(){
            /**
             * Clear the home page and show a dog and its attributes
             * Called by Swing when the dog button is clicked
             * Overrides the actionPerformed class in the java.awt.event.ActionListener interface (a class with "empty methods")
             * @param event -- stores details of the input event occured -- in this case the button click 
             */
            public void actionPerformed(ActionEvent event){
                homePanel.setVisible(false);
                addPetPanel("Pedro", "dog"); //I initially put the name and pet type the wrong way round and ChatGPT caught my error
                hideButtons();
            }
            
        });  
        
        catButton.addActionListener(new ActionListener(){
            /**
             * Clear the home page and show a cat and its attributes
             * Called by Swing when the cat button is clicked
             * Overrides the actionPerformed class in the java.awt.event.ActionListener interface (a class with "empty methods")
             * @param event -- stores details of the input event occured -- in this case the button click 
             */
            public void actionPerformed(ActionEvent event){
                homePanel.setVisible(false);
                addPetPanel("Kitty", "cat");//I initially put the name and pet type the wrong way round and ChatGPT caught my error
                hideButtons();
            }
            
        }); 
        otterButton.addActionListener(new ActionListener(){
            /**
             * Clear the home page and show an otter and its attributes
             * Called by Swing when the otter button is clicked
             * Overrides the actionPerformed class in the java.awt.event.ActionListener interface (a class with "empty methods")
             * @param event -- stores details of the input event occured -- in this case the button click 
             */
            public void actionPerformed(ActionEvent event){
                homePanel.setVisible(false);
                hideButtons();
                addPetPanel("晉佑", "otter");
            }
            
        });
    }
    /**
     * Override the paint method from the JFrame superclass
     * Within this method, call the original method to initialise the GUI
     * Then,render the panels and buttons
     * @param g - object of the Graphics class which helps us draw things in the GUI
     */
    public void paint(Graphics g){
        super.paint(g); //Call original method
        paintComponents(g); //ChatGPT told me to do this, because swing was designed for us to call methods to paint components and only painting the whole frame (super.paint(g)) may result in some components not showing 
    }
    
    /**
     * Hide all buttons
     * ChatGPT reminded me to hide the buttons after the pet is shown
     */
    public void hideButtons(){
        dogButton.setVisible(false);
        catButton.setVisible(false);
        otterButton.setVisible(false);
    }
    
    /**
     * Create, set location of, and display the panel for the pet image
     * @param String petName -- name of pet
     * @param String petType -- type of pet
     */
    public void addPetPanel(String petName, String petType){
        Pet pet = new Pet(petType, petName); //Create the pet to be displayed in the panel
        petPanel = new PetPanel(pet);
        petPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        add(petPanel);
    }
    
    /**
     * Create, set location of, and display the panel for the pet's attributes
     */
    
    
    /**
     * Main method
     */
    public static void main(String [] args){
        PetGUI petGui = new PetGUI();
    }
    
}

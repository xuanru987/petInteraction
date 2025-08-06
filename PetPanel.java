import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * A panel, effectively a rectangle in the GUI window, used to display the pet and its attributes
 *
 * @author Sydney Liu
 * @version 28/07/2025
 */
public class PetPanel extends JPanel //Inherits (gains access to all methods and fields of) from the JPanel class from Swing
{
    // instance variables - replace the example below with your own
    Pet pet; //Pet to be displayed
    private int x; //The pet's x-coordinate
    private int y; //The pet's y-coordinate
    private int headWidth; //Width of pet's head
    private int headHeight; //Height of pet's head
    private int bodyLength; //Length of pet's body
    private int bodyWidth; //Width of the pet's body
    private int limbsLength; //Length of each limb
    private int limbsWidth; //Width of each limb
    private int bodyDisplacement; //Displacement of the body from the coordinate I want because of blank space on the image that I can't crop
    private int headDisplacement; //Displacement of the head from the coordinate I want because of blank space on the image
    private int legDiplacement; //Displacement of legs 
    private int leg1x; //X-position of leftmost leg
    private int legGap; //Gap between each leg
    /**
     * Constructor for objects of class PetPanel
     * @param - Pet pet -- pet to be displayed
     */
    public PetPanel(Pet pet)
    {
        // initialise instance variables
        this.pet = pet;
        headHeight = 100;
        if(pet.getType().equals("otter")){
            headWidth = headHeight * 3/2;
        }
        else{
            headWidth = headHeight;
        }
        bodyLength = 100* 2;
        bodyWidth = bodyLength/2;
        limbsLength = headHeight * 7 /10;
        limbsWidth = limbsLength / 4;
        bodyDisplacement = bodyLength/4;
        headDisplacement = headWidth/3;
        legDiplacement = limbsLength /10; 
        x = 800;
        y = 300;
        leg1x = x - bodyLength/2;
        legGap = bodyWidth/6;
    }
    
    /**
     * Override the paintComponent method from the JFrame superclass
     * Within this method, call the original method to reset the GUI window, then display some images
     * This method gets called automatically
     */
    public void paintComponent(Graphics g){ //Method to override the paint method
        super.paintComponent(g); //Call the original paint method to reset GUI
        Graphics2D g2D = (Graphics2D) g; //Convert g into a Graphics2D object, wchih has better functionality
        g2D.drawImage(pet.getHead(), x, y - headHeight + headDisplacement, headWidth, headHeight, null);
        g2D.drawImage(pet.getBody(), x - bodyLength + bodyDisplacement, y, bodyLength, bodyWidth, null);
        for(int i = 0; i < 4; i++){
        g2D.drawImage(pet.getLeg(), leg1x + (legGap + limbsWidth) * i, y + bodyWidth - legDiplacement, limbsWidth, limbsLength, null);
        }
    }
}




/**
 * A "panel", effectively a rectangle, within the GUI window.
 * Used to organise custom text and buttons without interferring with things like the title of the window.
 * I had some layout issues when trying to draw text directly in the frame, and ChatGPT suggested I draw in the panel
 * @author Sydney Liu
 * @version 26/07/2025
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Dimension;

public class HomePanel extends JPanel //Inherits (gains access to all methods and fields of) from the JPanel class from Swing
{
   
    /**
     * Constructor for objects of class PetPanel
     */
    public HomePanel()
    {
    }
    
     /**
     * Override the paintComponent method in the JPanel superclass to customise what's displayed
     * @param  g - an object of the java.awt.Graphics class ("Graphics object"), that helps you draw shapes, images, etc.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); //Call the original paintComponent method from JPanel to clear the panel before adding extra text and images 
        Graphics2D g2D = (Graphics2D) g; //Convert g into a Graphics2D object, wchih has better functionality
        g2D.setFont(new Font("Consolas", Font.BOLD, 30)); //Set font to Consolas, bold and size 30
        g2D.drawString("Welcome to Pet Interactor. Which pet do you want to bring home?", 200, 100);
    }
}

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
/**
 * Panel to display the attributes that we find less essential to feeling like we "know" the pet
 * E.g. the minimum and maximum weights to stay alive, which are still important and are shown when a button on the UI is clicked
 * @author Sydney Liu
 * @version 4/8/25
 */
public class extraAttributes extends JPanel
{
    Pet pet; //Pet of which the attributes are displayed
    private int x;//x-coordinate of top attribute
    private int y; //y-coordinate of top attribute
    private int gap; //Gap between each attribute displayed
    /**
     * Constructor for objects of class extraAttributes
     */
    public extraAttributes(Pet pet)
    {
        this.pet = pet;
        x = 1000;
        y = 1000;
        gap = 20;
    }

    /**
     * Override the paintComponent method from the JPanel superclass
     * Within this method, call the original method to reset the panel, then display the attributes
     * This method gets called automatically
     */
    public void paintComponent(Graphics g){ //Method to override the paintComponent method
        super.paintComponent(g); //Call the original paint method to reset panel
        Graphics2D g2D = (Graphics2D) g; //Convert g into a Graphics2D object, wchih has better functionality
        g2D.drawString("Min Weight Allowed" + pet.getMin(), x, y);
        g2D.drawString("Max Weight Allowed" + pet.getMax(), x, y + gap);
        g2D.drawString("Metabolism:" + pet.getMet(), x, y + gap * 2);
    }
}

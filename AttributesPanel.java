import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A panel, effectively a rectangle in the GUI window to display the attributes of the pet
 *
 * @Sydney Liu
 * @31/07/2025
 */ 

public class AttributesPanel extends JPanel
{
    // instance variables - replace the example below with your own
    private JButton showMore; //Button to show more attributes
    private JButton showLess; //Button to hide some attributes
    private int x1;//x-coordinate of top-left attribute
    private int y1; //y-coordinate of top-left attribute
    private int x2; //x-coordinate of top-right attribute
    private int gap; //Gap between each attribute displayed
    private Pet pet; //Pet to display attributes of
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;
    public AttributesPanel(Pet pet){
        this.setLayout(null); //Prevent the "layout manager" from changing the positions of components, which initially happened before ChatGPT suggested adding this line
        this.pet = pet;
        x1 = 20;
        y1 = 20;
        x2 = 1000;
        gap = 20;
        showMore = new JButton("Show More:");
        showLess = new JButton("Show Less:");
        showMore.setBounds(x2, y1 + gap, BUTTON_WIDTH, BUTTON_HEIGHT);
        
        //Add action listeners to call the approprate methods when the buttons are clicked
        
        showMore.addActionListener(new ActionListener(){
            /**
             * Hide and inactivate the "show more" button and show the "show less" button instead
             * Show the less essential attributes of the pet
             * Called by Swing when the dog button is clicked
             * Overrides the actionPerformed class in the java.awt.event.ActionListener interface (a class with "empty methods")
             * @param event -- stores details of the input event occured -- in this case the button click 
             */
            public void actionPerformed(ActionEvent event){
                showMore.setBounds(0, 0, 0, 0);
                showLess.setBounds(x2, y1 + gap, BUTTON_WIDTH, BUTTON_HEIGHT);
            }
        });
        
        showLess.addActionListener(new ActionListener(){
            /**
             * Hide and inactivate the "show less" button and show the "show more" button instead
             * Hide the less essential attributes of the pet
             * Called by Swing when the dog button is clicked
             * Overrides the actionPerformed class in the java.awt.event.ActionListener interface (a class with "empty methods")
             * @param event -- stores details of the input event occured -- in this case the button click 
             */
            public void actionPerformed(ActionEvent event){
                showLess.setBounds(0, 0, 0, 0);
                showMore.setBounds(x2, y1 + gap, BUTTON_WIDTH, BUTTON_HEIGHT);
            }
        });
    }

    /**
     * Override the paintComponent method from the JPanel superclass
     * Within this method, call the original method to reset the panel, then display the attributes
     * This method gets called automatically
     */
    public void paintComponent(Graphics g){ //Method to override the paintComponent method
        super.paintComponent(g); //Call the original paint method to reset panel
        Graphics2D g2D = (Graphics2D) g; //Convert g into a Graphics2D object, wchih has better functionality
        g2D.drawString("Name: " + pet.getName(), x1, y1);
        g2D.drawString("Age: " + pet.getAge(), x1, y1 + gap);
        g2D.drawString("Weight: " + pet.getWeight(), x2, y1);
    }
}

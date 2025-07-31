import java.util.HashMap;
import ecs100.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 
 * A virtual pet users can interact with via a GUI (programmed in another class)
 * Has a name, weight, max and min weight to stay alive, metabolism, age, and possibly thirst/hydration
 * Can perform certain animated actions on demand, like eating & drinking
 * @author Sydney Liu
 * @version 21/07/2025
 */
public class Pet
{
    //Initialise instance variables
    private String type; //Type of animal
    private String name; //Name of pet
    private double weight; //Weight of pet in Kg
    private double minWeight; //Minimum weight to stay alive
    private double maxWeight; //Maximum weight to stay alive
    private double metabolism; //Metabolism, in how many Kg it'll lose per hour without food (easier for the user)
    private int age; //Age of pet in yrs
    
    private static final Image bodyIm = new ImageIcon("C:/Users/celes/petInteraction/Body.png").getImage();; //Image of body
    private static final Image legIm = new ImageIcon("C:/Users/celes/petInteraction/Leg.png").getImage(); //Image of leg
    private Image headIm; //Image of head
    
    /**
     * Constructor for objects of class Pet
     */
    public Pet(String type, String name)
    {
        // initialise instance variables
        this.type = type;
        this.name = name;
        this.age = 0;
        
        if (type.equals("dog")){
            this.weight = 0.2;
            headIm = new ImageIcon("C:/Users/celes/petInteraction/Dog.png").getImage(); //Set the haed image to a dog head image
        }
        else if (type.equals("cat")){
            this.weight = 0.1;
            headIm = new ImageIcon("C:/Users/celes/petInteraction/Cat.png").getImage(); //Set the haed image to a cat head image
        }
        else if(type.equals("otter")){
            this.weight = 1.0;
            headIm = new ImageIcon("C:/Users/celes/petInteraction/Otter.png").getImage(); //Set the haed image to a cat head image
        }
        //Calculate other weight-related variables from the pet's weight
        maxWeight = weight * 2;
        minWeight = weight/2;
        metabolism = weight * 0.003;
    }
    
    /**
     * Return the current weight of the pet
     * @return weight -- current weight of pet
     */
    public double getWeight(){
        return this.weight;
    }
    
    /**
     * Return the maximum weight the pet can survive at
     * @return maxWeight -- maximum weight allowed for the pet
     */
    public double getMaxWeight(){
        return this.maxWeight;
    }
    
    /**
     * Return the minimum weight the pet can survive at
     * @return minWeight -- minimum weight allowed for the pet
     */
    public double getMinWeight(){
        return this.minWeight;
    }
    
    /**
     * Return the current age of the pet
     * @return age -- age of pet
     */
    public int getAge(){
        return this.age;
    }
    
    /**
     * Return image of each leg of pet
     * @return leg -- leg image of pet
     */
    public Image getLeg(){
        return this.legIm;
    }
    
    /**
     * Return image of body of pet
     * @return body -- image of body of pet
     */
    public Image getBody(){
        return this.bodyIm;
    }
    
    /**
     * Return image of the head of the pet
     * @return head -- head image of pet
     */
    public Image getHead(){
        return this.headIm;
    }
    
    /**
     * Return type of the pet
     * @return type -- type of pet
     */
    public String getType(){
        return this.type;
    }
    
    /**
     * Return pet name
     * @return name -- pet name
     */
    public String getName(){
        return this.name;
    }
}

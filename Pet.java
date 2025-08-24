import java.util.HashMap;
import ecs100.*;
import java.awt.Color;
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
    private String image; //Image of head
    private int imageX; //X-coordinate of image
    private int imageY; //Y-coordinate of image
    private int rightAttributes; //Right side of right column attributes 
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
            image = "Dog.jpg";
            weight = 0.2;
        }
        else if (type.equals("cat")){
            image = "Cat.jpg";
            weight = 0.1;
        }
        else{
            image = "Otter.jpg";
            weight = 1.0;
        }
        
        //Calculate other weight-related variables from the pet's weight
        //MathGPT spotted that I put these lines above where this.weight was set and I moved them down (24/08/25)
        maxWeight = weight * 2;
        minWeight = weight/2;
        metabolism = weight * 0.0005;
    }
    
    /**
     * Display image of pet
     */
    public void displayIm(){
        UI.drawImage(image, 100, 100);
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
    public double getMax(){
        return this.maxWeight;
    }
    
    /**
     * Return the minimum weight the pet can survive at
     * @return minWeight -- minimum weight allowed for the pet
     */
    public double getMin(){
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
     * Return image of the head of the pet
     * @return head -- head image of pet
     */
    public String getIm(){
        return this.image;
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
    
    /**
     * Return the metabolim of the pet, in how many Kg it'll lose per hour without food (easier for the user)
     * @return metabolism - metabolism of pet
     */
    public double getMet(){
        return this.metabolism;
    }
}

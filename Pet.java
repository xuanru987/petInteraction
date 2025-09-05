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
    private double weight; //Current weight of pet in Kg
    private double equilibriumWeight; //Most suitable weight
    private double minWeight; //Minimum weight to stay alive
    private double maxWeight; //Maximum weight to stay alive
    private double metabolism; //Metabolism, in how many Kg it'll lose per hour without food (easier for the user)
    private int age; //Age of pet in yrs
    private String image; //Image of pet
    private String deadImage; //Image of pet when it dies
    private int imageX; //X-coordinate of image
    private int imageY; //Y-coordinate of image
    private int rightAttributes; //Right side of right column attributes 
    
    
    private HashMap<String, Double>animalWeights; //Newborn weights of each animal type
    /**
     * Constructor for objects of class Pet
     */
    public Pet(String type, String name, int age, double weight)
    {
        // initialise instance variables
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        
        if (type.equals("dog")){
            image = "Dog.jpg";
            deadImage = "deadDog.jpg";
            imageX = 100;
            imageY = 55;
        }
        else if (type.equals("cat")){
            image = "Cat.jpg";
            deadImage = "deadCat.jpg";
            imageX = 100;
            imageY = 55;
        }
        else{
            image = "Otter.jpg";
            deadImage = "deadOtter.jpg";
            imageX = 80;
            imageY = 80;
        }
        
        animalWeights = new HashMap<String, Double>();
        animalWeights.put("dog", 0.2);
        animalWeights.put("cat", 0.1);
        animalWeights.put("otter", 1.0);
        
        setEquilibriumWeight(); //Calculate the most suitable weight from age and newborn weight
        
        //Calculate max and min weight from the pet's most suitable weight
        maxWeight = equilibriumWeight * 2;
        minWeight = equilibriumWeight/2;
        
        //Calculate metabolism from weight - MathGPT reminded me to put this below the code that sets the initial "weight" of the object;
        metabolism = weight * 0.0005; 
    }
    
    /**
     * Calculate and set/update the most suitable weight for the pet using its age
     */
    public void setEquilibriumWeight(){
        if(age <= 5){
            this.equilibriumWeight = animalWeights.get(type) * (1 + age);
        }
        else{
            this.equilibriumWeight = animalWeights.get(type) * 6;
        }
    }
    
    /**
     * Display image of pet
     */
    public void displayIm(){
        UI.drawImage(image, imageX, imageY);
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
    
    /**
     * Change its own weight from digesting food or exercising, and update metabolism according to new weight
     * ChatGPT told me that because Math.round() returns a long (integer), I need to add .0 to the denominators to make them doubles, otherwise java rounds the result of division to an integer which would become 0
     * @param double ratio = added or subtracted weight / pet's weight
     */
    public void changeWeight(double ratio){
        this.weight = Math.round(weight * (1 + ratio)* 10000)/10000.0; //Round adjusted weight to 4dp
        metabolism = Math.round(weight * 0.0005 * 10000)/10000.0; //Round adjusted metabolism to 4dp
    }
    
    /**
     * Check if the pet is within the allowed weight range
     * If not, the pet dies
     * Return whether it is alive or not
     * @return boolean petAlive - whether the pet is alive or not
     */
    public boolean checkAlive(){
        boolean petAlive = true;
        if(weight < minWeight || weight > maxWeight){
            petAlive = false;
        }
        return petAlive;
    }
    
    /**
     * Change the image of the pet to show that it is dead
     */
    public void showDead(){
        UI.drawImage(deadImage, imageX, imageY);
        if(weight > maxWeight){
            UI.drawString("Oops! " + name + " has died from being overweight.", 130, 70);
        }
        else if(weight < minWeight){
            UI.drawString("Oops! " + name + " has died from being underweight.", 130, 70);
        }
    }
}
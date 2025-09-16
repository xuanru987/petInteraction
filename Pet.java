import java.util.HashMap;
import ecs100.*;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * 
 * A virtual pet users can interact with via a GUI (programmed in another class)
 * Has a name, weight, max and min weight to stay alive, metabolism, age, and possibly thirst/hydration
 * Can perform certain animated actions on demand, like eating & drinking
 * Sources: MathGPT
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
    private double age; //Age of pet in yrs
    private String image; //Image of pet
    private String deadImage; //Image of pet when it dies
    private String bentImage; //Image of pet bent down to eat
    private int bentWidth; //Width of bent image
    private int bentHeight; //Height of bent image
    private int imageX; //X-coordinate of image
    private int imageY; //Y-coordinate of image
    private int bentX; //X - coordinate of bent image
    private int bentY; //Y - coordinate of bent image
    private int rightAttributes; //Right side of right column attributes 
    private double eatingTime; //TIme the animal takes to eat
    
    private int foodTop; //Top of food at where the pet likes to enjoy it
    private int foodLeft; //Left of food at where the pet likes to enjoy it
    
    private int ballLeft1; //Left of ball when on pet's head
    private int ballTop1; //Top of ball when on pet's head
    private int ballLeft2; //Left of ball after bouncing off pet's head
    private int ballTop2; //Top of ball after bouncing off pet's head
    
    private Ball ball; //The ball this pet plays with
    
    private HashMap<String, Double>animalWeights; //Newborn weights of each animal type
    /**
     * Constructor for objects of class Pet
     */
    public Pet(String type, String name, double age, double weight)
    {
        // initialise instance variables
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        
        eatingTime = 10000; //MathGPT told me that I cannot write 10^3 for 10 to the power of 3 in java
        
        ball = new Ball();
        
        if (type.equals("dog")){
            //if(age >= 2){
                image = "Dog.png";
            //}
            //else{
                //image = "babyDog.png";
            //}
            deadImage = "deadDog.jpg";
            bentImage = "bentDog.png"; //ChatGPT redrew the dog I drew so that it's bent down to eat
            imageX = 100;
            imageY = 55;
            bentX = 200;
            bentY = 120;
            bentWidth = 200;
            bentHeight = 150;
            foodLeft = 212;
            foodTop = 250;
            ballLeft1 = 100;
            ballTop1 = 50;
            ballLeft2 = 50;
            ballTop2 = 100;
        }
        else if (type.equals("cat")){
            //if(age >= 2){
                image = "Cat.png";
                imageX = 100;
                imageY = 55;
            //}
            //else{
                //image = "babyCat.png";
                //imageX = 200;
                //imageY = 70;
            //}
            
            deadImage = "deadCat.jpg";
            bentImage = "bentCat.png"; //ChatGPT redrew the cat I drew so that it's bent down to eat
            bentX = 200;
            bentY = 120;
            bentWidth = 200;
            bentHeight = 150;
            foodTop = 235;
            foodLeft = 220;
            ballLeft1 = 100;
            ballTop1 = 50;
            ballLeft2 = 50;
            ballTop2 = 100;
        }
        else{
            //if(age >= 2){
                image = "Otter.png";
            //}
           // else{
                //image = "babyOtter.png";
            //}
            deadImage = "deadOtter.jpg";
            bentImage = "bentOtter.png";
            imageX = 80;
            imageY = 120;
            bentX = 100;
            bentY = 130;
            bentWidth = 340;
            bentHeight = 90;
            foodTop = 170;
            foodLeft = 415;
            ballLeft1 = 418;
            ballTop1 = 80;
            ballLeft2 = 506;
            ballTop2 = 160;
        }
        
        animalWeights = new HashMap<String, Double>();
        animalWeights.put("dog", 0.2);
        animalWeights.put("cat", 0.1);
        animalWeights.put("otter", 1.0);
        
        setWeights(); //Calculate the most suitable, max and min weight from age and newborn weight
        
        //Calculate metabolism from weight - MathGPT reminded me to put this below the code that sets the initial "weight" of the object;
        metabolism = weight * 0.0005; 
    }
    
    /**
     * PLay with ball
     */
    public void bounceBall(){
        ball.bounce(ballLeft1, ballTop1);
        displayIm();
        ball.bounce(ballLeft2, ballTop2);
        displayIm();
    }
    
    /**
     * Calculate and set/update the most suitable weight for the pet using its age
     * Then, calculate the max and min weights allowed using the most suitable weight
     */
    public void setWeights(){
        if(age <= 5){
            this.equilibriumWeight = animalWeights.get(type) * (1 + age);
        }
        else{
            this.equilibriumWeight = animalWeights.get(type) * 6;
        }
        //Calculate max and min weight from the pet's most suitable weight
        maxWeight = equilibriumWeight * 2;
        minWeight = equilibriumWeight/2;
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
    public double getAge(){
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
     * Bend down to eat, and come back up a few seconds later
     * @param String food - image of food to be eaten
     * @param double foodWidth = width of food image
     * @param double foodHeight - height of food image
     */
    public void bendToEat(String food, double foodWidth, double foodHeight){
        if(food != ""){ //If there is an actual food image parsed in
            //I initially put a line to erase the usual image, but MathGPT told me that ecs "erases" by filling the rectangle with white, which would cover another image I am displaying, so I removed the line
            UI.drawImage(bentImage, bentX, bentY, bentWidth, bentHeight);
            UI.drawImage(food, foodLeft, foodTop, foodWidth, foodHeight);
            UI.sleep(eatingTime);
            UI.eraseImage(bentImage, bentX, bentY);
            UI.eraseImage(food, foodLeft, foodTop);
        }
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
    
    /**
     * Return top of food where this pet likes to enjoy it
     */
    public int getFoodTop(){
        return this.foodTop;
    }
    
    /**
     * Return left of food where this pet likes to enjoy it
     */
    public int getFoodLeft(){
        return this.foodLeft;
    }
    
    /**
     * Let the pet age - just change the age variable for now
     * @param years - "years" (in the game) passed since last time
     */
    public void age(double years){
        this.age = age + years;
        setWeights();
    }
}
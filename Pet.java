import java.util.HashMap;
/**
 * 
 * A virtual pet users can interact with via a GUI (programmed in another class)
 * Has a name, weight, max and min weight to stay alive, metabolism, age, and possibly thirst/hydration
 * Can perform certain animated actions on demand, like eating & drinking
 * @Sydney Liu
 * @21/07/2025
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
    private double age; //Age of pet in yrs
    private HashMap<String, Double> animalWeights; //The initial weights of each animal type at birth
    /**
     * Constructor for objects of class Pet
     */
    public Pet(String type, String name)
    {
        // initialise instance variables
        this.type = type;
        this.name = name;
        animalWeights = new HashMap<String, Double>();
        animalWeights.put("cat", 0.1);
        animalWeights.put("dog", 0.2);
        animalWeights.put("otter", 1.0);
        weight = animalWeights.get(type); //Set the weight of the pet to the average newborn weight of its animal type
        //Calculate other weight-related variables from the pet's weight
        maxWeight = weight * 2;
        minWeight = weight/2;
        metabolism = weight * 0.003;
    }

    /**
     * Display the pet on the GUI
     */
    
}

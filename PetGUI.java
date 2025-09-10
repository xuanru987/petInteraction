/**
 * A GUI that interacts with the user to allow them to create a pet and take care of it
 * Displays a welcome message at first 
 * After pet selection the pet, its attributes, the player's resources and the pet's behaviour are displayed
 * Sources: ChatGPT 2/9/25, 3/9/25, 4/9/25
 * @author Sydney Liu
 * @version 26/07/2025
 */
import java.awt.Color;
import java.awt.Font;
import ecs100.*;
import java.util.HashMap;
import java.time.Instant; //Class to access current time in milliseconds since 12am 1/1/1970 GMT + 0

public class PetGUI{
    private Pet pet; //The pet in the game
    private String previewedType; //The type of pet being previewed
    private int leftAttributes; //x-coordinate of attributes on the left side
    private int rightAttributes; //x-coordinate of attributes on the right side
    private int topAttributes; //y-coordinate of attributes at the top
    private int attSpace; //Space between the top of each attribute
    
    private static final String SHOW_MORE = "Show more....jpg"; //"Show more" button for attributes
    private static final String SHOW_LESS = "Show less....jpg"; //"Show more" button for attributes
    private static final String SELECT = "Select.jpg"; //"Select" button for pets
    private static final String DOG_IM = "Dog.png"; //Image of the dog
    private static final String CAT_IM = "Cat.png"; //Image of the cat
    private static final String OTTER_IM = "Otter.png"; //Image of the otter
    
    private int showMoreWidth; //Width of "show more" button
    private int showMoreHeight; //Height of "show more" button
    private int showMoreLeft; //Left side of "show more" button
    private int showMoreTop; //Y-coordinate of top of "show more" button
    private int showLessLeft; //Left side of "show less" button
    private int selectLeft; //Left side of "select" button
    private int selectTop; //Top of "select" button
    private int selectWidth; //Width of "select" button
    private int selectHeight; //Height of "select" button
    
    private double foodTop; //Top of the images of foods
    private double foodLeft; //Left of the leftmost food image
    private double foodWidth; //Width of the food images
    private double foodHeight; //Height of the food images
    private double foodGap; //Gap between each food
    private double foodDistance; //Dstance between the left side of each food
    
    private boolean petPresent; //Whether there is already a pet
    private boolean foodsShown; //Whether the foods are shown
    private boolean exerciseShown; //Whether the exercise options are shown
    
    private Foods foods; //The collection of foods for the pet
    private Exercise exercise; //Exercise options for the pet
    
    private HashMap<String, Double>animalWeights; //Initial weights of each animal type
    
    private ReadFromFile attributesReader; //Object to read the pet and UI's attributes that were stored in a file when the user last exited the program
    private HashMap<Integer, String> previousAttributes; //Attributes of pet and UI from last time, read from file
    private long lastTimeStamp; // The time when the user last exited the game (ms)
    private long tSinceQuit; //Time since the user last quit in (h)
     
    public PetGUI(){
        UI.initialise();
        UI.addButton("Dog", this :: showDog);
        UI.addButton("Cat", this :: showCat);
        UI.addButton("Otter", this :: showOtter);
        UI.addButton("Foods", this :: showHideFoods);
        UI.addButton("Exercise", this :: showHideExercise);
        UI.addButton("Save & exit", this :: saveNExit);
        UI.setMouseListener(this :: doMouse);
        
        leftAttributes = 20;
        rightAttributes = 400;
        topAttributes = 20;
        attSpace = 40;
        
        showMoreWidth = 50;
        showMoreHeight = 20;
        showMoreLeft = leftAttributes;
        showMoreTop = topAttributes + 20;
        showLessLeft = showMoreLeft + showMoreWidth + 1;
        selectLeft = 440;
        selectTop = 230;
        selectWidth = 150;
        selectHeight = 60;
        
        petPresent = false;
        foodsShown = false;
        exerciseShown = false;
        
        foodTop = 340;
        foodLeft = 99;
        foodWidth = 50;
        foodHeight = 50;
        foodGap = 50;
        foodDistance = foodWidth + foodGap;
        
        animalWeights = new HashMap<String, Double>();
        animalWeights.put("dog", 0.2);
        animalWeights.put("cat", 0.1);
        animalWeights.put("otter", 1.0);
        
        attributesReader = new ReadFromFile();
        previousAttributes = attributesReader.readAttributes();
        existingPet();
    }
    
    /**
     * Create the object of a pet that already existed last time the user used the program
     * The pet's attributes were stored in a file
     */
    public void existingPet(){
        if(previousAttributes.get(0).equals("true")){ //If the file says that there is an existing pet
            lastTimeStamp = Long.valueOf(previousAttributes.get(5)); //Access the time when the user last quit
            tSinceQuit = (Instant.now().toEpochMilli() - lastTimeStamp)/3600000; //time passed = current time - lastTimeStamp; then convert to h
            createPet(previousAttributes.get(1), previousAttributes.get(2), Integer.valueOf(previousAttributes.get(3)), Double.valueOf(previousAttributes.get(4)));
            petPresent = true;
            pet.changeWeight(-tSinceQuit * pet.getMet()/pet.getWeight()); //Subtract the weight lost while the user was away -- chatGPT reminded me to parse in the subtracted amount as a ratio of the pet's weight and not a value
        }
    }
    
    /**
     * Display welcome message
     */
    public void welcome(){
        if(previousAttributes.get(0).equals("false")){
        UI.drawString("Welcome to Pet Interactor. Bring a pet home by clicking a button :)", 80, 50);
        }
    }
    
    /**
     *Show a dog for preview
     */
    public void showDog(){
        if(petPresent == false){
        UI.clearGraphics();
        UI.drawImage(SELECT, selectLeft, selectTop, selectWidth, selectHeight);
        UI.drawImage(DOG_IM, 100, 55);
        previewedType = "dog";
        }
    }
    
    /**
     *Show a cat for preview
     */
    public void showCat(){
        if(petPresent == false){
        UI.clearGraphics();
        UI.drawImage(SELECT, selectLeft, selectTop, selectWidth, selectHeight);
        UI.drawImage(CAT_IM, 100, 55);
        previewedType = "cat";
        }
    }
    
    /**
     * Show an otter for preview
     */
    public void showOtter(){
        if(petPresent == false){
        UI.clearGraphics();
        UI.drawImage(SELECT, selectLeft, selectTop, selectWidth, selectHeight);
        UI.drawImage(OTTER_IM, 80, 120);
        previewedType = "otter";
        }
    }
    
    /**
     * Create and show a certain pet and its attributes
     * @param String type - type of animal
     * @param String name - name of pet
     * @param int age - current age
     * @param double weight - current weight
     */
    public void createPet(String type, String name, int age, double weight){
        //MathGPT spotted that I had an "if petPresent == false condition", which would stop this method from being called after clicking "select", so I removed the condition
        //24/8/25
        UI.eraseString("Welcome to Pet Interactor. Bring a pet home by clicking a button :)", 80, 50);
        this.pet = new Pet(type, name, age, weight);
        pet.displayIm();
        showAttributes();
        foods = new Foods(pet, foodTop, foodLeft, foodWidth, foodHeight, foodGap);
        exercise= new Exercise(pet, foodTop, foodLeft, foodWidth, foodHeight, foodGap);
    }
    
    /**
     * Show the attributes of the pet
     */
    public void showAttributes(){
        UI.drawString("Name: " + pet.getName(), rightAttributes, topAttributes);
        UI.drawString("Age: " + pet.getAge(), rightAttributes, topAttributes + attSpace);
        UI.drawString("Current weight: " + pet.getWeight() + " kg", leftAttributes, topAttributes);
        UI.drawImage(SHOW_MORE, showMoreLeft, showMoreTop, showMoreWidth, showMoreHeight);
    }
    
    /**
     * Show the extra attributes that will only appear when one clicks "show more"
     * Try fix the hard coding?
     */
    public void extraAttributes(){
        UI.setColor(Color.green);
        UI.drawString("Min weight at this age (or else it dies): " + pet.getMin() * 1000 + " g", leftAttributes, topAttributes + attSpace * 2);
        UI.drawString("Max weight at this age (or else it dies): " + pet.getMax() * 1000 + " g", leftAttributes, topAttributes + attSpace * 3);
        UI.drawString("Weight lost per h without food: " + pet.getMet() * 1000 + " g", leftAttributes, topAttributes + attSpace * 4);
        UI.drawImage(SHOW_LESS, showLessLeft, showMoreTop, showMoreWidth, showMoreHeight);
        UI.setColor(Color.black);
    }
    
    /**
     * Method to respond to mouse input
     * @param - String action - type of mouse action
     * @param double x - x-coordinate of mouse action
     * @param double y - y-coordinate of mouse action
     */
    public void doMouse(String action, double x, double y){
        if (action.equals("clicked")){
            UI.println(x + ", " + y);
            if(inRect(x, y, showMoreLeft, showMoreLeft + showMoreWidth, showMoreTop, showMoreTop + showMoreHeight)){
                extraAttributes();
            }
            if(inRect(x, y, showLessLeft, showLessLeft + showMoreWidth, showMoreTop, showMoreTop + showMoreHeight)){
                UI.clearGraphics();
                this.pet.displayIm();
                showAttributes();
            }
            if(inRect(x, y, selectLeft, selectLeft + selectWidth, selectTop, selectTop + selectHeight) && petPresent == false){
                String petName;
                petPresent =  true; //Record that there is already a pet, so that the user can't choose other pets now
                UI.eraseRect(selectLeft, selectTop, selectWidth, selectHeight); //Erase the "select" button
                UI.drawString("What name would you like for your pet? Respond in the text panel on the left", 50, 60);
                petName = UI.askString("Pet name: ");
                UI.eraseString("What name would you like for your pet? Respond in the text panel on the left", 50, 60);
                createPet(previewedType, petName, 0, animalWeights.get(previewedType));
            }
            if(foodsShown == true) {//MathGPT reminded me to put == and not = (29/8/25) //This also means petPresent == true
                UI.clearGraphics();
                foods.playSound(x, y); //If the user clicked on a food image, play the appropriate sound
                foods.displayEaten(x, y);
                pet.bend();
                foods.hideEaten(x, y);
                pet.displayIm();
                foods.addWeight(x, y); //Add an appropriate weight to the pet according to the food (or no food) clicked
                showAttributes();
                foods.showFoods();
                checkDead();
            }
            else if(exerciseShown == true) { //This also means petPresent == true
                UI.eraseString("Current weight: " + pet.getWeight() + " kg", leftAttributes, topAttributes); //Erase the pet's weight
                exercise.playSound(x, y); //If the user clicked on an exercse image, play the appropriate sound
                if(exercise.imageClicked(x, y) == "ball.jpg"){
                    pet.bounceBall();
                }
                exercise.subtractWeight(x, y); //If the user clicks while the exercise options are shown, subtract an appropriate weight from the pet according to the option (or no option) clicked
                UI.drawString("Current weight: " + pet.getWeight() + " kg", leftAttributes, topAttributes); //Re-display the pet's weight
                checkDead();
            }
        }
    }
    
    /**
     * Check if the pet is dead, and if so, display screen for dead pet
     */
    public void checkDead(){
        if(pet.checkAlive() == false){
            UI.clearGraphics();
            pet.showDead();
            UI.drawString("Choose a new pet by clicking one of the buttons.", 80, 95);
            petPresent = false;
        }
    }
    
    /**
     * Return true if a point is within a rectangle (including edges), false otherwise
     * @param double x -- x-coordinate of point
     * @param double y -- y-coordinate
     * @param double left -- left edge of rectangle
     * @param double right -- right edge of rectangle
     * @param double top -- top of rectangle
     * @param double bottom -- bottom of rectangle
     * @return -- boolean inRect -- whether of not the point is in the rectangle
     */
    public boolean inRect(double x, double y, double left, double right, double top, double bottom){
        boolean inRect = false;
        if (x >= left && x <= right && y >= top && y <= bottom){
            inRect = true;
        }
        return inRect;
    }
    
    /**
     * If foods aren't already shown, display the images of the correct foods for the animal type
     * If already shown, hide the foods
     */
    public void showHideFoods(){
        if(petPresent == true && foodsShown == false){
            if(exerciseShown == true){
                exercise.hideExercise();
            }
            foods.showFoods();
            foodsShown = true;
            exerciseShown = false;
        }
        else if(petPresent == true && foodsShown == true){
            foods.hideFoods();
            foodsShown = false;
        }
    }
    
    /**
     * If not shown yet, show all exercise options applicable to the animal type 
     * If already shown, hide the exercise options
     */
    public void showHideExercise(){
        if(petPresent == true && exerciseShown == false){
            exercise.showExercise();
            exerciseShown = true;
            foodsShown = false;
        }
        else if(petPresent == true && exerciseShown == true){
            exercise.hideExercise();
            exerciseShown = false;
        }
    }  
    
    /**
     * Store the pet's information when the user exits the program
     */
    public void saveNExit(){
        WriteToFile attributesWriter;
        if(petPresent == true){
            attributesWriter = new WriteToFile(petPresent, pet.getType(), pet.getName(), pet.getAge(), pet.getWeight(), Instant.now().toEpochMilli()); //Instant.now().toEpochMilli() -- current time in ms since 12am 1/1/1970
        }
        else{
            attributesWriter = new WriteToFile(petPresent, "no type", "no name", 0, 0.0, Instant.now().toEpochMilli());
        }
        attributesWriter.writeToFile();
        UI.quit();
    }
    
    /**
     * Main method
     */
    public static void main(String [] args){
        PetGUI petGui = new PetGUI();
        petGui.welcome();
    }
}

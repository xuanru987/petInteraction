/**
 * A GUI that interacts with the user to allow them to create a pet and take care of it
 * Displays a welcome message at first 
 * After pet selection the pet, its attributes, the player's resources and the pet's behaviour are displayed
 * @author Sydney Liu
 * @version 26/07/2025
 */
import java.awt.Color;
import java.awt.Font;
import ecs100.*;
import java.util.HashMap;

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
    private static final String DOG_IM = "Dog.jpg"; //Image of the dog
    private static final String CAT_IM = "Cat.jpg"; //Image of the cat
    private static final String OTTER_IM = "Otter.jpg"; //Image of the otter
    
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
    
    private HashMap<Integer, String> dogFoods; //Foods available for dog -- index and image filename 
    private HashMap<Integer, String> catFoods; //Foods available for cat -- index and image filename 
    private HashMap<Integer, String> otterFoods; //Foods available for otter -- index and image filename 
    
    private HashMap<Integer, String> dogExercise; //Exercise options for dog -- index and image filename 
    private HashMap<Integer, String> catExercise; //Exercise options for cat -- index and image filename 
    private HashMap<Integer, String> otterExercise; //Exercise options for otter -- index and image filename 
    
    public PetGUI(){
        
        UI.initialise();
        UI.addButton("Dog", this :: showDog);
        UI.addButton("Cat", this :: showCat);
        UI.addButton("Otter", this :: showOtter);
        UI.addButton("Foods", this :: showHideFoods);
        UI.addButton("Exercise", this :: showHideExercise);
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
        
        foodTop = 310;
        foodLeft = 99;
        foodWidth = 50;
        foodHeight = 50;
        foodGap = 50;
        foodDistance = foodWidth + foodGap;
        
        dogFoods = new HashMap<Integer, String>();
        dogFoods.put(0, "dogFood.jpg");
        dogFoods.put(1, "meat.jpg");
        dogFoods.put(2, "pizza.jpg");
        dogFoods.put(3, "sushi.jpg");
        
        catFoods = new HashMap<Integer, String>();
        catFoods.put(0, "catFood.jpg");
        catFoods.put(1, "meat.jpg");
        catFoods.put(2, "cockroach.jpg");
        catFoods.put(3, "mouse.jpg");
        
        otterFoods = new HashMap<Integer, String>();
        otterFoods.put(0, "fish.jpg");
        otterFoods.put(1, "crab.jpg");
        otterFoods.put(2, "pizza.jpg");
        otterFoods.put(3, "sushi.jpg");
        
        dogExercise = new HashMap<Integer, String>();
        dogExercise.put(0, "field.jpg");
        dogExercise.put(1, "pool.jpg");
        dogExercise.put(2, "ball.jpg");
        dogExercise.put(3, "treadmill.jpg");
        
        catExercise = new HashMap<Integer, String>();
        catExercise.put(0, "field.jpg");
        catExercise.put(1, "catToy.jpg");
        catExercise.put(2, "ball.jpg");
        catExercise.put(3, "treadmill.jpg");
        
        otterExercise = new HashMap<Integer, String>();
        otterExercise.put(0, "pool.jpg");
        otterExercise.put(1, "ball.jpg");
    }
    /**
     * Display welcome message
     */
    public void welcome(){
        UI.drawString("Welcome to Pet Interactor. Bring a pet home by clicking a button :)", 80, 50);
    }
    
    /**
     *Show a dog for preview
     */
    public void showDog(){
        if(petPresent == false){
        UI.clearGraphics();
        UI.drawImage(SELECT, selectLeft, selectTop, selectWidth, selectHeight);
        UI.drawImage(DOG_IM, 100, 100);
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
        UI.drawImage(CAT_IM, 100, 100);
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
     * @param double weight - initial weight
     */
    public void createPet(String type, String name){
        //MathGPT spotted that I had an "if petPresent == false condition", which would stop this method from being called after clicking "select", so I removed the condition
        //24/8/25
        UI.eraseString("Welcome to Pet Interactor. Bring a pet home by clicking a button :)", 80, 50);
        this.pet = new Pet(type, name);
        pet.displayIm();
        showAttributes();
    }
    
    /**
     * Show the attributes of the pet
     */
    public void showAttributes(){
        UI.drawString("Name: " + pet.getName(), rightAttributes, topAttributes);
        UI.drawString("Age: " + pet.getAge(), rightAttributes, topAttributes + attSpace);
        UI.drawString("Current weight: " + pet.getWeight(), leftAttributes, topAttributes);
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
            if(x >=showMoreLeft && x <= showMoreLeft + showMoreWidth && y >= showMoreTop && y <= showMoreTop + showMoreHeight){
                extraAttributes();
            }
            if(x >=showLessLeft && x <= showLessLeft + showMoreWidth && y >= showMoreTop && y <= showMoreTop + showMoreHeight){
                UI.clearGraphics();
                this.pet.displayIm();
                showAttributes();
            }
            if(x >=selectLeft && x <= selectLeft + selectWidth && y >= selectTop && y <= selectTop + selectHeight && petPresent == false){
                String petName;
                petPresent =  true; //Record that there is already a pet, so that the user can't choose other pets now
                UI.eraseRect(selectLeft, selectTop, selectWidth, selectHeight); //Erase the "select" button
                UI.drawString("What name would you like for your pet? Respond in the text panel on the left", 90, 60);
                petName = UI.askString("Pet name: ");
                createPet(previewedType, petName);
                UI.eraseString("What name would you like for your pet? Respond in the text panel on the left", 90, 60);
            }
        }
    }
    
    /**
     * If foods aren't already shown, display the images of the correct foods for the animal type
     * If already shown, hide the foods
     */
    public void showHideFoods(){
        if(petPresent == true && foodsShown == false){
            if(previewedType == "dog"){
                for (int i : dogFoods.keySet()){
                UI.drawImage(dogFoods.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                }
            }
            else if(previewedType == "cat"){
                for (int i : catFoods.keySet()){
                UI.drawImage(catFoods.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                } 
            }
            else{
                for (int i : otterFoods.keySet()){
                    UI.drawImage(otterFoods.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                }
            }
            foodsShown = true;
        }
        else if(petPresent == true && foodsShown == true){
            for(int i = 0; i < 4; i ++){
                UI.eraseRect(foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight);
            }
            foodsShown = false;
        }
    }
    
    /**
     * If not shown yet, show all exercise options applicable to the animal type 
     * If already shown, hide the exercise options
     */
    public void showHideExercise(){
        if(petPresent == true && exerciseShown == false){
            if(previewedType == "dog"){
                for (int i : dogExercise.keySet()){
                UI.drawImage(dogExercise.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                }
            }
            else if(previewedType == "cat"){
                for (int i : catExercise.keySet()){
                UI.drawImage(catExercise.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                } 
            }
            else{
                for (int i : otterExercise.keySet()){
                    UI.drawImage(otterExercise.get(i), foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight); 
                }
            }
            exerciseShown = true;
        }
        else if(petPresent == true && exerciseShown == true){
            for(int i = 0; i < 4; i ++){
                UI.eraseRect(foodLeft + foodDistance * i, foodTop, foodWidth, foodHeight);
            }
            exerciseShown = false;
        }
    }  
    
    /**
     * Main method
     */
    public static void main(String [] args){
        PetGUI petGui = new PetGUI();
        petGui.welcome();
    }
    
}

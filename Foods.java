import java.util.HashMap;
import ecs100.*;
/**
 * Foods for each animal in the interaction game
 * Can display and hide themselves and add weight to the animal
 * @Sydney Liu 
 * @27/8/25 
 */
public class Foods
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private HashMap<Integer, String> dogFoods; //Foods available for dog -- index and image filename 
    private HashMap<Integer, String> catFoods; //Foods available for cat -- index and image filename 
    private HashMap<Integer, String> otterFoods; //Foods available for otter -- index and image filename 
    
    private HashMap<String, HashMap<Integer, String>> animalsFoods; //Each animal type paired with their respective food collection
    //MathGPT told me that I needed to put <Integer, String> for the HashMap within, otherwise the HashMap is seen as raw 29/8/25
    
    private HashMap <String, Double>foodWeights; //The weight each food (by image filename) adds to pets -- by ratio to the pet's weight
    private HashMap <String, String>foodSounds; //The sound each food (by image filename) makes when eaten -- by filename of soundfile
    
    private Pet pet; //The pet the foods are for
    
    private double top; //Top of the row of foods
    private double left; //Left of the row of foods
    private double width; //Width of each food image
    private double height; //Height of each food image
    private double gap; //Gap between each food image
    private double distance; //Distance between the left of each image
    
    
    /**
     * Initialise instance variables
     * @param pet - the pet the foods are for
     * @param top - Top of the row of foods
     * @param left - Left of the row of foods
     * @param width - Width of each food image
     * @param height - height of each food image
     * @param gap - Gap between each food image
     */
    public Foods(Pet pet, double top, double left, double width, double height, double gap)
    {
        this.pet = pet;
        
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.gap = gap;
        this.distance = width + gap;
        
        // Collections of foods and exercise options
        dogFoods = new HashMap<Integer, String>();
        dogFoods.put(0, "dogFood.jpg");
        dogFoods.put(1, "meat.jpg");
        dogFoods.put(2, "pizza.jpg");
        dogFoods.put(3, "sushi.jpg");
        
        catFoods = new HashMap<Integer, String>();
        catFoods.put(0, "dogFood.jpg");
        catFoods.put(1, "meat.jpg");
        catFoods.put(2, "cockroach.jpg");
        catFoods.put(3, "mouse.jpg");
        
        otterFoods = new HashMap<Integer, String>();
        otterFoods.put(0, "fish.jpg");
        otterFoods.put(1, "crab.jpg");
        otterFoods.put(2, "pizza.jpg");
        otterFoods.put(3, "sushi.jpg");
        
        animalsFoods = new HashMap<String, HashMap<Integer, String>>();
        animalsFoods.put("dog", dogFoods);
        animalsFoods.put("cat", catFoods);
        animalsFoods.put("otter", otterFoods);
        
        foodWeights = new HashMap<String, Double>();
        foodWeights.put("dogFood.jpg", 0.003);
        foodWeights.put("meat.jpg", 0.003);
        foodWeights.put("pizza.jpg", 0.005);
        foodWeights.put("sushi.jpg", 0.002);
        foodWeights.put("catFood.jpg", 0.003);
        foodWeights.put("cockroach.jpg", 0.001);
        foodWeights.put("mouse.jpg", 0.0025);
        foodWeights.put("fish.jpg", 0.002);
        foodWeights.put("crab.jpg", 0.004);
        
        foodSounds = new HashMap<String, String>();
        foodSounds.put("dogFood.jpg", "eatingCrispyCookies.wav");
        foodSounds.put("meat.jpg", "meatSounds.wav");
        foodSounds.put("pizza.jpg", "munchingSounds.wav");
        foodSounds.put("sushi.jpg", "munchingSounds.wav");
        foodSounds.put("catFood.jpg", "eatingCrispyCookies.wav");
        foodSounds.put("cockroach.jpg", "eatingCockroach.wav");
        foodSounds.put("mouse.jpg", "meatSounds.wav");
        foodSounds.put("fish.jpg", "fishSounds.wav");
        foodSounds.put("crab.jpg", "fishSounds.wav");
    }

    /**
     * Display all the foods on UI
     */
    public void showFoods()
    {
        for (int i : animalsFoods.get(pet.getType()).keySet()){
            UI.drawImage(animalsFoods.get(pet.getType()).get(i), left + distance * i, top, width, height); 
        }
    }
    
    /**
     * Hide all foods
     */
    public void hideFoods(){
        for(int i = 0; i < 4; i ++){
                UI.eraseRect(left + distance * i, top, width, height);
                pet.displayIm(); //eraseRect() also erases part of the pet so we need to redraw it
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
     * Check if a point is in the image of any food
     * If so, display the food the GUI, at the appropriate spot relative to the pet
     */
    public void displayEaten(double x, double y){
        if(imageClicked(x, y) != ""){
            UI.drawImage(imageClicked(x, y), pet.getFoodLeft(), pet.getFoodTop(), width, height); 
        }        
    }
    
    /**
     * Check if a point is on the image of any food
     * If so, hide the food on the GUI
     */
    public void hideEaten(double x, double y){
        if(imageClicked(x, y) != ""){
            UI.eraseRect(pet.getFoodLeft(), pet.getFoodTop(), width, height); 
        }        
    }
    
    /**
     * Check if a point is in the image of any food
     * If it is, add the appropriate weight for the food to the animal 
     */
    public void addWeight(double x, double y){
        if(imageClicked(x, y) != ""){
            pet.changeWeight(foodWeights.get(imageClicked(x, y))); //add the appropriate weight to the pet
        }
    }
    
    /**
     * Check if a point (usually point of mouse click) is in the image of any food
     * If it is, play the appropriate sound for the food
     */
    public void playSound(double x, double y){
        if(imageClicked(x, y) != ""){
            SoundPlayer soundPlayer = new SoundPlayer(foodSounds.get(imageClicked(x, y)));
            soundPlayer.playSound();
        }
    }
    
    /**
     * Check if a point (usually point of mouse click) is in the image of any food
     * If it is, return food image file name
     */
    public String imageClicked(double x, double y){
        String imageClicked = "";
        for (int i : animalsFoods.get(pet.getType()).keySet()){ //Loop through the appropriate index - food image HashMap for the pet
            //If the point is on the image being checked,
            if (inRect(x, y, left + distance * i, left + distance * i + width, top, top + height)){ //MathGPT reminded me to parse in the edges of the images rather than left, top, width and height 29/8/25 
                imageClicked = animalsFoods.get(pet.getType()).get(i); 
            }
        }
        return imageClicked;
    }
}
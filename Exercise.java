import java.util.HashMap;
import ecs100.*;
/**
 * Exercise options for the pet in the interaction game
 * Can display themselves and subtract weight from the pet
 * @Sydney Liu
 * Sources: ChatGPT
 * @27/8/25
 */
public class Exercise
{
    // 实例变量 - 用你自己的变量替换下面的例子
    private HashMap<Integer, String> dogExercise; //Exercise options for dog -- index and image filename 
    private HashMap<Integer, String> catExercise; //Exercise options for cat -- index and image filename 
    private HashMap<Integer, String> otterExercise; //Exercise options for otter -- index and image filename
    
    private HashMap<String, HashMap<Integer, String>> animalsExercise; //Each animal type paired with their respective exercise options
    
    private HashMap <String, Double> exerciseWeights; //The weight each type of exercise (by image filename) subtracts from pets -- by ratio to the pet's weight
    private HashMap <String, String>exerciseSounds; //The sound each type of exercise (by image filename) produces  -- by filename of soundfile
    
    private Pet pet; //The pet the foods are for

    private String type; //type of animal
    private double top; //Top of the row of foods
    private double left; //Left of the row of foods
    private double width; //Width of each food image
    private double height; //Height of each food image
    private double gap; //Gap between each food image
    private double distance; //Distance between the left of each image
    private double desGap; //Gap between images and their description
    private final double textHeight = 10; //Height of text on UI
    /**
     * Constructor of objects of class Exercise
     * 
     * @param pet - the pet the foods are for
     * @param top - Top of the row of foods
     * @param left - Left of the row of foods
     * @param width - Width of each food image
     * @param height - height of each food image
     * @param gap - Gap between each food image
     */
    public Exercise(Pet pet, double top, double left, double width, double height, double gap)
    {
        this.pet = pet;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.gap = gap;
        this.distance = width + gap;
        desGap = 4;
        
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
        otterExercise.put(2, "ottersPlaying.jpg");
        otterExercise.put(3, "juggling.jpg");
        
        animalsExercise = new HashMap<String, HashMap<Integer, String>>();
        animalsExercise.put("dog", dogExercise);
        animalsExercise.put("cat", catExercise);
        animalsExercise.put("otter", otterExercise);
        
        exerciseWeights = new HashMap<String, Double>();
        exerciseWeights.put("field.jpg", 0.005);
        exerciseWeights.put("pool.jpg", 0.003);
        exerciseWeights.put("ball.jpg", 0.005);
        exerciseWeights.put("treadmill.jpg", 0.003);
        exerciseWeights.put("catToy.jpg", 0.002);
        exerciseWeights.put("ottersPlaying.jpg", 0.005);
        exerciseWeights.put("juggling.jpg", 0.002);
        
        exerciseSounds = new HashMap<String, String>();
        exerciseSounds.put("field.jpg", "runningSounds.wav");
        exerciseSounds.put("pool.jpg", "lakeSwimming.wav");
        exerciseSounds.put("ball.jpg", "ballSounds.wav");
        exerciseSounds.put("treadmill.jpg", "runningSounds.wav");
        exerciseSounds.put("catToy.jpg", "catToy.wav");
        exerciseSounds.put("ottersPlaying.jpg", "lakeSwimming.wav");
        exerciseSounds.put("juggling.jpg", "pebbles.wav");
    }

    /**
     * Display all the exercise options on UI
     * MathGPT reminded me to use pet.getType() to access the type of pet because I had a String type variable before that I removed but I forgot to change "type" to pet.getType()
     */
    public void showExercise()
    {
        for (int i : animalsExercise.get(pet.getType()).keySet()){
            UI.drawImage(animalsExercise.get(pet.getType()).get(i), left + distance * i, top, width, height);
            if(animalsExercise.get(pet.getType()).get(i).equals("catToy.jpg")){
                UI.drawString(" cat toy", left + distance * i, top - desGap);
            }
            else if(animalsExercise.get(pet.getType()).get(i).equals("juggling.jpg")){
                UI.drawString("juggling", left + distance * i, top - desGap * 2 - textHeight);
                UI.drawString("pebbles", left + distance * i, top - desGap);
            }
            else if(animalsExercise.get(pet.getType()).get(i).equals("ottersPlaying.jpg")){
                UI.drawString("playing with", left + distance * i, top - desGap * 2 - textHeight);
                UI.drawString("other otters", left + distance * i, top - desGap);
            }
        }
    }
    
    /**
     * Hide all exercise options
     */
    public void hideExercise(){
        for(int i : animalsExercise.get(pet.getType()).keySet()){
            UI.eraseRect(left + distance * i, top, width, height);
            if(animalsExercise.get(pet.getType()).get(i).equals("catToy.jpg")){
                UI.eraseString(" cat toy", left + distance * i, top - desGap);
            }
            else if(animalsExercise.get(pet.getType()).get(i).equals("juggling.jpg")){
                UI.eraseString("juggling", left + distance * i, top - desGap * 2 - textHeight);
                UI.eraseString("pebbles", left + distance * i, top - desGap);
            }
            else if(animalsExercise.get(pet.getType()).get(i).equals("ottersPlaying.jpg")){
                UI.eraseString("playing with", left + distance * i, top - desGap * 2 - textHeight);
                UI.eraseString("other otters", left + distance * i, top - desGap);
            }
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
     * Check if a point is in the image of any exercise option
     * If it is, subtract the appropriate weight from the animal 
     */
    public void subtractWeight(double x, double y){
        for (int i : animalsExercise.get(pet.getType()).keySet()){ //Loop through the appropriate index - food image HashMap for the pet
            //If the point is on the image being checked,
            if (inRect(x, y, left + distance * i, left + distance * i + width, top, top + height)){ //MathGPT reminded me to parse in the edges of the images rather than left, top, width and height 29/8/25 
                 pet.changeWeight(-exerciseWeights.get(animalsExercise.get(pet.getType()).get(i))); //subtract the appropriate weight from the pet
            }
        }
    }
    
    /**
     * Check if a point is on the image of any exercise option
     * If it is, play the corresponding sound
     */
    public void playSound(double x, double y){
        for (int i : animalsExercise.get(pet.getType()).keySet()){ //Loop through the appropriate index - food image HashMap for the pet
            //If the point is on the image being checked,
            if (inRect(x, y, left + distance * i, left + distance * i + width, top, top + height)){ //MathGPT reminded me to parse in the edges of the images rather than left, top, width and height 29/8/25 
                 SoundPlayer soundPlayer = new SoundPlayer(exerciseSounds.get(animalsExercise.get(pet.getType()).get(i)));
                 soundPlayer.playSound();
            }
        }
    }
}
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

public class PetGUI{
    private Pet pet;
    private int leftAttributes; //x-coordinate of attributes on the left side
    private int rightAttributes; //x-coordinate of attributes on the right side
    private int topAttributes; //y-coordinate of attributes at the top
    private int attSpace; //Space between the top of each attribute
    private static final String SHOW_MORE = "Show more....jpg"; //"Show more" button for attributes
    private static final String SHOW_LESS = "Show less....jpg"; //"Show more" button for attributes
    private int showMoreWidth; //Width of "show more" button
    private int showMoreHeight; //Height of "show more" button
    private int showMoreLeft; //Left side of "show more" button
    private int showMoreTop; //Y-coordinate of top of "show more" button
    private int showLessLeft; //Left side of "show less" button
    private boolean petPresent; //Whether there is already a pet
    
    public PetGUI(){
        UI.addButton("Dog", this :: showDog);
        UI.addButton("Cat", this :: showCat);
        UI.addButton("Otter", this :: showOtter);
        UI.setMouseListener(this :: doMouse);
        leftAttributes = 20;
        rightAttributes = 400;
        topAttributes = 20;
        attSpace = 40;
        showMoreWidth = 50;
        showMoreHeight = 20;
        showMoreLeft = rightAttributes;
        showMoreTop = topAttributes + 20;
        showLessLeft = showMoreLeft + showMoreWidth + 1;
        petPresent = false;
    }
    /**
     * Display welcome message
     */
    public void welcome(){
        UI.println("Welcome to Pet Interactor. Bring a pet home by clicking a button :)");
    }
    
    /**
     * Create and show a dog
     */
    public void showDog(){
        if(petPresent == false){
        this.pet = new Pet("dog", "Pedro", 0.2);
        petPresent = true;
        pet.displayIm();
        showAttributes();
        }
    }
    
    /**
     * Create and show a cat
     */
    public void showCat(){
        if(petPresent == false){
        this.pet = new Pet("cat", "Kitty", 0.1);
        petPresent = true;
        pet.displayIm();
        showAttributes();
        }
    }
    
    /**
     * Create and show an otter
     */
    public void showOtter(){
        if(petPresent == false){
        this.pet = new Pet("otter", "晉佑", 1.0);
        petPresent = true;
        pet.displayIm();
        showAttributes();
        }
    }
    
    /**
     * Show the attributes of the pet
     */
    public void showAttributes(){
        UI.drawString("Name: " + pet.getName(), leftAttributes, topAttributes);
        UI.drawString("Age: " + pet.getAge(), leftAttributes, topAttributes + attSpace);
        UI.drawString("Weight: " + pet.getWeight(), rightAttributes, topAttributes);
        UI.drawImage(SHOW_MORE, showMoreLeft, showMoreTop, showMoreWidth, showMoreHeight);
    }
    
    /**
     * Show the extra attributes that will only appear when one clicks "show more"
     * Try fix the hard coding?
     */
    public void extraAttributes(){
        UI.drawString("Min weight: " + pet.getMin(), rightAttributes, topAttributes + attSpace * 2);
        UI.drawString("Max weight: " + pet.getMax(), rightAttributes, topAttributes + attSpace * 3);
        UI.drawString("Kgs it'll lose per h ", rightAttributes, topAttributes + attSpace * 4);
        UI.drawString("without food", rightAttributes, topAttributes + attSpace * 5);
        UI.drawString("" + pet.getMet(), rightAttributes, topAttributes + attSpace * 6);
        UI.drawImage(SHOW_LESS, showLessLeft, showMoreTop, showMoreWidth, showMoreHeight);
    }
    /**
     * Method to responed to mouse input
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

import java.io.FileWriter; //Import the filewriter class
import java.io.IOException; //Import a class to handle exceptions
/**
 * A class that helps write to the attributes file created with the CreateFile class
 * Store the latest values of the pet's attributes each time the user exits the game
 * Sources: https://www.w3schools.com/java/java_files_create.asp and MathGPT (1/9/25) & ChatGPT (2/9/25)
 * 
 * @author Sydney Liu
 * @1/9/25
 */
public class WriteToFile
{
    private int age; //Current age of pet to write to the flie
    private double weight; //Current weight of pet to write to the file
    private String type; //Type of pet
    private String name; //Name of pet to write to file
    private boolean petPresent; //Whether there is a pet
    private long time; //Current time (h) ; long - for handling large numbers
    /**
     * Constructor of objects of class WriteToFile
     * @param int age - current age of pet to write to the file
     * @param double weight - current weight of pet to write to file
     */
    public WriteToFile(boolean petPresent, String type, String name, int age, double weight, long time){ 
        this.petPresent = petPresent;
        this.type = type;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.time = time;
    }
    
    /**
     * Write the current age and weight of pet to the attributes file
     */
    public void writeToFile(){
        //Create FileWriter object and specify the file to write to
        try{
            FileWriter writer1 = new FileWriter("C:\\Users\\sydne\\petInteraction\\attributesFile.txt");
            writer1.write(Boolean.toString(petPresent));
            writer1.write("\n"); //Switch to the next line
            writer1.write(type);
            writer1.write("\n"); //Switch to the next line
            writer1.write(name); 
            writer1.write("\n"); //Switch to the next line
            writer1.write(Integer.toString(age)); //Convert int age to a string before writing to the file 
            writer1.write("\n"); //Switch to the next line
            writer1.write(Double.toString(weight)); //Convert double weight to a string before writing to the file 
            writer1.write("\n");
            writer1.write(Long.toString(time));
            writer1.close(); //ChatGPT told me that I needed to close the writer after writing, otherwise the strings will stay in memory and get discarded after the program closes
        }
        //If an error occurs
        catch (IOException e){ //e is an object containing information about the error
            e.printStackTrace(); //Print the type of error, its message and the method calls that led to the error
        }
    }
}
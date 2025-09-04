import java.io.File;
import java.io.FileNotFoundException; //Helps handle errors
import java.util.Scanner; //This helps us read from the file
import java.util.HashMap; 
/**
 * Read from the attributes file to retrieve the attributes of the pet when the user last closed the program
 * Sources: https://www.w3schools.com/java/java_files_read.asp
 * @author Sydney Liu
 * @version 2/9/25
 */
public class ReadFromFile
{
    /**
     * Constructor of objects of class ReadFromFile
     */
    public ReadFromFile(){
        
    }
    
    /**
     * Create a HashMap
     * Read each line of the file (each pet or GUI attribute stored) and put it in the HashMap
     * If error, print explanation
     * @return petAttributes - HashMap containing the pet and UI attributes, each with an index
     */
    public HashMap readAttributes(){
        HashMap<Integer, String> petAttributes = new HashMap<Integer, String>();
        int attIndex = 0; //Index of attributes in HashMap
        try{
            File file1 = new File("C:\\Users\\sydne\\petInteraction\\attributesFile.txt"); //Create a file object representing the attributes file
            Scanner attScanner = new Scanner(file1); //Create a scanner object that scans from the attributes file
            while (attScanner.hasNextLine()){ //While there are still unread lines in the file
                //Reads next unread line and put into HashMap
                petAttributes.put(attIndex, attScanner.nextLine());  
                attIndex +=1;
            }
            attScanner.close();
        }
        //If there is an error
        catch(FileNotFoundException e) { //e is an object containing information about the error
            e.printStackTrace(); //Print the type of error, its message and the method calls that led to the error
        }
        return petAttributes;
    }
}
import java.io.File; //Import the File class
import java.io.IOException; //Import a class to handle exceptions
/**
 * Create a file to write to each time the user exits the game and read from each time they open the game
 * Sources: https://www.w3schools.com/java/java_files.asp and MathGPT (1/9/25)
 * @Sydney Liu
 * @1/9/25
 */
public class CreateFile
{

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String [] args)
    {
        //If the file can be created successfully
        try{
            //Create new file, specifying file name and file path
            File file1 = new File("C:\\Users\\liusy\\petInteraction\\attributesFile.txt");
            file1.createNewFile();
        }
        //If the program fails to create the file
        catch(IOException e){ //e is an object containing information about the error
            e.printStackTrace(); //Print the type of error, its message and the method calls that led to the error
        }
    }
}

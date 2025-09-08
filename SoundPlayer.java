import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
/**
 * Helps play soundfiles for when the pet in a pet interaction game is eating or exercising
 * Can play the appropriate sound file based on the food / exercise parsed into the constructor
 * Sources: ChatGPT
 * @author Sydney Liu
 * @version 6/9/25
 */
public class SoundPlayer
{
    // Instance variables
    File soundFile; //Object that stands for the sound file to be played
    Clip myClip; //The "sound player"
    boolean canPlay; //If the clip is successfully created and opened

    /**
     * Constructor of objects of class SoundPlayer
     * AudioSystem - a class that helps us access our computer's audio system
     * @param fileName - File name of sound file to be played
     */
    public SoundPlayer(String fileName)
    {
        try{
            soundFile = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile); //An input stream is something that helps the program read a bit of data at a time; parsing in the file object tells the program to read from the file it represents
            myClip = AudioSystem.getClip();  
            myClip.open(inputStream); //Tell the clip what sound to play by parsing in the audio input stream
            canPlay = true;
        }
        //If not .wav file, file absent or unreadable, or hardware system refuses to connect with the program, respectively
        catch(UnsupportedAudioFileException | LineUnavailableException | IOException e){
            e.printStackTrace(); //Is is an object storing info about error; ask e to print out the info
            canPlay = false;//ChatGPT reminded me that myClip is still left as null in some exceptions, so I need to prevent myClip.start() from being run
        }
    }

    /**
     * Play the sound loaded into the clip
     */
    public void playSound(){
        if(canPlay == true){
        myClip.start(); //Play the sound
        }
    }
    
    /**
     * Close the clip / "sound player" so that the systemm isn't overloaded when I play another sound later
     */
    public void closeClip(){
        myClip.close(); //Prevent the system from being overloaded when I use another clip later
    }
}

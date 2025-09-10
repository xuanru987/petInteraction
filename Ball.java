import ecs100.*;
/**
 * A ball that can "move" on the GUI
 * 
 * @author Sydney Liu
 * @version 9/9/25
 */
public class Ball
{
    private final String BALL_IMAGE = "ball.jpg";
    private final int width = 50; //Width of image
    private final int height = 50; //Image height
    
    public Ball(){
    }
    
    /**
     * Bounce off a surface
     */
    public void bounce(int left, int top){
        UI.drawImage(BALL_IMAGE, left, top, width, height);
        UI.sleep(1000);
        UI.eraseRect(left, top, width, height);
    }
}
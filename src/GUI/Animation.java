package GUI;

import java.awt.*;

/**
 * Created by Future on 7/10/2016.
 */
public class Animation {
    private int       count;
    private int       index;
    private int       speed;
    private int       numFrames;
    private Texture   currentFrame;
    private Texture[] frames;

    public Animation(int speed, Texture... frames) {
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
    }

    private void nextFrame() {
        currentFrame = frames[index++];
        if (index >= numFrames)
            index = 0;
    }

    public void run() {
        // Animation is faster the lower speed is
        count++;
        if(count > speed){
            count = 0;
            nextFrame();
        }

        // Animation is faster the greater speed is
//        count += speed;
//        if (count > 100) { //where 100 is some constant, you choose!
//            count = 0;
//            nextFrame();
//        }
    }

    public void render(Graphics g, int x, int y){
        if(currentFrame != null)
            currentFrame.render(g, x, y);
    }
}

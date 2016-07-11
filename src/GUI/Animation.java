package GUI;

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

}

package GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Future on 7/12/2016.
 */
public class Player {
    private Texture texture;
    private Rectangle boundRectangle;
    public Point center;
    private Map<String , Animation> animationMap;
    private Animation animation;
    private int x, y;
    private final double CONST_VELOCITY = 6;
    private double velX, velY;
    private boolean isMoving;
    private String direction;


    public Player(String imageName, int x, int y) {
        this.texture = new Texture(imageName);
        this.x = x;
        this.y = y;
        this.animationMap = new HashMap<>();
        this.boundRectangle = new Rectangle(this.x, this.y, this.getTexture().getWidth(), this.getTexture().getHeight());
        this.center = new Point((int) (boundRectangle.getX() + (boundRectangle.getWidth() / 2)), (int) (boundRectangle.getY() + (boundRectangle.getHeight() / 2)));
    }

}

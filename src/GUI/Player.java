package GUI;

import Input.KeyInput;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Future on 7/7/2016.
 */
public class Player implements Serializable{
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

//    public PlayerPackage(String imageName, int x, int y, Texture... textures) {
//        this.texture = new Texture(imageName);
//        this.x = x;
//        this.y = y;
//        this.animationMap = new HashMap<>();
//        this.boundRectangle = new Rectangle(this.x, this.y, this.getTexture().getWidth(), this.getTexture().getHeight());
//        this.animation = new Animation(5, textures);
//    }

    public Player(String imageName, int x, int y, Texture[] upTextures, Texture[] rightTextures, Texture[] leftTextures, Texture[] downTextures) {
        this.texture = new Texture(imageName);
        this.x = x;
        this.y = y;
        this.animationMap = new HashMap<>();
        this.animationMap.put("Up", new Animation(5, upTextures));
        this.animationMap.put("Right", new Animation(5, rightTextures));
        this.animationMap.put("Down", new Animation(5, downTextures));
        this.animationMap.put("Left", new Animation(5, leftTextures));
        this.boundRectangle = new Rectangle(this.x, this.y, this.getTexture().getWidth(), this.getTexture().getHeight());
    }



    public void tick(){

        if (KeyInput.wasPressed(KeyEvent.VK_RIGHT)) {
            right();
        } else if (KeyInput.wasPressed(KeyEvent.VK_LEFT)) {
            left();
        } else if (KeyInput.wasPressed(KeyEvent.VK_UP)) {
            up();
        } else if (KeyInput.wasPressed(KeyEvent.VK_DOWN)) {
            down();
        } else if (KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
            return;
        }
        if (velX != 0 || velY != 0) {
            this.isMoving = true;
        } else{
            this.isMoving = false;
        }
//        else if (KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
//            enter();
//        }
        if(this.direction != null) {
            this.animationMap.get(this.direction).run();
        }
    }


    public void up() {
        velX = 0;
        velY = -1 * CONST_VELOCITY;
        this.direction = "Up";
    }

    public void down() {
        velX = 0;
        velY = CONST_VELOCITY;
        this.direction = "Down";
    }

    public void right() {
        velX = CONST_VELOCITY;
        velY = 0;
        this.direction = "Right";
    }

    public void left() {
        velX = -1 * CONST_VELOCITY;
        velY = 0;
        this.direction = "Left";
    }

    public void update(){
        this.boundRectangle.setBounds(this.x, this.y, this.getTexture().getWidth(), this.getTexture().getHeight());
        this.center = new Point((int) (boundRectangle.getX() + (boundRectangle.getWidth() / 2)), (int) (boundRectangle.getY() + (boundRectangle.getHeight() / 2)));
    }

    public void render(Graphics graphics, int x, int y) {
        if (!isMoving){
            this.texture.render(graphics, x, y);
        }
        else if(this.direction != null){
            this.animationMap.get(this.direction).render(graphics, x, y);
        }
    }
    //------------------------------------------------------------- Getter And Setters
    public Texture getTexture() {
        return texture;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.update();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.update();
    }

    public Rectangle getBoundRectangle() {
        return boundRectangle;
    }

    public Point getCenter() {
        return center;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}

package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Future on 7/10/2016.
 */
public class Texture {
    private transient BufferedImage image;
    private int width;
    private int height;

    public Texture() {
        this.width = 75;
        this.height = 75;
    }

    public Texture(String imageName) {
        try {
            this.image = ImageIO.read(new File("./resources/images/" + imageName + ".png"));
        } catch (IOException e) {
            return;
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(this.image, x ,  y, this.width, this.height, null);
    }
    public void render(Graphics2D g, int destX1, int destX2, int srcX1, int srcX2, int y) {
        g.drawImage(image, destX1, y, destX2, y + height, srcX1, 0, srcX2, height, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

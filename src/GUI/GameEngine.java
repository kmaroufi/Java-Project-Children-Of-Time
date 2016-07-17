package GUI;

import Input.KeyInput;
import Input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


/**
 * Created by Future on 7/6/2016.
 */
public class GameEngine extends JPanel {
    private TileMap tileMap;
    private final double CONST_VELOCITY = 6;
    private final int FPS = 32;
    private Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            if (tileMap == null) {
                return;
            }
            while(true) {
                if (!hasCollisionWithAllBarriers(tileMap.getPlayer())) {
                    tileMap.getPlayer().setX((int) (tileMap.getPlayer().getX() + tileMap.getPlayer().getVelX()));
                    tileMap.getPlayer().setY((int) (tileMap.getPlayer().getY() + tileMap.getPlayer().getVelY()));
                }
                while (hasCollisionWithAllBarriers(tileMap.getPlayer())) {
                    tileMap.getPlayer().setX((int) (tileMap.getPlayer().getX() - tileMap.getPlayer().getVelX()));
                    tileMap.getPlayer().setY((int) (tileMap.getPlayer().getY() - tileMap.getPlayer().getVelY()));
                }
                if (KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
                    enter();
                }
                repaint();
                requestFocus();
                stopplayer();
                tileMap.getPlayer().tick();
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public void setTileMapPlayer(){
        Texture[] downTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            downTextures[i] = new Texture("Player(Down-" + (i + 1) + ")");
        }
        Texture[] rightTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            rightTextures[i] = new Texture("Player(Right-" + (i + 1) + ")");
        }
        Texture[] leftTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            leftTextures[i] = new Texture("Player(Left-" + (i + 1) + ")");
        }
        Texture[] upTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            upTextures[i] = new Texture("Player(Up-" + (i + 1) + ")");
        }
        Player player = new Player("Player(Down-1)", 300, 300, upTextures, rightTextures, leftTextures, downTextures);
        this.tileMap.setPlayer(player);
    }

    //-------------------------------------------------------------------------------
    public GameEngine() {
        this.setSize(600,600);
        requestFocus();
        addKeyListener(new KeyInput());
        addMouseListener(new MouseInput());
        requestFocus();
        buildMap();
        gameThread.start();
    }
    //-------------------------------------------------------------------------------
    public void buildMap() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./resources/maps/Mohammad.dat"));
            this.tileMap = (TileMap) inputStream.readObject();
            this.setTileMapPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void enter() {
        Cell playerCell = findPlayerCell(this.tileMap.getPlayer());
        System.out.println(playerCell.getMode());
    }

    public Cell findPlayerCell(Player player) {
        return this.tileMap.findPlayerCell(player);
    }


    public void paintPlaid(Graphics graphics){
        if (tileMap == null) {
            return;
        }
        this.tileMap.paintPlaid(graphics);
    }


    public boolean hasCollisionWithAllBarriers(Player player) {
        if (this.tileMap == null) {
            return false;
        }
        return this.tileMap.hasCollisionwithAllBarriers(player);
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (tileMap == null) {
            return;
        }
//        this.paintPlaid(graphics);
        paintMapFeatures(graphics);
        paintPlayer(graphics, tileMap.getPlayer().getX(), tileMap.getPlayer().getY());
    }

    public void stopplayer() {
        this.tileMap.getPlayer().setVelX(0);
        this.tileMap.getPlayer().setVelY(0);
    }

    public void paintMapFeatures(Graphics graphics) {
        if (this.tileMap == null) {
            return;
        }
        this.tileMap.paintMapFeatures(graphics);
    }

    public void paintPlayer(Graphics graphics, int x, int y) {
        this.tileMap.getPlayer().render(graphics, x, y);
    }

}


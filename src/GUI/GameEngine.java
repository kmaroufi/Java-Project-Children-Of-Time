package GUI;

import Input.KeyInput;
import Input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


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

    //-------------------------------------------------------------------------------
    public GameEngine() {
        this.setSize(600,600);
        requestFocus();
        addKeyListener(new KeyInput());
        addMouseListener(new MouseInput());
        requestFocus();
        this.tileMap = new TileMap();
        gameThread.start();
    }
    //-------------------------------------------------------------------------------

    private void enter() {
        Cell playerCell = findPlayerCell(this.tileMap.getPlayer());
        System.out.println(playerCell.getMode());
    }

    public Cell findPlayerCell(Player player) {
        return this.tileMap.findPlayerCell(player);
    }


    public void paintPlaid(Graphics graphics){
        this.tileMap.paintPlaid(graphics);
    }


    public boolean hasCollisionWithAllBarriers(Player player) {
        return this.tileMap.hasCollisionwithAllBarriers(player);
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.paintPlaid(graphics);
        paintMapFeatures(graphics);
        paintPlayer(graphics, tileMap.getPlayer().getX(), tileMap.getPlayer().getY());
    }

    public void stopplayer() {
        this.tileMap.getPlayer().setVelX(0);
        this.tileMap.getPlayer().setVelY(0);
    }

    public void paintMapFeatures(Graphics graphics) {
        this.tileMap.paintMapFeatures(graphics);
    }

    public void paintPlayer(Graphics graphics, int x, int y) {
        this.tileMap.getPlayer().render(graphics, x, y);
    }

}


package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.io.*;
import java.util.HashMap;

/**
 * Created by Future on 7/7/2016.
 */
public class TileMap implements Serializable{
    public static HashMap<String, TileMap> gameMaps = new HashMap<>();
    private Cell[][] cells;
    private int SIZE_OF_BARRIER = 50;
    private int SIZE_OF_TABLE = 600;
    private int NUMBER_OF_BARRIERS_PER_ROW = SIZE_OF_TABLE/SIZE_OF_BARRIER;
    private int NUMBER_OF_BARRIERS_PER_COLUMN = SIZE_OF_TABLE/SIZE_OF_BARRIER;
    private Player player;

    public TileMap(String mode, int mapSize) {
        this.SIZE_OF_TABLE = mapSize;
        NUMBER_OF_BARRIERS_PER_ROW = SIZE_OF_TABLE/SIZE_OF_BARRIER;
        NUMBER_OF_BARRIERS_PER_COLUMN = SIZE_OF_TABLE/SIZE_OF_BARRIER;
        this.cells = new Cell[NUMBER_OF_BARRIERS_PER_ROW][NUMBER_OF_BARRIERS_PER_COLUMN];
        this.buildEmptyMap();
    }

    public TileMap(){
        Texture[] downTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            downTextures[i] = new Texture("PlayerPackage(Down-" + (i + 1) + ")");
        }
        Texture[] rightTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            rightTextures[i] = new Texture("PlayerPackage(Right-" + (i + 1) + ")");
        }
        Texture[] leftTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            leftTextures[i] = new Texture("PlayerPackage(Left-" + (i + 1) + ")");
        }
        Texture[] upTextures = new Texture[3];
        for(int i = 0;i < 3;i++) {
            upTextures[i] = new Texture("PlayerPackage(Up-" + (i + 1) + ")");
        }
        player = new Player("PlayerPackage(Down-1)", 300, 300, upTextures, rightTextures, leftTextures, downTextures);
//        System.out.println("Added Key Input");
        this.cells = new Cell[NUMBER_OF_BARRIERS_PER_ROW][NUMBER_OF_BARRIERS_PER_COLUMN];
        buildMap();
    }

    public TileMap(int SIZE_OF_TABLE) {
        this.SIZE_OF_TABLE = SIZE_OF_TABLE;
        player = new Player("player", SIZE_OF_TABLE / 2 , SIZE_OF_TABLE / 2);
//        System.out.println("Added Key Input");
        this.cells = new Cell[NUMBER_OF_BARRIERS_PER_ROW][NUMBER_OF_BARRIERS_PER_COLUMN];
        buildMap();
    }
    //----------------------------------------------------------------------- Functions
    public void readMapsFromFolder(){
        File folder = new File("./resources/maps/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(file.getPath()));
                    int index = file.getName().indexOf('.');
                    String fileName = file.getName().substring(0, index);
                    TileMap tileMap = (TileMap) outputStream.readObject();
                    this.gameMaps.put(fileName, tileMap);
                } catch (IOException e) {
                } catch (ClassNotFoundException e) {
                }

            }
        }
    }

    public void buildEmptyMap(){
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                this.cells[i][j] = new Cell("EmptyTile", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
            }
        }
    }

    public void buildMap() {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                if(i == 0 || i == NUMBER_OF_BARRIERS_PER_ROW - 1 || j == 0 || j == NUMBER_OF_BARRIERS_PER_COLUMN - 1) {
//                    System.out.println("y = " + i * SIZE_OF_BARRIER + " And x = " + j * SIZE_OF_BARRIER);
                    this.cells[i][j] = new Cell("Barrier", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                } else{
//                    System.out.println("y = " + i * SIZE_OF_BARRIER + " And x = " + j * SIZE_OF_BARRIER);
                    int random = (int) Math.floor(7 * Math.random());
                    switch (random) {
                        case 0:
                            this.cells[i][j] = new Cell("EmptyTile", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 1:
                            this.cells[i][j] = new Cell("Barrier", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 2:
                            this.cells[i][j] = new Cell("Shop", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 3:
                            this.cells[i][j] = new Cell("WarRoom", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 4:
                            this.cells[i][j] = new Cell("SkillRoom", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 5:
                            this.cells[i][j] = new Cell("Door", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                        case 6:
                            this.cells[i][j] = new Cell("StoryBook", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                        default:
                            this.cells[i][j] = new Cell("Nothing", i * SIZE_OF_BARRIER, j * SIZE_OF_BARRIER);
                            break;
                    }
                }
            }
        }
    }

    public void buildNewMap(){
        JFrame editor = new JFrame();

    }

    public void paintMapFeatures(Graphics graphics) {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                this.cells[i][j].render(graphics);
            }
        }
    }

    public boolean hasCollisionwithAllBarriers(Player player) {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                if (this.cells[i][j].getMode().equals("Barrier") && hasCollision(player, cells[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCollision(Player player, Cell barrier) {
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY() / 2, player.getTexture().getWidth(),player.getTexture().getHeight() / 2);
        Area area1 = new Area(playerBounds);
        Rectangle barrierBounds = new Rectangle(barrier.getX(), barrier.getY(), barrier.getWidth(), barrier.getHeight());
        Area area2 = new Area(barrierBounds);
        area1.intersect(area2);
        if (area1.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void paintPlaid(Graphics graphics) {
        for(int i = 0;i < SIZE_OF_TABLE;i += SIZE_OF_BARRIER) {
            for(int j = 0;j < SIZE_OF_TABLE;j += SIZE_OF_BARRIER) {
                graphics.drawLine(i, j, i, j + SIZE_OF_TABLE);
                graphics.drawLine(i, j, i + SIZE_OF_TABLE, j);
            }
        }
    }

    public Cell findPlayerCell(Player player) {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                Rectangle cellRectangle = new Rectangle(cells[i][j].getX(), cells[i][j].getY(), cells[i][j].getWidth(), cells[i][j].getHeight());
                if (cellRectangle.contains(player.getCenter().getX(), player.getY())) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    public void render(Graphics graphics) {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                this.cells[i][j].render(graphics);
            }
        }
    }

    public Cell findCellWithThisCoordinate (double x, double y) {
        for(int i = 0;i < NUMBER_OF_BARRIERS_PER_ROW;i++) {
            for(int j = 0;j < NUMBER_OF_BARRIERS_PER_COLUMN;j++) {
                if (cells[i][j].containsPoint(x, y)) {
                    return cells[i][j];
                }
            }
        }
        return null;
    }

    //--------------------------------------------------------------------- Add Features to map

    public void addBarrier(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("Barrier");
    }
    public void addShop(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("Shop");
    }
    public void addWarRoom(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("WarRoom");
    }
    public void addSkillRoom(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("SkillRoom");
    }
    public void addDoor(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("Door");
    }
    public void addFinalWar(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("FinalWar");
    }
    public void addEmptyTile(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("EmptyTile");
    }
    public void addBook(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("StoryBook");
    }

    public void addKey(double x, double y) {
        Cell cell = findCellWithThisCoordinate(x, y);
        if (cell == null) {
            return;
        }
        cell.setMode("Key");
    }


    //---------------------------------------------------------------------- Getter And Setters
    public Player getPlayer() {
        return player;
    }


}


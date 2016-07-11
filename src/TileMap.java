import java.io.Serializable;

/**
 * Created by Future on 7/12/2016.
 */
public class TileMap implements Serializable{
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
        player = new Player("Player(Down-1)", 300, 300, upTextures, rightTextures, leftTextures, downTextures);
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

}

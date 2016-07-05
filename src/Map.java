import java.util.ArrayList;

/**
 * Created by Future on 7/5/2016.
 */
public class Map {
    private Tile[][] tiles;
    private int width , height;
    private ArrayList<Player> players;
    //--------------------------------------------------------------------- Constructor

    public Map(Tile[][] tiles, int width, int height, ArrayList<Player> players) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.players = players;
    }

    //--------------------------------------------------------------------- Fucntion
    //--------------------------------------------------------------------- Getter && Setter

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}

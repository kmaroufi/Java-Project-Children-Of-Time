import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Future on 7/12/2016.
 */
public class MapEditor {
    private TileMap newMap;
    private JFrame mapFrame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveMenu, exitMenu, restartMapMenu;
    private ObjectOutputStream mapWriter;
    private ObjectInputStream mapReader;
    private JFileChooser openMapFileChooser;
    private JFileChooser saveMapFileChooser;
    private JComboBox comboBox;
    private int mapSize;
    private String mapName;
}

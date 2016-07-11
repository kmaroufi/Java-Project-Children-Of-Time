import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Future on 7/12/2016.
 */
public class MapEditor extends JPanel{
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

    private Thread mapEditorThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if(MouseInput.wasClicked() && menuBar != null) {
                    System.out.println((MouseInput.getX() - menuBar.getX()) + " - " + (MouseInput.getY() - menuBar.getY() - 23));
                }
                if (newMap != null) {
                    process();
                }
                repaint();
            }
        }
    });


    public MapEditor() {
        this.setFocusable(true);
        this.setSize(600, 600);
        //---------------------------------------------
        this.comboBox = new JComboBox();
        comboBox.addItem("Door");
        comboBox.addItem("Barrier");
        comboBox.addItem("Shop");
        comboBox.addItem("WarRoom");
        comboBox.addItem("FinalWar");
        comboBox.addItem("SkillRoom");
        comboBox.addItem("EmptyTile");
        comboBox.addItem("StoryBook");




//        this.door = new JRadioButton("Door");
//        this.checkFrame.add(door);
//
//        this.barrier = new JRadioButton("Barrier");
//        this.checkFrame.add(barrier);
//
//        this.shop = new JRadioButton("Shop");
//        this.checkFrame.add(shop);
//
//        this.warRoom = new JRadioButton("WarRoom");
//        this.checkFrame.add(warRoom);
//
//        this.skillRoom = new JRadioButton("SkillRoom");
//        this.checkFrame.add(skillRoom);
//
//        this.finalWar = new JRadioButton("FinalWar");
//        this.checkFrame.add(finalWar);
//
//        this.emptyTile = new JRadioButton("EmptyTile");
//        this.checkFrame.add(emptyTile);
        //----------------------------------------------------------------
        this.mapFrame = new JFrame(this.mapName);
        mapFrame.setFocusable(true);
        this.mapFrame.setLayout(new BorderLayout());
        this.mapFrame.add(this ,BorderLayout.CENTER);
        this.mapFrame.add(comboBox, BorderLayout.SOUTH);
        this.mapEditorThread.start();
        this.setMenuBar();
        showFrames();
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

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

    private void setMenuBar(){
        this.menuBar = new JMenuBar();
        this.menuBar.setSize(800,30);
        this.mapFrame.setJMenuBar(this.menuBar);
        this.fileMenu = new JMenu("File");
        this.saveMenu = new JMenuItem("Save");
        this.exitMenu = new JMenuItem("Exit");
        this.restartMapMenu = new JMenuItem("Restart Map");
        this.menuBar.add(fileMenu);
        this.fileMenu.add(saveMenu);
        this.fileMenu.add(restartMapMenu);
        this.fileMenu.add(exitMenu);
        this.saveMenu.addActionListener(this);
        this.restartMapMenu.addActionListener(this);
        this.exitMenu.addActionListener(this);
    }
    public void restartMap(){
        this.newMap = new TileMap("MAP-NAME", this.mapSize);
    }

    public void saveMap(){
        try {
            this.mapEditorThread.stop();
            this.mapWriter = new ObjectOutputStream(new FileOutputStream("./resources/maps/" + this.mapName + ".dat"));
            this.mapWriter.writeObject(this.newMap);
            this.mapReader = new ObjectInputStream(new FileInputStream("./resources/maps/" + this.mapName + ".dat"));
            TileMap tileMap = (TileMap) this.mapReader.readObject();
            if (tileMap != null) {
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        if (this.newMap == null) {
            return;
        }
        if (MouseInput.wasPressed(MouseEvent.BUTTON1)) {
            System.out.println("selected by Mouse");
            String mode = (String) this.comboBox.getSelectedItem();
            switch (mode) {
                case "Door":
                    this.newMap.addDoor(MouseInput.getX() - 10, MouseInput.getY() - 60);
                    break;
                case "Barrier":
                    this.newMap.addBarrier(MouseInput.getX(), MouseInput.getY());
                    break;
                case "Shop":
                    this.newMap.addShop(MouseInput.getX(), MouseInput.getY());
                    break;
                case "WarRoom":
                    this.newMap.addWarRoom(MouseInput.getX(), MouseInput.getY());
                    break;
                case "SkillRoom":
                    this.newMap.addSkillRoom(MouseInput.getX(), MouseInput.getY());
                    break;
                case "FinalWar":
                    this.newMap.addFinalWar(MouseInput.getX(), MouseInput.getY());
                    break;
                case "EmptyTile":
                    this.newMap.addEmptyTile(MouseInput.getX(), MouseInput.getY());
                    break;
                case "StoryBook":
                    this.newMap.addBook(MouseInput.getX(), MouseInput.getY());
                    break;
                default:
                    return;
            }
        }
    }



}

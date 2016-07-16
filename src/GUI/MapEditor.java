package GUI;

import Input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * Created by Future on 7/7/2016.
 */
public class MapEditor extends JPanel implements ActionListener,GameFrame{
    private JButton makeNewMapButton;
    private JButton editExistingMapButton;
    private JTable mapsTable;
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
        this.makeNewMapButton = new JButton();
        this.editExistingMapButton = new JButton();
        this.comboBox = new JComboBox();
        this.mapFrame = new JFrame(this.mapName);
        //---------------------------------------------
        this.addComboBoxItems();
        mapFrame.setFocusable(true);
        this.mapFrame.setLayout(new BorderLayout());
        this.mapFrame.add(this ,BorderLayout.CENTER);
        this.mapFrame.add(comboBox, BorderLayout.SOUTH);
        this.mapEditorThread.start();
        this.setMenuBar();
        showFrames();
    }

    private void addComboBoxItems(){
        comboBox.addItem("Door");
        comboBox.addItem("Barrier");
        comboBox.addItem("Shop");
        comboBox.addItem("WarRoom");
        comboBox.addItem("FinalWar");
        comboBox.addItem("SkillRoom");
        comboBox.addItem("EmptyTile");
        comboBox.addItem("StoryBook");
        comboBox.addItem("Key");
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

    public static void setMapSelection(){

    }

    public void getMapNames(){
        File folder = new File("./resources/maps");
        for (File fileEntry : folder.listFiles()) {
            int index = fileEntry.getName().indexOf(".");
            String fileName = fileEntry.getName().substring(0, index);
            System.out.println(fileName);
        }
    }

    private void process() {
        if (this.newMap == null) {
            return;
        }
        if (MouseInput.wasPressed(MouseEvent.BUTTON1)) {
            System.out.println("selected by Mouse");
            String mode = (String) this.comboBox.getSelectedItem();
            double x = MouseInput.getX() - 10;
            double y = MouseInput.getY() - 60;
            switch (mode) {
                case "Door":
                    this.newMap.addDoor(x, y);
                    break;
                case "Barrier":
                    this.newMap.addBarrier(x, y);
                    break;
                case "Shop":
                    this.newMap.addShop(x, y);
                    break;
                case "WarRoom":
                    this.newMap.addWarRoom(x, y);
                    break;
                case "SkillRoom":
                    this.newMap.addSkillRoom(x, y);
                    break;
                case "FinalWar":
                    this.newMap.addFinalWar(x, y);
                    break;
                case "EmptyTile":
                    this.newMap.addEmptyTile(x, y);
                    break;
                case "StoryBook":
                    this.newMap.addBook(x, y);
                    break;
                default:
                    return;
            }
        }
    }

    public void updateTileMap(Graphics graphics){
        if (this.newMap == null) {
            return;
        }
        this.newMap.render(graphics);
        this.newMap.paintPlaid(graphics);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        updateTileMap(graphics);
    }

    public void getMapSize(){
        String size = JOptionPane.showInputDialog(this, "Set Size of The Map(600 - 800 - 1000)");
        try {
            this.mapSize = Integer.parseInt(size);
            if (mapSize != 600 && mapSize != 800 && mapSize != 1000) {
                JOptionPane.showMessageDialog(null, "Wrong Size");
                getMapSize();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong Input");
            getMapSize();
        }
    }


    public void showFrames(){
        this.mapName = JOptionPane.showInputDialog(this, "Choose The Name Of Map");
        //------------------------------------------------------------
        this.getMapSize();
        //------------------------------------------------------------
//        this.checkFrame.addMouseListener(new MouseInput());
//        this.checkFrame.addKeyListener(new KeyInput());
//        requestFocus();
//        this.checkFrame.setSize(500, 80);
//        this.checkFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.checkFrame.setVisible(true);
        //------------------------------------------------------------
        this.newMap = new TileMap("MAP NAME", mapSize);      // NOTICE
        requestFocus();
        this.mapFrame.addMouseListener(new MouseInput());
        requestFocus();
        this.mapFrame.setSize(mapSize, mapSize);
        this.setSize(mapSize - 100, mapSize - 100);
        this.mapFrame.add(this);
        this.mapFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mapFrame.setVisible(true);
    }


    public static void main(String[] args) {
//        MapEditor firstMap = new MapEditor();
//        JOptionPane.showMessageDialog(null, "Hello");
        MapEditor.setMapSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMenu) {
            this.saveMap();
        } else if (e.getSource() == exitMenu) {
            System.exit(0);
        } else if (e.getSource() == restartMapMenu) {
            this.restartMap();
        }
    }

    @Override
    public void setBackgroundImage() {

    }

    @Override
    public void addActionListeners() {

    }

    @Override
    public void showFrame() {

    }

    @Override
    public void setButtons() {

    }

    @Override
    public void addComponents() {

    }

    @Override
    public void setComponentsBounds() {

    }

    @Override
    public void setFonts() {

    }
}

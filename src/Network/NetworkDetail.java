package Network;

import GUI.Display;
import PlayerPackage.Player;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by asus-pc on 7/16/2016.
 */
public class NetworkDetail {
    private ArrayList<Pair<String, ArrayList<Integer>>> currentHosts = new ArrayList<>();
    private boolean islocalPlayerIsHost;
    private String serverHostName;
    private Player localPlayer;
    private Player LanPlayer;
    private int startingXP;
    private int startingMoney;
    private int startingImortalityPotion;

    NetworkDetail(Player player) {
        this.islocalPlayerIsHost = false;
        this.localPlayer = player;
    }

    public void update() {
        if (islocalPlayerIsHost) {
            new PvPBattleServerStream(this).start();
        } else {
            new PvPBattleClientStream(this).start();
        }
    }

    public void joinServer(String serverHostName) {
        Socket socket = null;
        try {
            socket = new Socket(serverHostName, 25000);
            Display.printInEachLine("Connected to 25000");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(2);
            if (((String)in.readObject()).equals("OK")) {
                this.serverHostName = serverHostName;
                this.update();
                for (Pair<String, ArrayList<Integer>> pair: this.currentHosts) {
                    if (pair.getKey().equals(serverHostName)) {
                        this.startingXP = pair.getValue().get(0);
                        this.startingMoney = pair.getValue().get(1);
                        this.startingImortalityPotion = pair.getValue().get(2);
                    }
                }
            } else {
                System.out.println("error!");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + this.serverHostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + this.serverHostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(25000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10007.");
        }
        Socket socket = null;
        System.out.println("Waiting for connection.....");
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("on of em connected!");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                int code = (int) in.readObject();
                switch (code) {
                    case 1:
                        out.writeObject(String.valueOf(this.startingXP) + " " + String.valueOf(this.startingMoney) + " " + String.valueOf(this.startingImortalityPotion));
                        break;
                    case 2:
                        out.writeObject("OK");
                }
                if (code == 2) {
                    break;
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connection successful");
        this.update();
    }

    public void refreshHosts() {
        new Thread(new HostSercher()).start();
    }

    public class HostSercher implements Runnable {

        @Override
        public void run() {
            Socket socket = null;
            ObjectOutputStream out;
            ObjectInputStream in;
            String serverHostname = new String ("127.0.0.1");
            try{
                socket = new Socket(serverHostname, 25000);
                Display.printInEachLine("Connected to 25000");
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                out.writeObject(1);
                String[] settingInformation = ((String) in.readObject()).split(" ");
                int startingXP = Integer.parseInt(settingInformation[0]);
                int startingMoney = Integer.parseInt(settingInformation[1]);
                int startingImortalityPotion = Integer.parseInt(settingInformation[2]);
                ArrayList<Integer> informations = new ArrayList<>();
                informations.add(startingXP);
                informations.add(startingMoney);
                informations.add(startingImortalityPotion);
                NetworkDetail.this.currentHosts = new ArrayList<>();
                NetworkDetail.this.currentHosts.add(new Pair<>(serverHostname, informations));
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Pair<String, ArrayList<Integer>>> getCurrentHosts() {
        return currentHosts;
    }

    public void setCurrentHosts(ArrayList<Pair<String, ArrayList<Integer>>> currentHosts) {
        this.currentHosts = currentHosts;
    }

    public boolean islocalPlayerIsHost() {
        return islocalPlayerIsHost;
    }

    public void setIslocalPlayerIsHost(boolean islocalPlayerIsHost) {
        this.islocalPlayerIsHost = islocalPlayerIsHost;
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(Player localPlayer) {
        this.localPlayer = localPlayer;
    }

    public Player getLanPlayer() {
        return LanPlayer;
    }

    public void setLanPlayer(Player lanPlayer) {
        LanPlayer = lanPlayer;
    }

    public String getServerHostName() {
        return serverHostName;
    }

    public void setServerHostName(String serverHostName) {
        this.serverHostName = serverHostName;
    }
}

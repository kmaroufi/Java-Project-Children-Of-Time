package Network;

import GUI.Display;
import PlayerPackage.Player;
import Engine.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by asus-pc on 7/16/2016.
 */
public class PvPBattleClientStream extends Thread {
    private NetworkDetail networkDetail;
    private String serverHostName;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public PvPBattleClientStream(NetworkDetail networkDetail) {
        this.networkDetail = networkDetail;
        this.serverHostName = this.networkDetail.getServerHostName();
    }

    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(this.serverHostName, 25000);
            Display.printInEachLine("Connected to 25000");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(this.networkDetail.getLocalPlayer());
            this.networkDetail.setLanPlayer((Player) in.readObject());

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
}

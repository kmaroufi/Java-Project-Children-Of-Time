package Network; /**
 * Created by asus-pc on 7/16/2016.
 */

import PlayerPackage.*;
import GUI.Display;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PvPBattleServerStream extends Thread {
    private NetworkDetail networkDetail;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public PvPBattleServerStream(NetworkDetail networkDetail) {
        this.networkDetail = networkDetail;
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(25000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10007.");
        }
        Socket socket = null;
        System.out.println("Waiting for connection.....");
        try {
            socket = serverSocket.accept();
            System.out.println("on of em connected!");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            this.networkDetail.setLanPlayer((Player) in.readObject());
            out.writeObject(this.networkDetail.getLocalPlayer());
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connection successful");
    }
}

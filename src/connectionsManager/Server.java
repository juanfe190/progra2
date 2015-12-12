/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionsManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulacit
 */
public class Server {

    private Socket socket;
    private ServerSocket server;
    private List<Client> clientList;

    public Server() {
        clientList = new ArrayList<>();
        try {
            server = new ServerSocket(8952);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void accept() {
        try {
            while (true) {
                socket = server.accept();
                Client client = new Client(socket);
                client.start();
                clientList.add(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

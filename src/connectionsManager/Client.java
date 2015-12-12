/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionsManager;

import Models.computerModel;
import computersManager.modifyComputers;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverproyectoprogra.Util;

/**
 *
 * @author ulacit
 */
public class Client extends Thread {

    private Socket socket;
    private InputStreamReader input;
    private DataOutputStream output;
    private BufferedReader buffer;
    private computerModel computerModel;
    private modifyComputers modCompMethods;
    
    public Client(final Socket socket) {
        modCompMethods = new modifyComputers();
        try {
            this.socket = socket;
            this.input = new InputStreamReader(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
            this.buffer = new BufferedReader(input);
            this.computerModel = modCompMethods.findComputer(socket.getInetAddress().getHostAddress());
            computerModel.setStatus(Util.enEspera);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public computerModel getPCmodel(){
        return this.computerModel;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                readData();
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //METODO PERMITE EL ENVIO DE MENSAJES, MEDIANTE EL USO DEL .WRITE
    public void sendMessage(String Message) {
        try {
            this.output.writeUTF(Message + "\n");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //METODO PERMITE LA LECTURA DE LOS MENSAJES
     private void readData() {
        try {
            String info = this.buffer.readLine();
            if (!info.isEmpty()) {
                //ACA VA EL CODIGO QUE REALIZARA UNA VEZ QUE INGRESE UN MENSAJE DE CUALQUIER TIPO AL LADO DEL SERVIDOR
                //ES NECESRAIO PROGRAMAR ESTA PARTE
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

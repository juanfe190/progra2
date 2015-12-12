package computersManager;



import computersManager.modifyComputers;
import Models.computerModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverproyectoprogra.Util;

public class computersMonitor extends Thread{
    private Socket pcSocket;
    private OutputStream out;
    private InputStream in;
    ArrayList<computerModel> computersList;
    
    public computersMonitor(){
        computersList = new modifyComputers().getComputers();
    }
    @Override
    public void run() {
       
    }
    
    private void getAllStatus(){
        for(computerModel pc : computersList){
            try {
                pcSocket = new Socket(pc.getIp(), Util.puerto);
                pc.setStatus(Util.enEspera);
                
            } catch (IOException ex) {
                pc.setStatus(Util.sinConexion);
                Logger.getLogger(computersMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }finally{pcSocket=null;}
        }
    }
}

package serverproyectoprogra;

import computersManager.modifyComputers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerProyectoProgra {
    public static void main(String[] args) throws IOException {
        modifyComputers modifier =  new modifyComputers();
        modifier.setComputer("192", "prueba");
        try {
            modifier.deleteComputer("192");
        } catch (Exception ex) {
            Logger.getLogger(ServerProyectoProgra.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifier.setPrice("4000");
        
    }
    
}

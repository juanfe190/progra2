package Functionality;

import Models.computerModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import serverproyectoprogra.Util;

public class modifyComputers {
    
    public void setComputer(String ip, String name){
            try {
                writeData(new computerModel(ip, name));
            } catch (IOException ex) {
                Logger.getLogger(modifyComputers.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void deleteComputer(String ip) throws IOException, Exception{
            FileWriter writer = null;
            Scanner sc = null;
            boolean found=false;
            if(!computerExists(ip)) throw new Exception("La computadora no existe");
        try {
            File file = new File(Util.computersDir);
            sc = new Scanner(file);
            String writingData = "";
            String inputData= "";
            String scLine=null;
            do {
                try{
                    scLine=sc.nextLine();
                    String inputIP=scLine.trim().split(",")[0];
                    if(scLine!=null && !inputIP.equals(ip))
                        inputData+=scLine+System.getProperty("line.separator");
                }catch(NoSuchElementException ex){break;}
            } while (scLine!=null);
            writer = new FileWriter(file, false);
            writingData+=System.getProperty("line.separator")+inputData;
            writer.write(writingData);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(modifyComputers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(modifyComputers.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            sc.close();
            writer.close();
        }
    }
    
    public ArrayList<computerModel> getComputers(){
        File file = new File(Util.computersDir);
        Scanner sc = null;
        ArrayList<computerModel> computers = new ArrayList<>();
        try {
            sc = new Scanner(file);
            String scLine;
            do {     
                scLine = sc.nextLine();
                if(scLine!=null){
                    String data[]=scLine.trim().split(",");
                    computers.add(new computerModel(data[0], data[1]));
                }
                
            } while (scLine!=null);
        }catch(NoSuchElementException ex){} 
         catch (FileNotFoundException ex) {
            System.out.println("ARCHIVO NO ENCONTRADO");
            System.err.println(ex);
        }finally{ 
            sc.close();
        }
        
        return computers;
    }
    
    public void setPrice(String price){
        try {
            File configFile = new File(Util.configDir);
            if(!configFile.exists()) new FileWriter(configFile).write("");
            Properties props = new Properties();
            props.load(new FileInputStream(configFile));
            props.setProperty("price", price);
            props.store(new FileWriter(configFile), null);
            
        } catch (IOException ex) {
            Logger.getLogger(modifyComputers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean computerExists(String ip){
        File file = new File(Util.computersDir);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            String scLine;
            do {     
                scLine = sc.nextLine();
                if(scLine!=null){
                    String data[]=scLine.trim().split(",");
                    if(data[0].equals(ip)){
                        return true;
                    }
                }
                
            } while (scLine!=null);
        }catch(NoSuchElementException ex){} 
         catch (FileNotFoundException ex) {
            System.out.println("ARCHIVO NO ENCONTRADO");
            System.err.println(ex);
        }finally{ 
            sc.close();
        }
        
        return false;
    }
    
    private void writeData(computerModel computer) throws IOException{
        String writingData = "";
        writingData+=computer.getIp() + ",";
        writingData+=computer.getName();
        writingData=writingData.trim();
        File file = new File(Util.computersDir);
        
        if(!file.exists()) new FileWriter("computers.txt").write("");
        FileWriter writer = null;
        Scanner sc = new Scanner(file);
        try {
            if(!computerExists(computer.getIp())){
                writer = new FileWriter(Util.computersDir, true);
                writer.write(writingData+System.getProperty("line.separator"));
                
            }else{
                String inputData= "";
                String scLine=null;
                do {    
                        try{
                            scLine=sc.nextLine();
                            String inputIP=scLine.trim().split(",")[0];
                            if(scLine!=null && !inputIP.equals(computer.getIp()))
                                inputData+=scLine+System.getProperty("line.separator");
                            
                            
                        }catch(NoSuchElementException ex){break;}     
                } while (scLine!=null);
                
                writer = new FileWriter(file, false);
                writingData+=System.getProperty("line.separator")+inputData;
                writer.write(writingData);
            }
        } 
          catch (IOException ex) {
            Logger.getLogger(modifyComputers.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            writer.close();
            sc.close();
        }
    }
}

package Models;


public class computerModel {
    private String ip;
    private String name;
    private int status;
    private long initialTime;

    public computerModel(String ip, String name){
        this.ip = ip;
        this. name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

    @Override
    public String toString() {
        return "computerModel{" + "ip=" + ip + ", name=" + name + ", status=" + status + ", initialTime=" + initialTime + '}';
    }
    
    
    
}

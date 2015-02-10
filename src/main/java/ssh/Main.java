package ssh;

import ua.pp.msk.hostinfo.CommandReturn;
import ua.pp.msk.hostinfo.ExecCommand;
import ua.pp.msk.hostinfo.ExecCommandImpl;
import up.pp.msk.ssh.DeviceInfo;

public class Main implements DeviceInfo {

    //cat /proc/uptime
    public long getUpTime() {
        // TODO Auto-generated method stub
        return 0;
    }
//cat /proc/uptime

    public long getIdleTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getUptimeInfo() {
        // TODO Auto-generated method stub
        return uptime;
    }

    public String getLoadAverage() {
        // TODO Auto-generated method stub
        return loadAverage;
    }

    public float getLoadAverage1min() {
        // TODO Auto-generated method stub
        return loadAverage1min;
    }

    public float getLoadAverage5min() {
        // TODO Auto-generated method stub
        return loadAverage5min;
    }

    public float getLoadAverage15min() {
        // TODO Auto-generated method stub
        return loadAverage15min;
    }

    public int getLoggedInUsers() {
        // TODO Auto-generated method stub
        return loggedInUsers;
    }

    public String getIpAddress() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getMacAddress() {
        // TODO Auto-generated method stub
        return macAddr;
    }

    public String getHostname() {

        return hostname;
    }

    public long getTotalRam() {
        // TODO Auto-generated method stub
        return totalRam;
    }

    public long getFreeRam() {
        // TODO Auto-generated method stub
        return freeRam;
    }

    public int getNumberOfCores() {
        // TODO Auto-generated method stub
        return numOfCores;
    }

    private static void main() {
        System.out.println("WOW!");
    }
    private ExecCommand ec;
    private String hostname;
    private String uptime;
    private String loadAverage;
    private int loggedInUsers;
    private long freeRam;
    private long totalRam;
    private float loadAverage1min;
    private float loadAverage5min;
    private float loadAverage15min;
    private int numOfCores;
    private String macAddr;

    private void setLoggedInUsers() {
        CommandReturn result = ec.executeCommand("who | wc -l");
        loggedInUsers = (int) Float.parseFloat(result.getStdOut());
    }
//memstat parsing change
    private void setFreeRam() {
        CommandReturn result = ec.executeCommand(" free -m | grep Mem | awk  '{print $4}'");
        freeRam = (long) Float.parseFloat(result.getStdOut());
    }
  //memstat parsing change
    private void setTotalRam() {
        CommandReturn result = ec.executeCommand("free -m | grep Mem | awk  '{print $2}'");
        totalRam = (long) Float.parseFloat(result.getStdOut());
    }

    private void setNumberOfCores() {
        CommandReturn result = ec.executeCommand("grep ^processor /proc/cpuinfo | wc -l");
        numOfCores = Integer.parseInt(result.getStdOut().replaceAll("\n", ""));
    }

    private void setLoadAverage1min() {
        CommandReturn result = ec.executeCommand("uptime | awk -F : '{print substr($5,1,5)}'");
        loadAverage1min = Float.parseFloat(result.getStdOut());
    }

    private void setLoadAverage5min() {
        CommandReturn result = ec.executeCommand("uptime | awk -F, '{print $5}'");
        loadAverage5min = Float.parseFloat(result.getStdOut());
    }

    private void setLoadAverage15min() {
        CommandReturn result = ec.executeCommand("uptime | awk -F, '{print $6}'");
        loadAverage15min = Float.parseFloat(result.getStdOut());
    }

    private void setLoadAverage() {
        CommandReturn result = ec.executeCommand(" uptime | awk -F, '{print $4,\",\",$5,\",\",$6}'");
        loadAverage = result.getStdOut();
    }

    private void setUptimeInfo() {
        CommandReturn result = ec.executeCommand("uptime");
        uptime = result.getStdOut();
    }

    private void setMacAddress() {
        CommandReturn result = ec.executeCommand("cat /sys/class/net/$(cat /proc/net/route | awk '{ if ($2 == 00000000) print $1; }')/address");
        macAddr = result.getStdOut();
    }

    public Main(String host, int port, String userName, String passwd) {
        ec = new ExecCommandImpl(host, port, userName, passwd, null);
    }

    private void setHostname() {
        CommandReturn result = ec.executeCommand("hostname -f");
        hostname = result.getStdOut();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
      sb.append("Hostname is: ").append(getHostname());
      sb.append("\nuptime is:").append(getUptimeInfo());
      sb.append("\nNumber of cores is: ").append(getNumberOfCores());
      sb.append("\nTotal RAM is ").append(getTotalRam());
      sb.append("\nFree RAM is ").append(getFreeRam());
      sb.append("\nQuantity of logged in users is ").append(getLoggedInUsers());
      sb.append("\nMac adress is: ").append(getMacAddress());
      sb.append("\nLoad Average is: ").append(getLoadAverage());
      sb.append("\nload average 1min is: ").append(getLoadAverage1min());
      sb.append("\nload average 5min is: ").append(getLoadAverage5min());
      sb.append("\nload average 15min is: ").append(getLoadAverage15min());
      return sb.toString();
    }

    public static void main(String args[]) {
        main();
        Main m = new Main("maskimko.msk.pp.ua", 22, "edem", "123456");
        m.setHostname();
        m.setUptimeInfo();
        m.setLoadAverage();
        m.setLoggedInUsers();
        m.setFreeRam();
        m.setTotalRam();
        m.setNumberOfCores();
        m.setLoadAverage1min();
        m.setLoadAverage5min();
        m.setLoadAverage15min();
        m.setMacAddress();
        System.out.println(m);
        
    }

}

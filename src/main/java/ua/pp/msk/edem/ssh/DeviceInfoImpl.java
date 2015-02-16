package ua.pp.msk.edem.ssh;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import ua.pp.msk.hostinfo.CommandReturn;
import ua.pp.msk.hostinfo.ExecCommand;
import ua.pp.msk.hostinfo.ExecCommandImpl;



public class DeviceInfoImpl implements DeviceInfo {
	//cat /proc/uptime
    public long getUpTime() {
        // TODO Auto-generated method stub
        return uptimeup;
    }
//cat /proc/uptime

    public long getIdleTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /**
     * Should return simple output of uptime command
     * @return String stdout of uptime command
     */
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
        return ip.getHostName();
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

   public int getuptimeupSec() {
	   return uptimeupSec;
   }
   public float getIdletimeupSec() {
	   return idletimeupSec;
   }
    public int getNumberOfCores() {
        // TODO Auto-generated method stub
        return numOfCores;
    }

    private static final String LOGINUSERSCMD = "who | wc -l";
    
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
    private long uptimeup;
    private float idletimeupSec;
    private  int uptimeupSec;
    private InetAddress ip;
    
    final void setLoggedInUsers() {
        CommandReturn result = ec.executeCommand(LOGINUSERSCMD);
        loggedInUsers = (int) Float.parseFloat(result.getStdOut());
    }

    final void setFreeRam() {
        CommandReturn result = ec.executeCommand(" cat /proc/meminfo | grep MemFree | awk '{print $2}'");
        freeRam = (long) Float.parseFloat(result.getStdOut());
    }
  
    final void setTotalRam() {
        CommandReturn result = ec.executeCommand(" cat /proc/meminfo | grep MemTotal | awk '{print $2}'");
        totalRam = (long) Float.parseFloat(result.getStdOut());
    }

    final void setNumberOfCores() {
        CommandReturn result = ec.executeCommand("grep ^processor /proc/cpuinfo | wc -l");
        numOfCores = Integer.parseInt(result.getStdOut().replaceAll("\n", ""));
    }
    
	final void setUpTime() {
        CommandReturn result = ec.executeCommand("cat /proc/uptime | awk '{print $1}'");
        uptimeup = (long) Float.parseFloat(result.getStdOut().replaceAll("\n", ""));
      
        		//Integer.parseInt(result.getStdOut().replaceAll("\n", ""));
    }
         
         //DON'T like it
    final  void setUpTimeSec()  {
    	CommandReturn result = ec.executeCommand("cat /proc/uptime | awk '{print $1}'");
    	uptimeupSec= (int)  Float.parseFloat(result.getStdOut().replaceAll("\n", ""));
    }
    final void setIdleTimeSec()  {
    	CommandReturn result = ec.executeCommand("cat /proc/uptime | awk '{print $2}'");
    	idletimeupSec=   Float.parseFloat(result.getStdOut().replaceAll("\n", ""));
    }
	final void setLoadAverage1min() {
        CommandReturn result = ec.executeCommand(" cat /proc/loadavg  | awk '{print $1}'");
        loadAverage1min = Float.parseFloat(result.getStdOut());
    }

    final void setLoadAverage5min() {
        CommandReturn result = ec.executeCommand(" cat /proc/loadavg  | awk '{print $2}'");
        loadAverage5min = Float.parseFloat(result.getStdOut());
    }

    final void setLoadAverage15min() {
        CommandReturn result = ec.executeCommand(" cat /proc/loadavg  | awk '{print $3}'");
        loadAverage15min = Float.parseFloat(result.getStdOut());
    }

    final void setLoadAverage() {
        CommandReturn result = ec.executeCommand("  cat /proc/loadavg");
        loadAverage = result.getStdOut();
    }

    final void setUptimeInfo() {
        CommandReturn result = ec.executeCommand("uptime");
        uptime = result.getStdOut();
    }

    final void setMacAddress() {
        CommandReturn result = ec.executeCommand("cat /sys/class/net/$(cat /proc/net/route | awk '{ if ($2 == 00000000) print $1; }')/address");
        macAddr = result.getStdOut();
    }

    public DeviceInfoImpl(String host, int port, String userName, String passwd) throws UnknownHostException {
        ec = new ExecCommandImpl(host, port, userName, passwd, null);
        this.ip =  Inet4Address.getByName(host);
        /*
        Make all commands as a constants or resource bundles to ensure type safety.
        Make a batch command processing. Something like this:
        */
//        String[] commands = new String[]{LOGINUSERSCMD, " cat /proc/meminfo | grep MemFree | awk '{print $2}'", " cat /proc/meminfo | grep MemTotal | awk '{print $2}'", "grep ^processor /proc/cpuinfo | wc -l",
//        "cat /proc/uptime | awk '{print $1}'", "cat /proc/uptime | awk '{print $1}'", "cat /proc/uptime | awk '{print $1}'" ...};
//        ec.executeCommand(commands)
        //It will improve speed in times
        //Better to move it to some private method init();
        //And then we can remove final modificator on the setters
        setFreeRam();
        setHostname();
        setIdleTimeSec();
        setLoadAverage();
        setLoadAverage15min();
        setLoadAverage1min();
        setLoadAverage5min();
        setLoggedInUsers();
        setMacAddress();
        setNumberOfCores();
        setTotalRam();
        setUpTime();
        setUpTimeSec();
        setUptimeInfo();
    }

     final void setHostname() {
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
      sb.append("\nserver is up: ").append(getUpTime());
      return sb.toString();
    }

    public Calendar getSwitchedOnDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getIdleTimeAsDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InetAddress getInetAddress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] getMacBytes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}

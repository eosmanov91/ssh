package ssh;

import ua.pp.msk.hostinfo.CommandReturn;
import ua.pp.msk.hostinfo.ExecCommand;
import ua.pp.msk.hostinfo.ExecCommandImpl;
import up.pp.msk.ssh.DeviceInfo;


public class Main implements DeviceInfo {

	public long getUpTime() {
		// TODO Auto-generated method stub
		return 0;
	}

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

	public  float getLoadAverage5min() {
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

	private static void main(){
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
		loggedInUsers = (int) Float.parseFloat( result.getStdOut());
	}
	
	private void setFreeRam() {
		CommandReturn result = ec.executeCommand(" free -m | grep Mem | awk  '{print $4}'");
		freeRam = (long) Float.parseFloat ( result.getStdOut());
	}
	private void setTotalRam() {
		CommandReturn result = ec.executeCommand("free -m | grep Mem | awk  '{print $2}'");
		totalRam = (long) Float.parseFloat( result.getStdOut());
	}
	private void setNumberOfCores() {
		CommandReturn result = ec.executeCommand("lscpu | grep 'CPU(s):' | awk '{print $2}'");
		numOfCores = (int) Float.parseFloat( result.getStdOut());
	}
	private void setLoadAverage1min() {
		CommandReturn result = ec.executeCommand("uptime | awk -F : '{print substr($5,1,5)}'");
		 loadAverage1min = Float.parseFloat( result.getStdOut());
	}
	private void setLoadAverage5min() {
		CommandReturn result = ec.executeCommand("uptime | awk -F, '{print $5}'");
		 loadAverage5min = Float.parseFloat( result.getStdOut());
	}
	
	private void setLoadAverage15min() {
		CommandReturn result = ec.executeCommand("uptime | awk -F, '{print $6}'");
		 loadAverage15min = Float.parseFloat( result.getStdOut());
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
		CommandReturn result = ec.executeCommand("/sbin/ifconfig | grep eth | grep HW | awk '{print -F $5}'");
		macAddr = result.getStdOut();
	}
	public Main(String host, int port, String userName, String passwd) {
		ec = new ExecCommandImpl(host, port, userName, passwd, null );
	}
	
	private void setHostname()  {
		CommandReturn result = ec.executeCommand("hostname -f");
		hostname =  result.getStdOut();
	}
	@Override 
	public String toString() {
		return "I don't know nothing, except the hostname: " + getHostname();
	}
	public static void main(String args[]){
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
		System.out.println (m);
		System.out.println("Hostname is: " +m.getHostname());
		System.out.println ("uptime is:" + m.getUptimeInfo());
		System.out.println("Number of cores is: " + m.getNumberOfCores());
		System.out.println ("Total RAM is " + m.getTotalRam());
		System.out.println ("Free RAM is " + m.getFreeRam());
		System.out.println ("Quantity of logged in users is " +m.getLoggedInUsers());
		System.out.println ("Mac adress is: " + m.getMacAddress());
		System.out.println("Load Average is: " + m.getLoadAverage());
		System.out.println("load average 1min is: " + m.getLoadAverage1min());
		System.out.println("load average 5min is: " + m.getLoadAverage5min());
		System.out.println("load average 15min is: " + m.getLoadAverage15min());
	}
	
}

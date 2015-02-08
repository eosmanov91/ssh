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
		return null;
	}

	public String getLoadAverage() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getLoadAverage1min() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getLoadAverage5min() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getLoadAverage15min() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getLoggedInUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getIpAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMacAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHostname() {
		
		return hostname;
	}

	public long getTotalRam() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getFreeRam() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfCores() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void main(){
		System.out.println("WOW!");
	}
	private ExecCommand ec;
	private String hostname;
	
	public Main(String host, int port, String userName, String passwd) {
		ec = new ExecCommandImpl(host, port, userName, passwd, null );
	}
	
	private void setHostname()  {
		CommandReturn result = ec.executeCommand("hostname -f");
		hostname = result.getStdOut();
	}
	@Override 
	public String toString() {
		return "I don't know nothing, except the hostname: " + getHostname();
	}
	public static void main(String args[]){
		main();
		Main m = new Main("maskimko.msk.pp.ua", 22, "edem", "123456");
		m.setHostname();
		System.out.println (m);
		System.out.println(m.getHostname());
			
	}
	
}

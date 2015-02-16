package ua.pp.msk.edem.ssh;

public class Main{
  public static void main(String args[]) {
       
        DeviceInfoImpl m = new DeviceInfoImpl("silhouette.kyi.msk", 22, "edem", "123456");
        m.setHostname();
        m.setUptimeInfo();
        m.setLoadAverage();
        m.setLoggedInUsers();
        m.setFreeRam();
        m.setTotalRam();
        m.setUpTimeSec();
        m.setNumberOfCores();
        m.setLoadAverage1min();
        m.setIdleTimeSec();
        m.setLoadAverage5min();
        m.setLoadAverage15min();
        m.setMacAddress();
        m.setUpTime();
        System.out.println(m);
        SecondsToDate t= new SecondsToDate(m.getuptimeupSec());
        SecondsToDate tt= new SecondsToDate(m.getIdletimeupSec());
    }
}
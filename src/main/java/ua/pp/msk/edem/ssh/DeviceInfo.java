/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.edem.ssh;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author maskimko
 */
public interface DeviceInfo {

    //cat /proc/uptime
    public long getUpTime();
    /**
     * Shows the date when computer was turned on
     * @return Calendar object
     */
    public Calendar getSwitchedOnDate();
    public long getIdleTime();
    public Date getIdleTimeAsDate();
    //uptime

    public String getUptimeInfo();

    public String getLoadAverage();
    public float getLoadAverage1min();
    public float getLoadAverage5min();
    public float getLoadAverage15min();
    
    //who | wc -l 

    public int getLoggedInUsers();

    public String getIpAddress();
    public InetAddress getInetAddress();
    public String getMacAddress();
    public byte[] getMacBytes();
    public String getHostname();

    //cat /proc/meminfo in kb

    public long getTotalRam();

    public long getFreeRam();

    //grep processor /proc/cpuinfo | wc -l 

    public int getNumberOfCores();

}

package ssh;


public class SecondsToDate {
		int days = 0;
		int hours = 0;
		int mins= 0;
		int second= 0;
		
		
	    public SecondsToDate(int time) {
	    	days = time/86400;
	    	hours = (time - (days*86400))/3600;
	    	mins= (time- (days*86400) -(hours*3600))/60;
	    	//t.second = (t.time- (t.days*86400) -(t.hours*3600) );
	    	if (mins > 10) {
	    	System.out.println ("Uptime is: " + days +" days, " + hours + ":" + mins);
	    	} else {
	    		System.out.println ("Uptime is: " + days +" days, " + hours + ":0" + mins);	
	    	}
	    	
	    
	}
		
	    public SecondsToDate(float time) {
	    	days = (int) (time/86400);
	    	hours = (int) ((time - (days*86400))/3600);
	    	mins= (int) ((time- (days*86400) -(hours*3600))/60);
	    	//t.second = (t.time- (t.days*86400) -(t.hours*3600) );
	    	if (mins > 10) {
		    System.out.println ("Idle time  is: " + days +" days, " + hours + ":" + mins);
	    	} else {
		    System.out.println ("Idle time is: " + days +" days, " + hours + ":0" + mins);	
		    }
		    	
	    
	}

}

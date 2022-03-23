package org.piesrgr8.dev.manager;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
	private HashMap<String, Timer> timers = new HashMap<String, Timer>();
	
	public void createTimer(String pill, String time) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// Run shit here
			}
		}, 3*60*1000);
		timers.put(pill, timer);
	}
	
	public void removeTimer(String pill) {
		timers.remove(pill);
	}
	
	public void resumeTimers() {
		
	}
	
	public HashMap<String, Timer> getTimers() {
		return timers;
	}
}

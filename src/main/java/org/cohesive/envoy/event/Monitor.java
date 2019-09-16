package org.cohesive.envoy.event;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Abstract class for functionality related to
 * implementing any sort of monitor, e.g monitor interval
 * and a call to perform the monitor action.
 * 
 * @author dharrington
 *
 */
public abstract class Monitor {
	
	protected static final Logger LOGGER = Logger.getLogger(Monitor.class.getName());
	
	/**
	 * Public constructor that
	 * schedules the event monitor.
	 */
	public Monitor() {
		schedule();
	}
	
	public static final MonitorEventEmitter emitter = MonitorEventEmitterFactory.getEmitter();
	
	/**
	 * This creates a TimerTask/Timer that will execute a method
	 * in the Monitor at a given interval.
	 */
	public void schedule(){
	    TimerTask repeatedTask = new TimerTask() {
	        @Override
			public void run() {
	    		LOGGER.log(Level.FINE, "Task performed on " + new Date());
	        	MonitorEvent event = performCheck();
	        	emitter.emit(event);
	        }
	    };
	    Timer timer = new Timer("Timer-" + this.getClass().getSimpleName());
	     
	    long delay  = 5000L;
	    long period = 30000L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
	
	/**
	 * This must be implemented to perform the check and return
	 * an event containing the results of the check.
	 * @return
	 */
	public abstract MonitorEvent performCheck();
	
}

package org.cohesive.envoy.event;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
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
	
	protected ScheduledExecutorService executorService;
	
	protected static final Logger LOGGER = Logger.getLogger(Monitor.class.getName());
	
	public static final MonitorEventEmitter emitter = MonitorEventEmitterFactory.getEmitter();
	
	/**
	 * This creates a scheduler thread that will execute a method
	 * in the Monitor at a given interval and start delay.
	 * @param delay The start delay
	 * @param interval How often to execute performCheck()
	 */
	public ScheduledFuture<?> schedule(Long delay, Long interval){
		executorService =  Executors.newScheduledThreadPool(1);
		return schedule(delay, interval, executorService);
	}

	/**
	 * This creates a scheduler thread that will execute a method
	 * in the Monitor at a given interval and start delay.
	 * @param delay The start delay
	 * @param interval How often to execute performCheck()
	 * @param the executor service to use
	 */
	public ScheduledFuture<?> schedule(Long delay, Long interval, ScheduledExecutorService service) {
		this.executorService = service;
	    return executorService.scheduleAtFixedRate( new Runnable() {
	        @Override
			public void run() {
	    		LOGGER.log(Level.FINE, "Task performed on " + new Date());
	        	MonitorEvent event = performCheck();
	        	if (event.getSend()) {
	        		emitter.emit(event);
	        	}
	        }
	    }, delay, interval, TimeUnit.MILLISECONDS);
	}

	/**
	 * This must be implemented to perform the check and return
	 * an event containing the results of the check.
	 * @return The results of the check as a monitor event
	 */
	public abstract MonitorEvent performCheck();
	
}

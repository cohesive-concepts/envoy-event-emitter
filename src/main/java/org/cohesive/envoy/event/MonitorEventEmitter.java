package org.cohesive.envoy.event;

/**
 * Interface to be implemented for monitor event
 * emitter to the eventing system regardless of
 * eventing system implementation details.
 * 
 * @author dharrington
 */
public interface MonitorEventEmitter {
	
	/**
	 * Creates an event object.
	 * @return The event object
	 */
	public MonitorEvent createEvent();
	
	/**
	 * Triggers the event report.
	 * @param event The event
	 * @return Success or failure during send
	 */
	public Boolean emit(MonitorEvent event);

}

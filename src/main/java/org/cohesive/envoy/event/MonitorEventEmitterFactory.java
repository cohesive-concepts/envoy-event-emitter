package org.cohesive.envoy.event;

/**
 * Factory class that provides an event emitter
 * implementation based on the event service 
 * type (e.g. DataDog, Logz.io, and others).
 * 
 * @author dharrington
 * 
 */
public class MonitorEventEmitterFactory {
	
	public static final MonitorEventEmitter getEmitter() {
		switch (getType().toUpperCase()) {
			case "DATADOG" :
				return new DataDogMonitorEventEmitter();
			default:
				throw new IllegalArgumentException("Unknown Event Emitter type");
		}
	}
	
	private static String getType() {
		return System.getenv("ENV_EVENT_SVC_TYPE");
	}

}

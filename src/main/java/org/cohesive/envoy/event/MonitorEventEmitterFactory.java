package org.cohesive.envoy.event;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Factory class that provides an event emitter
 * implementation based on the event service 
 * type (e.g. DataDog, Logz.io, and others).
 * 
 * @author dharrington
 * 
 */
public class MonitorEventEmitterFactory {
	
	protected static final Logger LOGGER = Logger.getLogger(Monitor.class.getName());
	
	/**
	 * Factory method that will load all implementations
	 * of MonitorEventEmitter that are configure to be
	 * locatable by ServiceLoader
	 * 
	 * @return a single implementation of the emitter
	 */
	@SuppressWarnings("unused")
	public static final MonitorEventEmitter getEmitter() {
		ServiceLoader<MonitorEventEmitter> services = ServiceLoader.load(MonitorEventEmitter.class);
		Integer classesLoaded = 0;
		MonitorEventEmitter emitter = new NoOpMonitorEventEmitter();
		for (MonitorEventEmitter service : services) {
			classesLoaded++;
		}
		if (classesLoaded == 0) {
			LOGGER.warning("No MonitorEventEmitter implementation found, loading no-op implementation.");
		} else if (classesLoaded > 1) {
			LOGGER.warning("Multiple MonitorEventEmitter implementations found, loading the first one. This is probably not what you want.");
		} else {
			emitter = services.iterator().next();
		}
		return emitter;
	}
}

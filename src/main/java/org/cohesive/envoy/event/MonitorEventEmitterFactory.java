package org.cohesive.envoy.event;

import java.util.ServiceLoader;

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
		ServiceLoader<MonitorEventEmitter> services = ServiceLoader.load(MonitorEventEmitter.class);
		return services.iterator().next();
	}
}

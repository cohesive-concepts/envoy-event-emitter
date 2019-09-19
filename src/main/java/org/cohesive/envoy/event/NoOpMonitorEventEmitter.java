package org.cohesive.envoy.event;
/**
 * No-Op emitter as default implementation
 * 
 * @author dharrington
 *
 */
public class NoOpMonitorEventEmitter implements MonitorEventEmitter {

	@Override
	public MonitorEvent createEvent() {
		return new MonitorEvent();
	}

	@Override
	public Boolean emit(MonitorEvent event) {
		return false;
	}

}

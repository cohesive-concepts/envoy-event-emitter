package org.cohesive.envoy.event;

/**
 * Class that provides basic 
 * functionality required for constructing
 * an interesting event regardless of the 
 * underlying event service implementation.
 * 
 *  @author dharrington
 */
@SuppressWarnings("hiding")
public class MonitorEvent {

	private String applicationId;
	private String title;
	private Long timestamp;
	private String message;
	private String priority;
	private Boolean send = false; 
	
	public MonitorEvent applicationId(String applicationId) {
		this.applicationId = applicationId;
		return this;
	}

	public MonitorEvent title(String title) {
		this.title = title;
		return this;
	}
	
	public MonitorEvent message(String message) {
		this.message = message;
		return this;
	}

	public MonitorEvent priority(String priority) {
		this.priority = priority;
		return this;
	}
	
	public MonitorEvent timestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	public MonitorEvent send(Boolean send) {
		this.send = send;
		return this;
	}
	
	public String getApplicationId() {
		return this.applicationId;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getPriority() {
		return this.priority;
	}
	
	public Long getTimestamp() {
		return this.timestamp;
	}

	public Boolean getSend() {
		return send;
	}

	public void setSend(Boolean send) {
		this.send = send;
	}
}

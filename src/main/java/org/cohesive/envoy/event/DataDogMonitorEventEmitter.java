package org.cohesive.envoy.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cohesive.envoy.json.Json;
import org.cohesive.envoy.json.JsonObject;

/**
 * Implementation of the abstract MonitorEventEmitter.
 * @see MonitorEventEmitter
 * 
 * @author dharrington
 */
public class DataDogMonitorEventEmitter implements MonitorEventEmitter {

	private static final Logger LOGGER = Logger.getLogger(DataDogMonitorEventEmitter.class.getName());
	
	private String hostUrl;

	private String apiKey;

	public DataDogMonitorEventEmitter() {
		super();
		this.init();
	}

	@Override
	public MonitorEvent createEvent() {
		return new MonitorEvent();
	}

	public void init() {
		this.hostUrl = System.getenv("ENV_EVENT_SVC_URL");
		this.apiKey = System.getenv("ENV_EVENT_SVC_API_KEY");
	}

	@Override
	public Boolean emit(MonitorEvent event) {

		JsonObject object = Json.object();
		object.add("title", event.getTitle());
		object.add("text", event.getMessage());
		object.add("alert_type", event.getPriority());
		object.add("date_happenend", event.getTimestamp());
		object.add("tags", Json.array("environment:dev", "application:" + event.getApplicationId()));
		HttpURLConnection conn = null;
		try {
			URL url = new URL(hostUrl + "?api_key=" + apiKey);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			byte[] input = object.toString().getBytes("utf-8");
			conn.getOutputStream().write(input, 0, input.length);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			LOGGER.log(Level.SEVERE, "Invalid event service URL: ", e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Cannot connect to event service: ", e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return true;
	}
}

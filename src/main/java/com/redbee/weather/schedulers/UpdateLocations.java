package com.redbee.weather.schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redbee.weather.model.entity.Forecast;
import com.redbee.weather.model.entity.Location;
import com.redbee.weather.service.ILocationService;
import com.redbee.weather.service.IUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UpdateLocations {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUserService userService;
	
	/**
	 * Search all locations and update their data.
	 * Also, for new locations (enabled = false), if results are found 
	 * by WOEID, change the flag to enabled=true, otherwise eliminate them.
	 * @throws IOException
	 * @throws JSONException
	 */
	@Scheduled(fixedRate = 44000)
	public void updateLocations() throws IOException, JSONException {

		Date init = new Date();
		
		Flux<Location> locationsFlux = locationService.findAll();
		List<Location> locations = locationsFlux.collectList().block();
		
		if (locations == null) {
			return;
		}
		
		String query = getQueryByWoeid(locations);
		String result = retrieveRSS(query);
		
		JSONObject jsonObj = new JSONObject(result);
		JSONArray jsonRoot = jsonObj.getJSONObject("query").getJSONObject("results").getJSONArray("channel");
		for (int i = 0; i < jsonRoot.length(); i++) {
			try {
			JSONObject root = jsonRoot.getJSONObject(i);
			String temperatureUnit = root.getJSONObject("units").get("temperature").toString();
			String name = root.getJSONObject("location").get("city").toString();
			String humidity = root.getJSONObject("atmosphere").get("humidity").toString();
			String visibility = root.getJSONObject("atmosphere").get("visibility").toString();
			String temperature = root.getJSONObject("item").getJSONObject("condition").get("temp").toString();
			String text = root.getJSONObject("item").getJSONObject("condition").get("text").toString();
			String pubDate = root.getJSONObject("item").get("pubDate").toString();
			
			JSONArray forecastArray = root.getJSONObject("item").getJSONArray("forecast");
			List<Forecast> forecasts = new ArrayList<Forecast>();
			if (forecastArray != null) {
				forecastArray.forEach(item -> {
				    JSONObject obj = (JSONObject) item;
				    Forecast forecast =  new Forecast();
				    forecast.setDate(obj.get("date").toString());
				    forecast.setDay(obj.get("day").toString());
					if (Location.fahrenheit_code.equals(temperatureUnit.toUpperCase())) {
						forecast.setHigh(String.valueOf(Integer.valueOf(obj.get("high").toString())-Location.celsius_diff));
					    forecast.setLow(String.valueOf(Integer.valueOf(obj.get("low").toString())-Location.celsius_diff));
					}else {
					    forecast.setHigh(obj.get("high").toString());
					    forecast.setLow(obj.get("low").toString());
					}

				    forecast.setText(obj.get("text").toString());
				    forecasts.add(forecast);
				});
			}
			
			String woeid = root.getJSONObject("item").get("link").toString().split("/")[root.getJSONObject("item").get("link").toString().split("/").length-1].replace("city-", "");

			Location location = locationService.findByWoeid(woeid).block();
			if (location != null) {
				if (pubDate != location.getPubDate()) {
					location.setName(name);
					location.setPubDate(pubDate);
					location.setHumidity(humidity);
					location.setVisibility(visibility);
					if (Location.fahrenheit_code.equals(temperatureUnit.toUpperCase())) {
						location.setTemperature(String.valueOf(Integer.valueOf(temperature)-Location.celsius_diff));
					}else {
						location.setTemperature(temperature);
					}
					location.setText(text);
					if (forecasts.size() > 0) {
						location.setForecasts(forecasts);
					}
					location.setEnabled(Boolean.TRUE);
					Mono<Location> monoLocation = locationService.save(location);
					monoLocation.subscribe();
				}
			}else {
				System.out.println("Not exists location with WOEID="+woeid);
			}
			}catch(Exception e ) {
				System.out.println("Error updating location: " + e.getMessage());
			}
		}
		
		deleteLocationsNoFound(init);

	}

	/**
	 * Remove locations that has could not found by woeid
	 * @param init	
	 */
	private void deleteLocationsNoFound(Date init) {
		Flux<Location> locationsFlux = locationService.findAll();
		List<Location> locations = locationsFlux.collectList().block();
		if (locations != null) {
			for (Location location : locations) {
				if (!location.getEnabled() && !location.getCreateAt().after(init)) {
					System.out.println("Se elimina la location " + location.getName() + " (" + location.getWoeid() + ")");
					Mono<Void> del = locationService.deleteById(location.getId());
					del.block();
				}
			}
		}
	}

	private String getQueryByWoeid(List<Location> locations) {
		String separator = "%2C%20";
		String listString = "";
		for (Location location : locations) {
			listString += location.getWoeid() + separator;
		}
		listString = listString.substring(0, listString.length() - separator.length());
		return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20("+listString+")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
	}
	
	
	private String retrieveRSS(String serviceUrl) throws IOException
	{
		URL url = new URL(serviceUrl);
		URLConnection connection = url.openConnection(Proxy.NO_PROXY);
		InputStream is = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(is);
		StringWriter writer = new StringWriter();
		copy(reader, writer);
		reader.close();
		is.close();

		return writer.toString();
	}
	
	private static long copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[1024];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}

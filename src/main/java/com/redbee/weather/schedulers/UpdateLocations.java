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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.service.ILocationService;
import com.redbee.weather.service.IUsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Transactional(readOnly=false)
public class UpdateLocations {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUsuarioService usuarioService;
	
	@Scheduled(fixedRate = 60000)
	public void updateLocations() throws IOException, JSONException {

		Flux<Location> locationsFlux = locationService.findAll();
		List<Location> locations = locationsFlux.collectList().block();
		
		String query = getQueryByWoeid(locations);
		String resultado = retrieveRSS(query);
		
		JSONObject jsonObj = new JSONObject(resultado);
		JSONArray jsonRoot = jsonObj.getJSONObject("query").getJSONObject("results").getJSONArray("channel");
		for (int i = 0; i < jsonRoot.length(); i++) {
			try {
			JSONObject root = jsonRoot.getJSONObject(i);
			String temperatureUnit = root.getJSONObject("units").get("temperature").toString();
			String nombre = root.getJSONObject("location").get("city").toString();
			String chill = root.getJSONObject("wind").get("chill").toString();
			String humidity = root.getJSONObject("atmosphere").get("humidity").toString();
			String visibility = root.getJSONObject("atmosphere").get("visibility").toString();
			String temperature = root.getJSONObject("item").getJSONObject("condition").get("temp").toString();
			String text = root.getJSONObject("item").getJSONObject("condition").get("text").toString();
			String pubDate = root.getJSONObject("item").get("pubDate").toString();
			
			String woeid = root.getJSONObject("item").get("link").toString().split("/")[root.getJSONObject("item").get("link").toString().split("/").length-1].replace("city-", "");

//			System.out.println("woeid: " + woeid);
//			System.out.println("\tLocation: " + nombre);
//			System.out.println("\tPublication date: " + pubDate);
//			System.out.println("\tChill: " + chill);
//			System.out.println("\tHumidity: " + humidity);
//			System.out.println("\tVisibility: " + visibility);
//			System.out.println("\tTemperature: " + temperature);
//			System.out.println("\tText: " + text);
			
			Location location = locationService.findByWoeid(woeid).block();
			if (location != null) {
				if (pubDate != location.getPubDate()) {
					location.setNombre(nombre);
					location.setPubDate(pubDate);
					location.setChill(chill);
					location.setHumidity(humidity);
					location.setVisibility(visibility);
					if ("F".equals(temperatureUnit)) {
						location.setTemperature(String.valueOf(Integer.valueOf(temperature)-32));
					}else {
						location.setTemperature(temperature );
					}
					location.setText(text);
					
					Mono<Location> monoLocation = locationService.save(location);
					monoLocation.subscribe();
				}
			}else {
				System.out.println("NO EXISTE LOCATION CON WOEID="+woeid);
			}
			}catch(Exception e ) {
				System.out.println("Error al actualizar location: " + e.getMessage());
			}
		}
//		for (Location location : locations) {
//			String woeid = location.getWoeid();
//			String query = getQueryByWoeid(woeid);
//			String resultado = retrieveRSS(query);
//			
//			JSONObject jsonObj = new JSONObject(resultado);
//			JSONObject jsonRoot = jsonObj.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
//			String temperatureUnit = jsonRoot.getJSONObject("units").get("temperature").toString();
//			String nombre = jsonRoot.getJSONObject("location").get("city").toString();
//			String chill = jsonRoot.getJSONObject("wind").get("chill").toString();
//			String humidity = jsonRoot.getJSONObject("atmosphere").get("humidity").toString();
//			String visibility = jsonRoot.getJSONObject("atmosphere").get("visibility").toString();
//			String temperature = jsonRoot.getJSONObject("item").getJSONObject("condition").get("temp").toString();
//			String text = jsonRoot.getJSONObject("item").getJSONObject("condition").get("text").toString();
//			String pubDate = jsonRoot.getJSONObject("item").get("pubDate").toString();
//			
//			System.out.println("\tLocation: " + nombre);
//			System.out.println("\tPublication date: " + pubDate);
//			System.out.println("\tChill: " + chill);
//			System.out.println("\tHumidity: " + humidity);
//			System.out.println("\tVisibility: " + visibility);
//			System.out.println("\tTemperature: " + temperature);
//			System.out.println("\tText: " + text);
//			
//			if (pubDate != location.getPubDate()) {
//				location.setNombre(nombre);
//				location.setPubDate(pubDate);
//				location.setChill(chill);
//				location.setHumidity(humidity);
//				location.setVisibility(visibility);
//				if ("F".equals(temperatureUnit)) {
//					location.setTemperature(String.valueOf(Integer.valueOf(temperature)-32));
//				}else {
//					location.setTemperature(temperature );
//				}
//				location.setText(text);
//				
//				Mono<Location> monoLocation = locationService.save(location);
//				monoLocation.subscribe();
//			}
//
//		}

	}
	
//	private String getQueryByWoeid(String woeid) {
//		return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%20"+woeid+"&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
//	}

	/**
	 * Ejemplo: id,id2... --> 201 %2C%20 2332
	 * @param woeidList
	 * @return
	 */
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

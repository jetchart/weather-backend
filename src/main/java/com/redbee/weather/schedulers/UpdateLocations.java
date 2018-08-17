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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.service.ILocationService;

import reactor.core.publisher.Flux;

@Component
public class UpdateLocations {

	@Autowired
	ILocationService locationService;
	
	@Scheduled(fixedRate = 10000)
	@Transactional
	public void updateLocations() throws IOException {
		Flux<Location> locationsFlux = locationService.findAll();
		List<Location> locations = locationsFlux.collectList().block();
		
		for (Location location : locations) {
			
			System.out.println("ADENTRO");
			String woeid = location.getWoeid();
			
			String query = getQueryByWoeid(woeid);
			String resultado;
			resultado = retrieveRSS(query);
			JSONObject jsonObj = new JSONObject(resultado);
			JSONObject jsonRoot = jsonObj.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
			String chill = jsonRoot.getJSONObject("wind").get("chill").toString();
			String humidity = jsonRoot.getJSONObject("atmosphere").get("humidity").toString();
			String visibility = jsonRoot.getJSONObject("atmosphere").get("visibility").toString();
			String temperature = jsonRoot.getJSONObject("item").getJSONObject("condition").get("temp").toString();
			String text = jsonRoot.getJSONObject("item").getJSONObject("condition").get("text").toString();
			
			System.out.println("\tLocation: " + jsonRoot.getJSONObject("location").get("city"));
			System.out.println("\tChill: " + chill);
			System.out.println("\tHumidity: " + humidity);
			System.out.println("\tVisibility: " + visibility);
			System.out.println("\tTemperature: " + temperature);
			System.out.println("\tText: " + text);
			
			location.setChill(chill);
			location.setHumidity(humidity);
			location.setVisibility(visibility);
			location.setTemperature(temperature);
			location.setText(text);

		}
//		Collection<String> woeids = new ArrayList<String>();
//		woeids.add("467005");
//		woeids.add("24553135");
		
//		for (String woeid : woeids) {
//			System.out.println("************************************");
//			System.out.println("WOEID:" + woeids);
//			System.out.println("************************************");
//			String query = getQueryByWoeid(woeid);
//			String resultado = retrieveRSS(query);
//			JSONObject jsonObj = new JSONObject(resultado);
//			JSONObject jsonRoot = jsonObj.getJSONObject("query")
//		    	.getJSONObject("results")
//		    	.getJSONObject("channel");	
//			System.out.println("\tLocation: " + jsonRoot.getJSONObject("location").get("city"));
//			System.out.println("\tChill: " + jsonRoot.getJSONObject("wind").get("chill"));
//			System.out.println("\tHumidity: " + jsonRoot.getJSONObject("atmosphere").get("humidity"));
//			System.out.println("\tVvisibility: " + jsonRoot.getJSONObject("atmosphere").get("visibility"));
//			System.out.println("\tTemperature: " + jsonRoot.getJSONObject("item").getJSONObject("condition").get("temp"));
//			System.out.println("\tText: " + jsonRoot.getJSONObject("item").getJSONObject("condition").get("text"));
//		}
		
	}
	
	private String getQueryByWoeid(String woeid) {
		return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%20"+woeid+"&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
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

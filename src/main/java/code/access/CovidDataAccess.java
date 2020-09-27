package code.access;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import code.entity.Country;
import code.entity.Global;
import lombok.var;

@Component
public class CovidDataAccess {

	private String countryURL = "https://corona.lmao.ninja/v2/countries";
	private String globalURL = "https://corona.lmao.ninja/v2/all";

	private ObjectMapper mapper = new ObjectMapper();
	private DecimalFormat decimal = new DecimalFormat("#.##");

	public String getResponse(String link) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(link);
			var connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			Scanner scan = new Scanner(connection.getInputStream());

			while (scan.hasNextLine())
				response.append(scan.nextLine());
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Country> getCountries() {
		var countries = new ArrayList<Country>();
		List<Map<String, Object>> response = null;
		var json = getResponse(countryURL);
		try {
			response = mapper.readValue(json, List.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// coverting from Map to Country object
		response.stream().forEach(map -> {
			Country country = mapper.convertValue(map, Country.class);
			countries.add(country);
		});
		return countries.stream()
				.sorted(Comparator.comparing(Country::getCases).reversed())
				.collect(Collectors.toList());
	}

	public Country getCountry(String country) {
		try {
			var json = getResponse(countryURL + "/" + country);
			return mapper.readValue(json, Country.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getCountryFlagURL(String country) {
		return (String) getCountry(country).getCountryInfo().get("flag");
	}

	@SuppressWarnings("unchecked")
	public Global getGlobalData() {
		var json = getResponse(globalURL);
		try {
			Map<String, Object> map = mapper.readValue(json, Map.class);
			return Global.builder()
					.affectedCountries((Integer) map.get("affectedCountries"))
					.cases((Integer) map.get("cases"))
					.deaths((Integer) map.get("deaths"))
					.recovered((Integer) map.get("recovered"))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double getDeathRate() {
		try {
			double cases = getGlobalData().getCases();
			double deaths = getGlobalData().getDeaths();
			double result = (deaths * 100) / cases;
			return Double.valueOf(decimal.format(result));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
package code.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import code.access.CovidDataAccess;

@Controller
public class RootResponse {

	@Autowired private CovidDataAccess dataAccess;

	@GetMapping("/")
	public String bootUp(Model web) {
		try {
			web.addAttribute("global", dataAccess.getGlobalData());
			web.addAttribute("data", dataAccess.getCountries());
			web.addAttribute("deathRate", dataAccess.getDeathRate());
			return "root.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/application-down.html";
		}
	}

	@PostMapping("/country")
	public String searchCountry(@RequestParam String country, Model web) {
		try {
			web.addAttribute("country", dataAccess.getCountry(country));
			web.addAttribute("flagURL", dataAccess.getCountryFlagURL(country));
			return "details.html";
		} catch (Exception e) {
			return "error/invalid-country.html";
		}
	}

	@GetMapping("/details/{country}")
	public String getMethodName(@PathVariable String country, Model web) {
		web.addAttribute("country", dataAccess.getCountry(country));
		web.addAttribute("flagURL", dataAccess.getCountryFlagURL(country));
		return "details.html";
	}
	
}
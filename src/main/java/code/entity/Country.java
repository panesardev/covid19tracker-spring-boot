package code.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Country {

	private String country;
	private Map<String, Object> countryInfo;
	private Long updated;
	private Integer cases;
	private Integer todayCases;
	private Integer deaths;
	private Integer todayDeaths;
	private Integer recovered;
	private Integer active;
	private Integer critical;
	private Integer casesPerOneMillion;
	private Integer deathsPerOneMillion;
	private String continent;
	private Integer tests;
	private Integer testsPerOneMillion;

}
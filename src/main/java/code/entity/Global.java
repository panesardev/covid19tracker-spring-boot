package code.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Global {

	private Integer cases;
	private Integer todayCases;
	private Integer deaths;
	private Integer todayDeaths;
	private Integer recovered;
	private Integer active;
	private Integer critical;
	private Integer casesPerOneMillion;
	private Integer deathsPerOneMillion;
	private Long updated;
	private Integer tests;
	private String continent;
	private Integer testsPerOneMillion;
	private Integer affectedCountries;

}
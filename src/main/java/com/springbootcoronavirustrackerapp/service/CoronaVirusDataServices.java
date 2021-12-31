package com.springbootcoronavirustrackerapp.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springbootcoronavirustrackerapp.model.LocationStat;

@Service
public class CoronaVirusDataServices {
	private static String virus_Data_Url ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStat> allStats = new ArrayList<>();
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		List<com.springbootcoronavirustrackerapp.model.LocationStat> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(virus_Data_Url))
		.build();
		HttpResponse<String>httpResponse=  client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvbodyReader =new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyReader);
		for (CSVRecord record : records) {
			LocationStat locationStat = new LocationStat();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size()-1));
			int PreviousCases = Integer.parseInt(record.get(record.size()-2));
			locationStat.setTotalLatestCases(latestCases);
			locationStat.setDiffrenceFromPreviousDate(latestCases - PreviousCases);
			newStats.add(locationStat);
		
		    
		}
		   this.allStats= newStats;
	}
	public static String getVirus_Data_Url() {
		return virus_Data_Url;
	}
	public static void setVirus_Data_Url(String virus_Data_Url) {
		CoronaVirusDataServices.virus_Data_Url = virus_Data_Url;
	}
	public List<LocationStat> getAllStats() {
		return allStats;
	}
	public void setAllStats(List<LocationStat> allStats) {
		this.allStats = allStats;
	}
	
}

package com.springbootcoronavirustrackerapp.controller;

import java.lang.Thread.State;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootcoronavirustrackerapp.model.LocationStat;
import com.springbootcoronavirustrackerapp.service.CoronaVirusDataServices;

@Controller
public class DefaultPage {
	@Autowired
	CoronaVirusDataServices coronaVirusDataServices;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List <LocationStat> allStats = coronaVirusDataServices.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getTotalLatestCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffrenceFromPreviousDate()).sum();
		model.addAttribute("LocationStat",allStats);
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("totalNewCases",totalNewCases);
		
		return "home";
	}
}

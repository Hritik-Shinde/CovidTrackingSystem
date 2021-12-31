package com.springbootcoronavirustrackerapp.model;

public class LocationStat {
	
	private String state;
	private String country;
	private int totalLatestCases;
	private int diffrenceFromPreviousDate;
	public int getDiffrenceFromPreviousDate() {
		return diffrenceFromPreviousDate;
	}
	public void setDiffrenceFromPreviousDate(int diffrenceFromPreviousDate) {
		this.diffrenceFromPreviousDate = diffrenceFromPreviousDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalLatestCases() {
		return totalLatestCases;
	}
	public void setTotalLatestCases(int totalLatestCases) {
		this.totalLatestCases = totalLatestCases;
	}
	@Override
	public String toString() {
		return "LocationStat [state=" + state + ", country=" + country + ", totalLatestCases=" + totalLatestCases + "]";
	}
	
	
}

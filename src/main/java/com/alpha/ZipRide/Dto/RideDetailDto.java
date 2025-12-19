package com.alpha.ZipRide.Dto;

public class RideDetailDto {

	private String fromlocation;
	private String tolocation;
	private int distance;
	private int fare;
	
	public String getFromlocation() {
		return fromlocation;
	}
	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}
	public String getTolocation() {
		return tolocation;
	}
	public void setTolocation(String tolocation) {
		this.tolocation = tolocation;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	public RideDetailDto(String fromlocation, String tolocation, int distance, int fare) {
		super();
		this.fromlocation = fromlocation;
		this.tolocation = tolocation;
		this.distance = distance;
		this.fare = fare;
	}
	
	public RideDetailDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "RideDetailDto [fromlocation=" + fromlocation + ", tolocation=" + tolocation + ", distance=" + distance
				+ ", fare=" + fare + "]";
	}

}

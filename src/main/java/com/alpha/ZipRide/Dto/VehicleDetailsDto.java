package com.alpha.ZipRide.Dto;

import com.alpha.ZipRide.Entity.Vehicle;

public class VehicleDetailsDto {
	private Vehicle v;
	private int fare;
	private int estimatedtime;
	public Vehicle getV() {
		return v;
	}
	public void setV(Vehicle v) {
		this.v = v;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getEstimatedtime() {
		return estimatedtime;
	}
	public void setEstimatedtime(int estimatedtime) {
		this.estimatedtime = estimatedtime;
	}
	public VehicleDetailsDto(Vehicle v, int fare, int estimatedtime) {
		super();
		this.v = v;
		this.fare = fare;
		this.estimatedtime = estimatedtime;
	}
	public VehicleDetailsDto() {
		super();
	}
	@Override
	public String toString() {
		return "VehicleDetailsDto [v=" + v + ", fare=" + fare + ", estimatedtime=" + estimatedtime + "]";
	}
	
	

}

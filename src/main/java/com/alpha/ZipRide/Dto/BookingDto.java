package com.alpha.ZipRide.Dto;

import com.alpha.ZipRide.Entity.Vehicle;

import jakarta.persistence.OneToOne;

public class BookingDto {


	private int vid;
	private String sourceloction;
	private String destinationlocation;
	private int fare;
	private int distancetravelled;
	private int estimatedtime;
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getSourceloction() {
		return sourceloction;
	}
	public void setSourceloction(String sourceloction) {
		this.sourceloction = sourceloction;
	}
	public String getDestinationlocation() {
		return destinationlocation;
	}
	public void setDestinationlocation(String destinationlocation) {
		this.destinationlocation = destinationlocation;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getDistancetravelled() {
		return distancetravelled;
	}
	public void setDistancetravelled(int distancetravelled) {
		this.distancetravelled = distancetravelled;
	}
	public int getEstimatedtime() {
		return estimatedtime;
	}
	public void setEstimatedtime(int estimatedtime) {
		this.estimatedtime = estimatedtime;
	}
	public BookingDto(int vid, String sourceloction, String destinationlocation, int fare, int distancetravelled,
			int estimatedtime) {
		super();
		this.vid = vid;
		this.sourceloction = sourceloction;
		this.destinationlocation = destinationlocation;
		this.fare = fare;
		this.distancetravelled = distancetravelled;
		this.estimatedtime = estimatedtime;
	}
	public BookingDto() {
		super();
	}
	@Override
	public String toString() {
		return "BookingDto [vid=" + vid + ", sourceloction=" + sourceloction + ", destinationlocation="
				+ destinationlocation + ", fare=" + fare + ", distancetravelled=" + distancetravelled
				+ ", estimatedtime=" + estimatedtime + "]";
	}
	
}

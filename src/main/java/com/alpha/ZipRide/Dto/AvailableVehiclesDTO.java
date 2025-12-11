package com.alpha.ZipRide.Dto;

import java.util.List;

import com.alpha.ZipRide.Entity.Customer;

public class AvailableVehiclesDTO {

	  private Customer c;                   
	    private double distance;              
	    private String sourceLocation; 
	    private String destination;           
	    private List<VehicleDetailsDto> availableVehicles;
		public Customer getC() {
			return c;
		}
		public void setC(Customer c) {
			this.c = c;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}
		public String getSourceLocation() {
			return sourceLocation;
		}
		public void setSourceLocation(String sourceLocation) {
			this.sourceLocation = sourceLocation;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public List<VehicleDetailsDto> getAvailableVehicles() {
			return availableVehicles;
		}
		public void setAvailableVehicles(List<VehicleDetailsDto> availableVehicles) {
			this.availableVehicles = availableVehicles;
		}
		public AvailableVehiclesDTO(Customer c, double distance, String sourceLocation, String destination,
				List<VehicleDetailsDto> availableVehicles) {
			super();
			this.c = c;
			this.distance = distance;
			this.sourceLocation = sourceLocation;
			this.destination = destination;
			this.availableVehicles = availableVehicles;
		}
		public AvailableVehiclesDTO() {
			super();
		}
		@Override
		public String toString() {
			return "AvailableVehiclesDTO [c=" + c + ", distance=" + distance + ", sourceLocation=" + sourceLocation
					+ ", destination=" + destination + ", availableVehicles=" + availableVehicles + "]";
		}
	    
	    
	    
}
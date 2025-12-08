package com.alpha.ZipRide.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle {

	@Id
	private int vid;
	private String vehiclename;
	private int vehicleno;
	private String vehicletype;
	private String vehiclemodel;
	private int vehiclecapacity;
	private String vehiclecurrencity;
	private String vehicleavailability_status="Available";
	private int priceperkm;
	
	@OneToOne
	@MapsId
	@JsonIgnore
	@JoinColumn(name="did")
	private Driver driver;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVehiclename() {
		return vehiclename;
	}

	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}

	public int getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(int vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getVehiclemodel() {
		return vehiclemodel;
	}

	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}

	public int getVehiclecapacity() {
		return vehiclecapacity;
	}

	public void setVehiclecapacity(int vehiclecapacity) {
		this.vehiclecapacity = vehiclecapacity;
	}

	public String getVehiclecurrencity() {
		return vehiclecurrencity;
	}

	public void setVehiclecurrencity(String vehiclecurrencity) {
		this.vehiclecurrencity = vehiclecurrencity;
	}

	public String getVehicleavailability_status() {
		return vehicleavailability_status;
	}

	public void setVehicleavailability_status(String vehicleavailability_status) {
		this.vehicleavailability_status = vehicleavailability_status;
	}

	public int getPriceperkm() {
		return priceperkm;
	}

	public void setPriceperkm(int priceperkm) {
		this.priceperkm = priceperkm;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicle(String vehiclename, int vehicleno, String vehicletype, String vehiclemodel, int vehiclecapacity,
			String vehiclecurrencity, String vehicleavailability_status, int priceperkm, Driver driver) {
		super();
		this.vehiclename = vehiclename;
		this.vehicleno = vehicleno;
		this.vehicletype = vehicletype;
		this.vehiclemodel = vehiclemodel;
		this.vehiclecapacity = vehiclecapacity;
		this.vehiclecurrencity = vehiclecurrencity;
		this.vehicleavailability_status = vehicleavailability_status;
		this.priceperkm = priceperkm;
		this.driver = driver;
	}

	public Vehicle() {
		super();
	}
	
	
	
	
	
}
	
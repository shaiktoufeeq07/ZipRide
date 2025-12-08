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
	private String vname;
	private int vno;
	private String vtype;
	private String vmodel;
	private int capacity;
	private String currencity;
	private String availability_status="Available";
	private int priceperkm;
	
	@OneToOne
	@MapsId
	@JsonIgnore
	@JoinColumn(name="did")
	private Driver driver;

	public Vehicle(int vid, String vname, int vno, String vtype, String vmodel, int capacity, String currencity,
			String availability_status, int priceperkm, Driver driver) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vno = vno;
		this.vtype = vtype;
		this.vmodel = vmodel;
		this.capacity = capacity;
		this.currencity = currencity;
		this.availability_status = availability_status;
		this.priceperkm = priceperkm;
		this.driver = driver;
	}

	public Vehicle() {
		super();
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getVmodel() {
		return vmodel;
	}

	public void setVmodel(String vmodel) {
		this.vmodel = vmodel;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCurrencity() {
		return currencity;
	}

	public void setCurrencity(String currencity) {
		this.currencity = currencity;
	}

	public String getAvailability_status() {
		return availability_status;
	}

	public void setAvailability_status(String availability_status) {
		this.availability_status = availability_status;
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
	
	
	
}

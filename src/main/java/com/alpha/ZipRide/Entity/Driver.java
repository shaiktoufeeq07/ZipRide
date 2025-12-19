package com.alpha.ZipRide.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int did;
private int licenseno;
private String upiid;
private String drivername;
private String driverstatus;
private int age;
private long drivermobileno;
private String drivergender;
private String drivermail;

@OneToOne(mappedBy = "driver", cascade =  CascadeType.ALL,orphanRemoval = true)

private Vehicle vehicle;

@OneToMany(mappedBy = "driver")
@JsonIgnore
private List<Booking> blist;

public int getDid() {
	return did;
}

public void setDid(int did) {
	this.did = did;
}

public int getLicenseno() {
	return licenseno;
}

public void setLicenseno(int licenseno) {
	this.licenseno = licenseno;
}

public String getUpiid() {
	return upiid;
}

public void setUpiid(String upiid) {
	this.upiid = upiid;
}

public String getDrivername() {
	return drivername;
}

public void setDrivername(String drivername) {
	this.drivername = drivername;
}

public String getDriverstatus() {
	return driverstatus;
}

public void setDriverstatus(String driverstatus) {
	this.driverstatus = driverstatus;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public long getDrivermobileno() {
	return drivermobileno;
}

public void setDrivermobileno(long drivermobileno) {
	this.drivermobileno = drivermobileno;
}

public String getDrivergender() {
	return drivergender;
}

public void setDrivergender(String drivergender) {
	this.drivergender = drivergender;
}

public String getDrivermail() {
	return drivermail;
}

public void setDrivermail(String drivermail) {
	this.drivermail = drivermail;
}

public Vehicle getVehicle() {
	return vehicle;
}

public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
}

public List<Booking> getBlist() {
	return blist;
}

public void setBlist(List<Booking> blist) {
	this.blist = blist;
}

public Driver(int licenseno, String upiid, String drivername, String driverstatus, int age, long drivermobileno,
		String drivergender, String drivermail, Vehicle vehicle, List<Booking> blist) {
	super();
	this.licenseno = licenseno;
	this.upiid = upiid;
	this.drivername = drivername;
	this.driverstatus = driverstatus;
	this.age = age;
	this.drivermobileno = drivermobileno;
	this.drivergender = drivergender;
	this.drivermail = drivermail;
	this.vehicle = vehicle;
	this.blist = blist;
}

public Driver() {
	super();
}

@Override
public String toString() {
	return "Driver [did=" + did + ", licenseno=" + licenseno + ", upiid=" + upiid + ", drivername=" + drivername
			+ ", driverstatus=" + driverstatus + ", age=" + age + ", drivermobileno=" + drivermobileno
			+ ", drivergender=" + drivergender + ", drivermail=" + drivermail + ", vehicle=" + vehicle + ", blist="
			+ blist + "]";
}


}
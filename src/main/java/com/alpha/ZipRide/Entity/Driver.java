package com.alpha.ZipRide.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int did;
private int licenseno;
private String upiid;
private String dname;
private String status;
private int age;
private long mobno;
private String gender;
private String mail;

@OneToOne(mappedBy = "driver", cascade =  CascadeType.ALL,orphanRemoval = true)

private Vehicle vehicle;

public Driver(int licenseno, String upiid, String dname, String status, int age, long mobno, String gender, String mail,
		Vehicle vehicle) {
	super();
	this.licenseno = licenseno;
	this.upiid = upiid;
	this.dname = dname;
	this.status = status;
	this.age = age;
	this.mobno = mobno;
	this.gender = gender;
	this.mail = mail;
	this.vehicle = vehicle;
}

public Driver() {
	super();
}

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

public String getDname() {
	return dname;
}

public void setDname(String dname) {
	this.dname = dname;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public long getMobno() {
	return mobno;
}

public void setMobno(long mobno) {
	this.mobno = mobno;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public Vehicle getVehicle() {
	return vehicle;
}

public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
}


}

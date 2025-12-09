package com.alpha.ZipRide.Entity;

import java.awt.print.Book;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerid;
	private String customername;
	private int customerage;
	private String customergender;
	private long customermobileno;
	private String email;
	private String currentlocation;
	
	@OneToMany
	private List<Booking> blist;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public int getCustomerage() {
		return customerage;
	}

	public void setCustomerage(int customerage) {
		this.customerage = customerage;
	}

	public String getCustomergender() {
		return customergender;
	}

	public void setCustomergender(String customergender) {
		this.customergender = customergender;
	}

	public long getCustomermobileno() {
		return customermobileno;
	}

	public void setCustomermobileno(long customermobileno) {
		this.customermobileno = customermobileno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentlocation() {
		return currentlocation;
	}

	public void setCurrentlocation(String currentlocation) {
		this.currentlocation = currentlocation;
	}

	public List<Booking> getBlist() {
		return blist;
	}

	public void setBlist(List<Booking> blist) {
		this.blist = blist;
	}

	public Customer(int customerid, String customername, int customerage, String customergender, long customermobileno,
			String email, String currentlocation, List<Booking> blist) {
		super();
		this.customerid = customerid;
		this.customername = customername;
		this.customerage = customerage;
		this.customergender = customergender;
		this.customermobileno = customermobileno;
		this.email = email;
		this.currentlocation = currentlocation;
		this.blist = blist;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername=" + customername + ", customerage=" + customerage
				+ ", customergender=" + customergender + ", customermobileno=" + customermobileno + ", email=" + email
				+ ", currentlocation=" + currentlocation + ", blist=" + blist + "]";
	}


}

package com.alpha.ZipRide.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
@Id
@GeneratedValue (strategy = GenerationType.AUTO)
	private int bookingid;
	@ManyToOne
	private Customer customer;
	
	@OneToOne
	private Driver driver;
	
	private String sourceloction;
	private String destinationlocation;
	private int fare;
	private int estimatedtime;
	
//	private Payment payment;
	
}

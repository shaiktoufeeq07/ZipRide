package com.alpha.ZipRide.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
@Entity
public class Booking {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
		private int bookingid;
	
		@ManyToOne
		private Customer customer;
		
		@ManyToOne
		@JsonIgnore
		private Vehicle vehicle;
		
		private String sourceloction;
		private String destinationlocation;
		private int fare;
		private int distancetravelled;
		private int estimatedtime;
		
		@Column(updatable = false)
		private Date bookingdate;
		
		@PrePersist
	    protected void onCreate() {
	        this.bookingdate = new Date(System.currentTimeMillis());
	    }
		
		private String paymentstatus="NOT PAID";
		
		@OneToOne
		private Payment payment;
		private String bookingstatus="PENDING";
		
		public int getBookingid() {
			return bookingid;
		}
		public void setBookingid(int bookingid) {
			this.bookingid = bookingid;
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		public Vehicle getVehicle() {
			return vehicle;
		}
		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
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
		public Date getBookingdate() {
			return bookingdate;
		}
		public void setBookingdate(Date bookingdate) {
			this.bookingdate = bookingdate;
		}
		public String getPaymentstatus() {
			return paymentstatus;
		}
		public void setPaymentstatus(String paymentstatus) {
			this.paymentstatus = paymentstatus;
		}
		public Payment getPayment() {
			return payment;
		}
		public void setPayment(Payment payment) {
			this.payment = payment;
		}
		public String getBookingstatus() {
			return bookingstatus;
		}
		public void setBookingstatus(String bookingstatus) {
			this.bookingstatus = bookingstatus;
		}
		
		public Booking(Customer customer, Vehicle vehicle, String sourceloction, String destinationlocation, int fare,
				int distancetravelled, int estimatedtime, Date bookingdate, String paymentstatus, Payment payment,
				String bookingstatus) {
			super();
			this.customer = customer;
			this.vehicle = vehicle;
			this.sourceloction = sourceloction;
			this.destinationlocation = destinationlocation;
			this.fare = fare;
			this.distancetravelled = distancetravelled;
			this.estimatedtime = estimatedtime;
			this.bookingdate = bookingdate;
			this.paymentstatus = paymentstatus;
			this.payment = payment;
			this.bookingstatus = bookingstatus;
		}
		
		public Booking() {
			super();
		}
		
		@Override
		public String toString() {
			return "Booking [bookingid=" + bookingid + ", customer=" + customer + ", vehicle=" + vehicle
					+ ", sourceloction=" + sourceloction + ", destinationlocation=" + destinationlocation + ", fare="
					+ fare + ", distancetravelled=" + distancetravelled + ", estimatedtime=" + estimatedtime
					+ ", bookingdate=" + bookingdate + ", paymentstatus=" + paymentstatus + ", payment=" + payment
					+ ", bookingstatus=" + bookingstatus + "]";
		}
	}
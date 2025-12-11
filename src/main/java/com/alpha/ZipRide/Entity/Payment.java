package com.alpha.ZipRide.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentid;
	
	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "vid")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "bookingid")
	private Booking booking;
	
	private int amount;
	private String paymenttype;
	
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
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
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	
	public Payment(Customer customer, Vehicle vehicle, Booking booking, int amount, String paymenttype) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.booking = booking;
		this.amount = amount;
		this.paymenttype = paymenttype;
	}
	
	public Payment() {
		super();
	}
	
	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ", customer=" + customer + ", vehicle=" + vehicle + ", booking="
				+ booking + ", amount=" + amount + ", paymenttype=" + paymenttype + "]";
	}

}

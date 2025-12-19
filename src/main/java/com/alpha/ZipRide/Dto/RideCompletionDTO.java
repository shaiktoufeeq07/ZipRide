package com.alpha.ZipRide.Dto;

import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Entity.Payment;
import com.alpha.ZipRide.Entity.Vehicle;

public class RideCompletionDTO {

	    private Booking booking;
	    private Payment payment;
	    private Vehicle vehicle;
	    private Customer customer;

	    public Booking getBooking() {
	        return booking;
	    }

	    public void setBooking(Booking booking) {
	        this.booking = booking;
	    }

	    public Payment getPayment() {
	        return payment;
	    }

	    public void setPayment(Payment payment) {
	        this.payment = payment;
	    }

	    public Vehicle getVehicle() {
	        return vehicle;
	    }

	    public void setVehicle(Vehicle vehicle) {
	        this.vehicle = vehicle;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }
}

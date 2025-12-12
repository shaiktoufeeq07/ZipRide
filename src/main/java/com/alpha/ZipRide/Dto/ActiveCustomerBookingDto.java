package com.alpha.ZipRide.Dto;

import com.alpha.ZipRide.Entity.Booking;

public class ActiveCustomerBookingDto {

	
	 private String customername;
	    private long customermobno;
	    private Booking booking;
	    private String customercurrentlocation;

	    public ActiveCustomerBookingDto() {}

	    public ActiveCustomerBookingDto(String customername, long customermobno, Booking booking,
	                                    String customercurrentlocation) {
	        this.customername = customername;
	        this.customermobno = customermobno;
	        this.booking = booking;
	        this.customercurrentlocation = customercurrentlocation;
	    }

	    public String getCustomername() {
	        return customername;
	    }

	    public void setCustomername(String customername) {
	        this.customername = customername;
	    }

	    public long getCustomermobno() {
	        return customermobno;
	    }

	    public void setCustomermobno(long customermobno) {
	        this.customermobno = customermobno;
	    }

	    public Booking getBooking() {
	        return booking;
	    }

	    public void setBooking(Booking booking) {
	        this.booking = booking;
	    }

	    public String getCustomercurrentlocation() {
	        return customercurrentlocation;
	    }

	    public void setCustomercurrentlocation(String customercurrentlocation) {
	        this.customercurrentlocation = customercurrentlocation;
	    }
	}


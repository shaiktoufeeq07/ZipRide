package com.alpha.ZipRide.Dto;

import java.util.List;

public class BookingHistoryDto {

	List<RideDetailDto> history;
	double totalamount;
	
	public List<RideDetailDto> getHistory() {
		return history;
	}
	public void setHistory(List<RideDetailDto> history) {
		this.history = history;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	
	public BookingHistoryDto(List<RideDetailDto> history, double totalamount) {
		super();
		this.history = history;
		this.totalamount = totalamount;
	}
	
	public BookingHistoryDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "BookingHistoryDto [history=" + history + ", totalamount=" + totalamount + "]";
	}
	
	
}

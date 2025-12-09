package com.alpha.ZipRide.Dto;

public class CustomerDto {

	private String customername;
	private int customerage;
	private String customergender;
	private long customermobileno;
	private String email;
	private double clatitude;
	private double clongitude;
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
	public double getClatitude() {
		return clatitude;
	}
	public void setClatitude(double clatitude) {
		this.clatitude = clatitude;
	}
	public double getClongitude() {
		return clongitude;
	}
	public void setClongitude(double clongitude) {
		this.clongitude = clongitude;
	}
	public CustomerDto(String customername, int customerage, String customergender, long customermobileno, String email,
			double clatitude, double clongitude) {
		super();
		this.customername = customername;
		this.customerage = customerage;
		this.customergender = customergender;
		this.customermobileno = customermobileno;
		this.email = email;
		this.clatitude = clatitude;
		this.clongitude = clongitude;
	}
	public CustomerDto() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerDto [customername=" + customername + ", customerage=" + customerage + ", customergender="
				+ customergender + ", customermobileno=" + customermobileno + ", email=" + email + ", clatitude="
				+ clatitude + ", clongitude=" + clongitude + "]";
	}

	
	
}

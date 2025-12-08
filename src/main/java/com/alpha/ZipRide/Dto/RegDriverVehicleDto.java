package com.alpha.ZipRide.Dto;

public class RegDriverVehicleDto {
	//Driver Details
	private String drivername;
	private int licenseno;
	private String upiid;
	private int age;
	private long drivermobileno;
	private String drivergender;
	private String drivermail;
	
	
	//Vehicle Detalils
	private String vehiclename;
	private int vehicleno;
	private String vehicletype;
	private String vehiclemodel;
	private int vehiclecapacity;
	private int priceperkm;
	
	//initial location details
	private double latitude;
	private double longitude;
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
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
	public String getVehiclename() {
		return vehiclename;
	}
	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}
	public int getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(int vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public int getVehiclecapacity() {
		return vehiclecapacity;
	}
	public void setVehiclecapacity(int vehiclecapacity) {
		this.vehiclecapacity = vehiclecapacity;
	}
	public int getPriceperkm() {
		return priceperkm;
	}
	public void setPriceperkm(int priceperkm) {
		this.priceperkm = priceperkm;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public RegDriverVehicleDto(String drivername, int licenseno, String upiid, int age, long drivermobileno,
			String drivergender, String drivermail, String vehiclename, int vehicleno, String vehicletype,
			String vehiclemodel, int vehiclecapacity, int priceperkm, double latitude, double longitude) {
		super();
		this.drivername = drivername;
		this.licenseno = licenseno;
		this.upiid = upiid;
		this.age =age;
		this.drivermobileno = drivermobileno;
		this.drivergender = drivergender;
		this.drivermail = drivermail;
		this.vehiclename = vehiclename;
		this.vehicleno = vehicleno;
		this.vehicletype = vehicletype;
		this.vehiclemodel = vehiclemodel;
		this.vehiclecapacity = vehiclecapacity;
		this.priceperkm = priceperkm;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public RegDriverVehicleDto() {
		super();
	}
	@Override
	public String toString() {
		return "RegDriverVehicleDto [drivername=" + drivername + ", licenseno=" + licenseno + ", upiid=" + upiid
				+ ", driverage=" + age + ", drivermobileno=" + drivermobileno + ", drivergender=" + drivergender
				+ ", drivermail=" + drivermail + ", vehiclename=" + vehiclename + ", vehicleno=" + vehicleno
				+ ", vehicletype=" + vehicletype + ", vehiclemodel=" + vehiclemodel + ", vehiclecapacity="
				+ vehiclecapacity + ", priceperkm=" + priceperkm + ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
	
	
}
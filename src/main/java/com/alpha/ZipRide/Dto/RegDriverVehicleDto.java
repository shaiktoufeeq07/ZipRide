package com.alpha.ZipRide.Dto;

public class RegDriverVehicleDto {
	//Driver Details
	private String dname;
	private int licenseno;
	private String upiid;
	private int age;
	private long mobno;
	private String gender;
	private String mail;
	
	
	//Vehicle Detalils
	private String vname;
	private int vno;
	private String vtype;
	private String vmodel;
	private int capacity;
	private int priceperkm;
	
	//initial location details
	private double latitude;
	private double longitude;
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
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
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public int getVno() {
		return vno;
	}
	public void setVno(int vno) {
		this.vno = vno;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getVmodel() {
		return vmodel;
	}
	public void setVmodel(String vmodel) {
		this.vmodel = vmodel;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
	public RegDriverVehicleDto(String dname, int licenseno, String upiid, int age, long mobno, String gender,
			String mail, String vname, int vno, String vtype, String vmodel, int capacity, int priceperkm,
			double latitude, double longitude) {
		super();
		this.dname = dname;
		this.licenseno = licenseno;
		this.upiid = upiid;
		this.age = age;
		this.mobno = mobno;
		this.gender = gender;
		this.mail = mail;
		this.vname = vname;
		this.vno = vno;
		this.vtype = vtype;
		this.vmodel = vmodel;
		this.capacity = capacity;
		this.priceperkm = priceperkm;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public RegDriverVehicleDto() {
		super();
	}
	@Override
	public String toString() {
		return "RegDriverVehicleDto [dname=" + dname + ", licenseno=" + licenseno + ", upiid=" + upiid + ", age=" + age
				+ ", mobno=" + mobno + ", gender=" + gender + ", mail=" + mail + ", vname=" + vname + ", vno=" + vno
				+ ", vtype=" + vtype + ", vmodel=" + vmodel + ", capacity=" + capacity + ", priceperkm=" + priceperkm
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
}

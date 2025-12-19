package com.alpha.ZipRide.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.CityRidesExample.ResponseStructure;
import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.BookingHistoryDto;
import com.alpha.ZipRide.Dto.RegDriverVehicleDto;
import com.alpha.ZipRide.Dto.RideCompletionDTO;
import com.alpha.ZipRide.Dto.RideDetailDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Entity.Driver;
import com.alpha.ZipRide.Entity.Payment;
import com.alpha.ZipRide.Entity.Vehicle;
import com.alpha.ZipRide.Repository.BookingRepo;
import com.alpha.ZipRide.Repository.CustomerRepo;
import com.alpha.ZipRide.Repository.DriverRepo;
import com.alpha.ZipRide.Repository.PaymentRepo;
import com.alpha.ZipRide.Repository.VehicleRepo;
import com.alpha.ZipRide.exception.DriverNotFoundException;


@Service
public class DriverService {

    @Autowired
    private DriverRepo dr;
    @Autowired
    private VehicleRepo vr;
    @Autowired
    private CustomerRepo cr;
    @Autowired
    private BookingRepo br;
    @Autowired
    private PaymentRepo pr;

    
//TO GET THE CURRENT CITY
    
    public String getCityName(double latitude, double longitude) {

        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude ;

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        Map<String, Object> address = (Map<String, Object>) response.get("address");

        if (address.get("city") != null)
            return address.get("city").toString();
        else if (address.get("town") != null)
            return address.get("town").toString();
        else if (address.get("village") != null)
            return address.get("village").toString();
        else
            return "Unknown";
    }

    //SAVING THE DRIVER
    
    public Driver saveDriverDTO(RegDriverVehicleDto dto) {

        Driver d = new Driver();
        d.setLicenseno(dto.getLicenseno());
        d.setUpiid(dto.getUpiid());
        d.setDrivername(dto.getDrivername());
        d.setAge(dto.getAge());
        d.setDrivermobileno(dto.getDrivermobileno());
        d.setDrivergender(dto.getDrivergender());
        d.setDrivermail(dto.getDrivermail());
        d.setDriverstatus("Available");

        String city = getCityName(dto.getLatitude(), dto.getLongitude());

        Vehicle v = new Vehicle();
        v.setVehiclename(dto.getVehiclename());
        v.setVehicleno(dto.getVehicleno());
        v.setVehicletype(dto.getVehicletype());
        v.setVehiclemodel(dto.getVehiclemodel());
        v.setVehiclecapacity(dto.getVehiclecapacity());
        v.setVehiclecurrencity(city); 
        v.setPriceperkm(dto.getPriceperkm());

        
        v.setDriver(d);
        d.setVehicle(v);

        
        return dr.save(d);
    }
    
    //FINDING THE DRIVER 
    
    public Driver findDriverByMobile(long drivermobileno) {
        return dr.findByDrivermobileno(drivermobileno);
    }

    //deleting the driver by mobile number    
    public String deleteDriver(long drivermobileno) {

        Driver driver = dr.findByDrivermobileno(drivermobileno);
        dr.delete(driver);
		return "Deleted Successfully";
        }
    
    //UPDATING THE VEHICLES LOCATION USING DRIVER'S MOBILE NUMBER
    
	public String updateDriverLocation(long drivermobileNo, double latitude, double longitude) {
		
		
        Driver driver = dr.findByDrivermobileno(drivermobileNo);
            
       
        String city = getCityName(latitude, longitude);

       
        if (driver.getVehicle() != null) {
            Vehicle v = driver.getVehicle();
            v.setVehiclecurrencity(city);

           
            vr.save(v);
        }

        return "Driver location updated to city: " + city;

	}

	public ResponceStructure<List<Booking>> seebookinghistory(long drivermobileno) {
		
			ResponceStructure<List<Booking>> rs = new ResponceStructure<List<Booking>>();
//		    List<Booking> list = vr.findCompletedBookingsByMobile(drivermobileno);
		    List<Booking> list = vr.findCompletedBookingsByDriverMobile(drivermobileno);

			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Successfully Fetched the completed rides");
			rs.setData(list);
		    return rs;
	}

	public ResponseEntity<ResponceStructure<RideCompletionDTO>> completeRide(int bookingId, String paymentType) {
		
        if (paymentType.equalsIgnoreCase("CASH")) {
            return cashPayment(bookingId);
        } 
        else if (paymentType.equalsIgnoreCase("UPI")) {
            return upiPayment(bookingId);
        } 
        else {
            throw new RuntimeException("Invalid payment type");
        }
    }

    
    // CASH PAYMENT
    
    private ResponseEntity<ResponceStructure<RideCompletionDTO>> cashPayment(int bookingId) {

        RideCompletionDTO dto = completeRideCommonLogic(bookingId, "CASH");

        ResponceStructure<RideCompletionDTO> rs = new ResponceStructure<>();
        rs.setStatuscode(200);
        rs.setMessage("Cash payment successful");
        rs.setData(dto);

        return ResponseEntity.ok(rs);
    }

    
    // UPI PAYMENT
    
    private ResponseEntity<ResponceStructure<RideCompletionDTO>> upiPayment(int bookingId) {

        RideCompletionDTO dto = completeRideCommonLogic(bookingId, "UPI");

        ResponceStructure<RideCompletionDTO> rs = new ResponceStructure<>();
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("UPI payment successful");
        rs.setData(dto);

        return ResponseEntity.ok(rs);
    }

    // COMMON RIDE COMPLETION LOGIC
    
    private RideCompletionDTO completeRideCommonLogic(int bookingId, String paymentType) {

        Booking booking = br.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        
        booking.setBookingstatus("COMPLETED");
        booking.setPaymentstatus("PAID");

        Customer customer = booking.getCustomer();
        customer.setBookingflag(false);

        Vehicle vehicle = booking.getVehicle();
        vehicle.setVehicleavailability_status("AVAILABLE");

        //penality calculation
        
        double fare = booking.getFare();           
        int penalityCount = customer.getPenality(); 
        int penalityPercent = penalityCount * 10;

        double finalAmount = fare + (fare * penalityPercent / 100.0);

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setCustomer(customer);
        payment.setVehicle(vehicle);
        payment.setAmount(booking.getFare());
        payment.setPaymenttype(paymentType);

        br.save(booking);
        cr.save(customer);
        vr.save(vehicle);
        pr.save(payment);

        RideCompletionDTO dto = new RideCompletionDTO();
        dto.setBooking(booking);
        dto.setCustomer(customer);
        dto.setVehicle(vehicle);
        dto.setPayment(payment);

        return dto;
    }

	public ResponceStructure cancelBooking(int did, LocalDate bookingdate) {
		int count=0;
		Driver d=dr.findById(did).orElseThrow(()->new RuntimeException("Driver is not found with that id"));

		Booking booking=br.findByVehicle_VidAndBookingdate(did, bookingdate).orElseThrow(()->new RuntimeException("Booking not Found with the id"));
		
		if("cancelled by driver".equalsIgnoreCase(booking.getBookingstatus())) {
			throw new RuntimeException("Booking already Cancelled");
		}
		for(Booking b:br.findByVehicle_Vid(did)) {
			if("cancelled by driver".equalsIgnoreCase(b.getBookingstatus())) {
				count++;
			}
		}
      booking.setBookingstatus("cancelled by driver");
      br.save(booking);
      
      if(count+1>=4) {
    	  d.setDriverstatus("blocked");
    	  dr.save(d);
      }
    
      ResponceStructure rs=new ResponceStructure();
      rs.setStatuscode(HttpStatus.OK.value());
      rs.setMessage("Booking Cancelled by the Driver");
      rs.setData("canclled");
      return rs;
}
	
}
	
	
	
	
	



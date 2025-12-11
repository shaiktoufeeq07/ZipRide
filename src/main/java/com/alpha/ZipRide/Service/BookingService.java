package com.alpha.ZipRide.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.BookingDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Entity.Driver;
import com.alpha.ZipRide.Entity.Vehicle;
import com.alpha.ZipRide.Repository.BookingRepo;
import com.alpha.ZipRide.Repository.CustomerRepo;
import com.alpha.ZipRide.Repository.DriverRepo;
import com.alpha.ZipRide.Repository.VehicleRepo;
import com.alpha.ZipRide.exception.CustomerNotFoundException;
@Service
public class BookingService {

	@Autowired
	private BookingRepo br;
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private VehicleRepo vr;
	
	@Autowired
	private DriverRepo dr;


	public ResponceStructure<Booking> bookvehicle(long customermobileno, BookingDto dto) {
		//finding customer object by mobiler number
		Customer c=cr.findByCustomermobileno(customermobileno).orElseThrow(()-> new CustomerNotFoundException());
		//finding vehicle id
		Integer Vehicleid = dto.getVid();
		//finding vehicle object by vehicle id
		Vehicle v=vr.findById(Vehicleid).orElseThrow(()-> new RuntimeException("vehicle not found with id "+Vehicleid));
		
	
		//
		
//		Find customer object by mobileno
		
		Booking b = new Booking();
		b.setCustomer(c);
		b.setVehicle(v);
		b.setSourceloction(dto.getSourceloction());
		b.setDestinationlocation(dto.getDestinationlocation());
		b.setFare(dto.getFare());
		b.setEstimatedtime(dto.getEstimatedtime());
		b.setDistancetravelled(dto.getDistancetravelled());
		b.getBookingdate();
		b.setBookingstatus("booked");
		br.save(b);
		
		//Adding booking to a customer
		c.getBlist().add(b);
//		cr.save(c);
		
		//Adding booking to a driver
		Driver d = v.getDriver();
		d.getBlist().add(b);
		
		
//		v.getDriver().getBlist().add(b);
	    v.setVehicleavailability_status("Vehicle Booked");
	    
	    cr.save(c);//saving a customer
	    dr.save(d);//saving a driver
	    
	    ResponceStructure<Booking> rs = new ResponceStructure<Booking>();
	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("Booked sucessfully");
	    rs.setData(b);
	    return rs;
	
	}
	

	
	
}
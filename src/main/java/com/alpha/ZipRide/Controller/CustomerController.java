package com.alpha.ZipRide.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.ActiveCustomerBookingDto;
import com.alpha.ZipRide.Dto.AvailableVehiclesDTO;
import com.alpha.ZipRide.Dto.CustomerDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cs;
	
	@PostMapping("/register")
	public Customer register(@RequestBody CustomerDto cd) {
		 return cs.registercustomer(cd);
	}
	
	@GetMapping("/findcustomer")
    public ResponseEntity<ResponceStructure<Customer>> find(@RequestParam long customermobileno) {
        ResponceStructure<Customer> response = cs.findCustomer(customermobileno);
        return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/deletecustomer")
	public ResponseEntity<ResponceStructure<String>> deletecustomer(@RequestParam long customermobileno) {
		ResponceStructure<String> response = cs.deletecustomer(customermobileno);
        return ResponseEntity.ok(response);
	}
	
	//to see all available vehicles
	@GetMapping("/seeallvehicles")
	public ResponseEntity<ResponceStructure<AvailableVehiclesDTO>> seeallavailablevehicles(@RequestParam long mobileno, @RequestParam("destinationcity") String destinationcity) {
		return cs.seeallavailablevehicles(mobileno, destinationcity);
	}
	
	@GetMapping("/seebookinghistory")
	public ResponceStructure<List<Booking>> bookinghistory(@RequestParam long mobileno) {
		 return cs.seebookinghistory(mobileno);
	}
	
	@GetMapping("/seeactivebookings")
	public ResponceStructure<ActiveCustomerBookingDto> activebookings(@RequestParam long mobileno){
		return cs.activebookings(mobileno);
	}
	
	@GetMapping("/servicemethod")
    public void servicemethod(@RequestParam long mobileNo) {
    	cs.findCustomer(mobileNo);
    }
	
	
}



























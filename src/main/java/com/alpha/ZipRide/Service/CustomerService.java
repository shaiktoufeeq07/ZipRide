package com.alpha.ZipRide.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.CustomerDto;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Repository.CustomerRepo;
import com.alpha.ZipRide.exception.CustomerNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class CustomerService {

	@Autowired
	private CustomerRepo cr;
	
	
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

	//to register the customer
	public Customer registercustomer(CustomerDto cd) {

        Customer c = new Customer();
        c.setCustomername(cd.getCustomername());
        c.setCustomerage(cd.getCustomerage());
        c.setCustomergender(cd.getCustomergender());
        c.setCustomermobileno(cd.getCustomermobileno());
        c.setEmail(cd.getEmail());
        
        c.setCurrentlocation(getCityName(cd.getClatitude(),cd.getClongitude()));
	     return cr.save(c);
	}
		
//	find by customermobileno
		public ResponceStructure<Customer> findCustomer(long customermobileno) {

	        Customer c = cr.findByCustomermobileno(customermobileno)
	                .orElseThrow(() -> new CustomerNotFoundException());

	        ResponceStructure<Customer> structure = new ResponceStructure<>();
	        structure.setStatuscode(HttpStatus.OK.value());
	        structure.setMessage("Customer found successfully!");
	        structure.setData(c);

	        return structure;
	    }

//		delete by customermobileno
		
		public ResponceStructure<String> deletecustomer(long customermobileNo) {

			   Customer customer = cr.findByCustomermobileno(customermobileNo)
			            .orElseThrow(CustomerNotFoundException::new);

			    // Delete the customer
			    cr.deleteByCustomermobileno(customermobileNo);

			    // Prepare response
			    ResponceStructure<String> response = new ResponceStructure<>();
			    response.setStatuscode(HttpStatus.OK.value());
			    response.setMessage("Customer with mobile number " + customermobileNo + " deleted successfully");
			    response.setData("Deleted Successfully");

			    return response;
	}
}


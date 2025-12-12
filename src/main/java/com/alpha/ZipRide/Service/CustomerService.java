package com.alpha.ZipRide.Service;

import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.ActiveCustomerBookingDto;
import com.alpha.ZipRide.Dto.AvailableVehiclesDTO;
import com.alpha.ZipRide.Dto.CustomerDto;
import com.alpha.ZipRide.Dto.VehicleDetailsDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Entity.Vehicle;
import com.alpha.ZipRide.Repository.BookingRepo;
import com.alpha.ZipRide.Repository.CustomerRepo;
import com.alpha.ZipRide.Repository.VehicleRepo;
import com.alpha.ZipRide.exception.CustomerNotFoundException;
import com.alpha.ZipRide.exception.InvalidDestinationCityException;

import jakarta.transaction.Transactional;
@Service
public class CustomerService {

	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private VehicleRepo vr;
	
	@Autowired
	private BookingRepo br;
	

	
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

	public double[] getCoordinates(String city) {

	    String apiKey = "4jzgMhanLaduMKYlgaNoS4xFufixiOhpBrwUK3InzxELr56ulhWhIIGAw3Hj5tGH";
	    String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
	    String url = "https://api.distancematrix.ai/maps/api/geocode/json?address="
	                 + encodedCity + "&key=" + apiKey;

	    RestTemplate restTemplate = new RestTemplate();
	    Map response = restTemplate.getForObject(url, Map.class);

	    // Check if response is valid
	    if (response == null || !"OK".equals(response.get("status"))) {
	        throw new InvalidDestinationCityException();  // or InvalidSourceCityException depending on usage
	    }

	    List resultList = (List) response.get("result");
	    if (resultList == null || resultList.isEmpty()) {
	        throw new InvalidDestinationCityException();
	    }

	    Map firstResult = (Map) resultList.get(0);

	    // Check address_components inside first result
	    if (!firstResult.containsKey("address_components") || firstResult.get("address_components") == null) {
	        throw new InvalidDestinationCityException();
	    }

	    Map geometry = (Map) firstResult.get("geometry");
	    if (geometry == null || !geometry.containsKey("location")) {
	        throw new InvalidDestinationCityException();
	    }

	    Map location = (Map) geometry.get("location");
	    double lat = Double.parseDouble(location.get("lat").toString());
	    double lng = Double.parseDouble(location.get("lng").toString());

	    return new double[]{ lat, lng };
	}


	public double getDistance(double sourceLat, double sourceLng, double destLat, double destLng) {
	

		    String apiKey = "uZfOzHLbMqX76OrJ1cgwySW9kmhwi9MGiuvQnzuRqv35vw9HLPx1StyZnjVQwUzb";
		    String url = "https://api.distancematrix.ai/maps/api/distancematrix/json?"
		            + "origins=" + sourceLat + "," + sourceLng
		            + "&destinations=" + destLat + "," + destLng
		            + "&key=" + apiKey;

		    RestTemplate restTemplate = new RestTemplate();
		    Map response = restTemplate.getForObject(url, Map.class);

		    if (response == null || !response.containsKey("rows"))
		        return -1;

		    List rows = (List) response.get("rows");
		    if (rows == null || rows.isEmpty())
		        return -1;

		    Map row = (Map) rows.get(0);
		    if (row == null || !row.containsKey("elements"))
		        return -1;

		    List elements = (List) row.get("elements");
		    if (elements == null || elements.isEmpty())
		        return -1;

		    Map element = (Map) elements.get(0);

		    // Check status
		    Object statusObj = element.get("status");
		    if (statusObj == null || !"OK".equals(statusObj.toString()))
		        return -1;

		    // Extract distance
		    Map dist = (Map) element.get("distance");
		    if (dist == null || !dist.containsKey("value"))
		        return -1;

		    double meters = Double.parseDouble(dist.get("value").toString());
		    return meters / 1000.0;
		}


	public ResponseEntity<ResponceStructure<AvailableVehiclesDTO>> seeallavailablevehicles(long mobileno, String destinationCity) {

		// Fetch customer
		Customer customer = cr.findByCustomermobileno(mobileno).orElseThrow(CustomerNotFoundException::new);

		String sourceCity = customer.getCurrentlocation();

		// Source coordinates
		double[] src =(getCoordinates(sourceCity));

		// Destination coordinates
		double[] dest =(getCoordinates(destinationCity));


		// Calculate distance
		double distance = getDistance(src[0], src[1], dest[0], dest[1]);
//		if (distance < 0) {
//	        throw new InvalidDestinationCityException();
//	    }

		// Fetch all vehicles
		List<Vehicle> vehicles = vr.findAll();

		List<VehicleDetailsDto> list = new ArrayList<>();

		for (Vehicle v : vehicles) {
			
			  if (!v.getVehiclecurrencity().equalsIgnoreCase(sourceCity)) {
			        continue; // Skip vehicles not in the customer's current city
			    }

			// Fare = distance * pricePerKm
			int fare = (int) (distance * v.getPriceperkm());

			// Estimated time = distance * 2 (OR any logic)
			int estimatedTime = (int) (distance * 2);

			VehicleDetailsDto vd = new VehicleDetailsDto(v, fare, estimatedTime);

			list.add(vd);
		}

		// Prepare DTO
		AvailableVehiclesDTO dto = new AvailableVehiclesDTO();
		dto.setC(customer);
		dto.setDistance(distance);
		dto.setSourceLocation(sourceCity);
		dto.setDestination(destinationCity);
		dto.setAvailableVehicles(list);

		// Final Response Structure
		ResponceStructure<AvailableVehiclesDTO>response = new ResponceStructure<>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Available vehicles fetched successfully");
		response.setData(dto);

		return  new ResponseEntity<ResponceStructure<AvailableVehiclesDTO>>(response,HttpStatus.OK);

	}

	public ResponceStructure<List<Booking>> seebookinghistory(long mobileno) {
		ResponceStructure<List<Booking>> rs=new ResponceStructure<List<Booking>>();
	    List<Booking> list = cr.findCompletedBookingsByMobile(mobileno);

		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Successfully Fetched the completed rides");
		rs.setData(list);
	    return rs;

}

	
		public ResponceStructure<ActiveCustomerBookingDto> activebookings(long mobileno) {
			ResponceStructure<ActiveCustomerBookingDto> rs=new ResponceStructure<ActiveCustomerBookingDto>();
			List<Booking> list=cr.findActiveBookingsByMobile(mobileno);
			
			//if there are no present bookings of an customer
		    if (list.isEmpty()) {
		        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		        rs.setMessage("No active bookings found");
		        rs.setData(null);
		        return rs;
		    }
		    
		    //to find the active bookings of a customer
		    Booking booking =list.get(list.size()-1);
		    Customer c=booking.getCustomer();
		    
		    ActiveCustomerBookingDto ad=new ActiveCustomerBookingDto();
		    ad.setCustomername(c.getCustomername());
		    ad.setCustomermobno(c.getCustomermobileno());
		    ad.setBooking(booking);
		    ad.setCustomercurrentlocation(c.getCurrentlocation());
		    
		    rs.setStatuscode(HttpStatus.FOUND.value());
		    rs.setMessage("Active Booking of the customer");
		    rs.setData(ad);
		    
			return rs;
			
	}}




























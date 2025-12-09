package com.alpha.ZipRide.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.AvailableVehiclesDTO;
import com.alpha.ZipRide.Dto.CustomerDto;
import com.alpha.ZipRide.Dto.VehicleDetailsDto;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Entity.Vehicle;
import com.alpha.ZipRide.Repository.CustomerRepo;
import com.alpha.ZipRide.Repository.VehicleRepo;
import com.alpha.ZipRide.exception.CustomerNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepo cr;

	@Autowired
	private VehicleRepo vr;

	// to get the city name from latitude and longitude
	public String getCityName(double latitude, double longitude) {

		String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;

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

	// To register a customer
	public Customer registercustomer(CustomerDto cd) {

		Customer c = new Customer();
		c.setCustomername(cd.getCustomername());
		c.setCustomerage(cd.getCustomerage());
		c.setCustomergender(cd.getCustomergender());
		c.setCustomermobileno(cd.getCustomermobileno());
		c.setEmail(cd.getEmail());

		// Set city from GPS using Nominatim
		c.setCurrentlocation(getCityName(cd.getClatitude(), cd.getClongitude()));

		return cr.save(c);
	}

	// To find a customer
	public ResponceStructure<Customer> findCustomer(long customermobileno) {

		Customer c = cr.findByCustomermobileno(customermobileno).orElseThrow(CustomerNotFoundException::new);

		ResponceStructure<Customer> structure = new ResponceStructure<>();
		structure.setStatuscode(HttpStatus.OK.value());
		structure.setMessage("Customer found successfully!");
		structure.setData(c);

		return structure;
	}

	// to delete a customer
	public ResponceStructure<String> deletecustomer(long customermobileNo) {

		Customer customer = cr.findByCustomermobileno(customermobileNo).orElseThrow(CustomerNotFoundException::new);

		cr.deleteByCustomermobileno(customermobileNo);

		ResponceStructure<String> response = new ResponceStructure<>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Customer deleted successfully!");
		response.setData("Deleted Successfully");

		return response;
	}

	public double[] getCoordinates(String city) {
		try {
			String apiKey = "4jzgMhanLaduMKYlgaNoS4xFufixiOhpBrwUK3InzxELr56ulhWhIIGAw3Hj5tGH";
			String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

			String url = "https://api.distancematrix.ai/maps/api/geocode/json?address=" + encodedCity + "&key="
					+ apiKey;		

			RestTemplate restTemplate = new RestTemplate();
			Map response = restTemplate.getForObject(url, Map.class);

			if (response == null || !response.containsKey("result")) {
				return null;
			}

			List resultList = (List) response.get("result");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}

			Map firstResult = (Map) resultList.get(0);
			Map geometry = (Map) firstResult.get("geometry");

			if (geometry == null || !geometry.containsKey("location")) {
				return null;
			}

			Map location = (Map) geometry.get("location");

			double lat = Double.parseDouble(location.get("lat").toString());
			double lng = Double.parseDouble(location.get("lng").toString());

			return new double[] { lat, lng };

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getDistance(double sourceLat, double sourceLng, double destLat, double destLng) {
		String apiKey = "uZfOzHLbMqX76OrJ1cgwySW9kmhwi9MGiuvQnzuRqv35vw9HLPx1StyZnjVQwUzb"; // Replace with your key
		String url = "https://api.distancematrix.ai/maps/api/distancematrix/json?" + "origins=" + sourceLat + ","
				+ sourceLng + "&destinations=" + destLat + "," + destLng + "&key=" + apiKey;

		RestTemplate restTemplate = new RestTemplate();
		Map response = restTemplate.getForObject(url, Map.class);
		if (!response.containsKey("rows"))
			return -1;

		List rows = (List) response.get("rows");
		Map row = (Map) rows.get(0);
		List elements = (List) row.get("elements");
		Map element = (Map) elements.get(0);
		Map dist = (Map) element.get("distance");

		double meters = Double.parseDouble(dist.get("value").toString());
		return meters / 1000.0; // KM
	}

	public ResponceStructure<AvailableVehiclesDTO> seeallavailablevehicles(long mobileno, String destinationCity) {

		// Fetch customer
		Customer customer = cr.findByCustomermobileno(mobileno).orElseThrow(CustomerNotFoundException::new);

		String sourceCity = customer.getCurrentlocation();

		// Source coordinates
		double[] src = getCoordinates(sourceCity);
		if (src == null)
			throw new RuntimeException("Invalid source city: " + sourceCity);

		// Destination coordinates
		double[] dest = getCoordinates(destinationCity);
		if (dest == null)
			throw new RuntimeException("Invalid destination city: " + destinationCity);

		// Calculate distance
		double distance = getDistance(src[0], src[1], dest[0], dest[1]);

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
		ResponceStructure<AvailableVehiclesDTO> response = new ResponceStructure<>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Available vehicles fetched successfully");
		response.setData(dto);

		return response;
	}
}

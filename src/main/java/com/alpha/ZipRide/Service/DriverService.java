package com.alpha.ZipRide.Service;

import java.util.List;
import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.ZipRide.ResponceStructure;

import com.alpha.ZipRide.Dto.RegDriverVehicleDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Driver;
import com.alpha.ZipRide.Entity.Vehicle;
import com.alpha.ZipRide.Repository.DriverRepo;
import com.alpha.ZipRide.Repository.VehicleRepo;


@Service
public class DriverService {

    @Autowired
    private DriverRepo dr;
    @Autowired
    private VehicleRepo vr;

    
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
		    List<Booking> list = vr.findCompletedBookingsByMobile(drivermobileno);

			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Successfully Fetched the completed rides");
			rs.setData(list);
		    return rs;
	}

}
	
	
	
	
	



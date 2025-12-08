package com.alpha.ZipRide.Service;

import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.ZipRide.Dto.RegDriverVehicleDto;
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
        d.setDname(dto.getDname());
        d.setAge(dto.getAge());
        d.setMobno(dto.getMobno());
        d.setGender(dto.getGender());
        d.setMail(dto.getMail());
        d.setStatus("Available");

        String city = getCityName(dto.getLatitude(), dto.getLongitude());

        Vehicle v = new Vehicle();
        v.setVname(dto.getVname());
        v.setVno(dto.getVno());
        v.setVtype(dto.getVtype());
        v.setVmodel(dto.getVmodel());
        v.setCapacity(dto.getCapacity());
        v.setCurrencity(city); 
        v.setPriceperkm(dto.getPriceperkm());

        
        v.setDriver(d);
        d.setVehicle(v);

        
        return dr.save(d);
    }
    
    //FINDING THE DRIVER 
    
    public Driver findDriverByMobile(long mobno) {
        return dr.findByMobno(mobno);
    }

    //deleting the driver by mobile number    
    public String deleteDriver(long mobno) {

        Driver driver = dr.findByMobno(mobno);
        dr.delete(driver);
		return "Deleted Successfully";
        }
    
    //UPDATING THE VEHICLES LOCATION USING DRIVER'S MOBILE NUMBER
    
	public String updateDriverLocation(long mobileNo, double latitude, double longitude) {
		
		
        Driver driver = dr.findByMobno(mobileNo);
            
       
        String city = getCityName(latitude, longitude);

       
        if (driver.getVehicle() != null) {
            Vehicle v = driver.getVehicle();
            v.setCurrencity(city);

           
            vr.save(v);
        }

        return "Driver location updated to city: " + city;

	}

}
	
	
	
	
	



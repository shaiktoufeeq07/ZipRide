package com.alpha.ZipRide.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ZipRide.Dto.RegDriverVehicleDto;
import com.alpha.ZipRide.Entity.Driver;
import com.alpha.ZipRide.Service.DriverService;

@RestController
public class ZipRideController {
	
	@Autowired
	private DriverService ds;
	
	@PostMapping("/savedetails")
	public Driver savingdetails(@RequestBody RegDriverVehicleDto rd) {
		 return ds.saveDriverDTO(rd);
	}
	
	@GetMapping("/finddriver")
	public Driver finding(@RequestParam long mobno) {
		return ds.findDriverByMobile(mobno);
	}
	
	@DeleteMapping("/deletedriver")
	public String deleting(@RequestParam long mobno) {
		return ds.deleteDriver(mobno);
	}
	
	
	@PatchMapping("/updatedriver")
	public String deleting(@RequestParam long mobno, @RequestParam double latitude, @RequestParam double  longitude) {
		 return ds.updateDriverLocation(mobno, latitude, longitude);
	}

}

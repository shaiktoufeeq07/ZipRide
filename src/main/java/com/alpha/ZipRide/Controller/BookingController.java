package com.alpha.ZipRide.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ZipRide.ResponceStructure;
import com.alpha.ZipRide.Dto.BookingDto;
import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Customer;
import com.alpha.ZipRide.Service.BookingService;
import com.alpha.ZipRide.Service.MailService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bs;
	
	@Autowired 
	private MailService mailservice;
	
	@PostMapping("/savebooking")
	public ResponceStructure<Booking> bookvehicle(@RequestParam long customermobileno , @RequestBody BookingDto dto) {
		return bs.bookvehicle(customermobileno,dto);
	}

	@GetMapping("sendmail")
	public void sendmail(String tomail, String subject, String message) {
		mailservice.sendmail(tomail, subject, message);
	}

}

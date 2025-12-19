package com.alpha.ZipRide.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javamailsender;

	public void sendmail(String toMail, String subject, String message) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toMail);
		mail.setSubject(subject);
		mail.setFrom("pasnoornikithareddy28@gmail.com");
		mail.setText(message);
		javamailsender.send(mail);
		
	}
	
	
	
}

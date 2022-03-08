package com.nrifintech.bms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail,String body)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("beingakscool@gmail.com");
		mailMessage.setTo(toEmail);
		mailMessage.setSubject("TICKET CONFIRMATION");
		mailMessage.setText(body);
		
		javaMailSender.send(mailMessage);
		
		System.out.println("Mail sent");
	}
}

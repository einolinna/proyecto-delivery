package com.delivery.BuenSabor.Factura.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("serviciocuentas1@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
			} catch (MailException exception) {
				exception.printStackTrace();
			}
		
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom("serviciocuentas1@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);
	    
	    emailSender.send(message);
		
	}

}

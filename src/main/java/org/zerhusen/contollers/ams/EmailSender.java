package org.zerhusen.contollers.ams;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.config.EmailConfig;

@RestController
@RequestMapping("/sendEmail")
public class EmailSender {
	
	@Autowired
	private EmailConfig emailconifg;
	
	@PostMapping("/sendMessage")
	public void mailSender(@RequestBody String mail) throws JSONException {
		JSONObject json = new JSONObject(mail);
		
		JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
		
		mailsender.setHost(emailconifg.getHost());
		mailsender.setPort(emailconifg.getPort());
		mailsender.setUsername(emailconifg.getUsername());
		mailsender.setPassword(emailconifg.getPassword());
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(emailconifg.getUsername());
		mailMessage.setTo("bertinmendonsa29@gmail.com");
		mailMessage.setSubject(json.getString("subject"));
		mailMessage.setText(json.getString("text"));
		
		
		mailsender.send(mailMessage);
	}

}

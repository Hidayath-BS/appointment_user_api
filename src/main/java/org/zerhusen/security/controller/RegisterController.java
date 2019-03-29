package org.zerhusen.security.controller;


import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.model.security.Ak_city;
import org.zerhusen.model.security.Ak_state;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.User;
import org.zerhusen.security.repository.CityRepository;
import org.zerhusen.security.repository.Rolerepository;
import org.zerhusen.security.repository.StateRepository;
import org.zerhusen.security.repository.UserRepository;
import org.zerhusen.service.MessageService;
import org.zerhusen.service.OtpService;
import org.zerhusen.service.PasswordEncoderCustom;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/register")
public class RegisterController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UserRepository userrepo; 
	
	@Autowired
	public Rolerepository rolerepo;

	@Autowired
	public OtpService otpservice;
	
	@Autowired
	public MessageService messageservice;
	
	@Autowired
	public PasswordEncoderCustom pswdencoder;
	
	@Autowired
	public CityRepository cityRepo;
	
	@Autowired
	public StateRepository stateRepo;
	
//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
		
	@PostMapping("/generateOtp")
	public ResponseEntity<?> genrateOtp(@RequestBody String body) throws JSONException {
		JSONObject json = new JSONObject(body);
		
		User userExists = userrepo.findByMobilenumber(json.getString("mobile_number"));
		
		if(userExists != null) {
			int otp = otpservice.genfpOtp(json.getString("mobile_number"));
			messageservice.sendSmsOtp(otp, json.getString("mobile_number"));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping("/validateOtp")
	public ResponseEntity<?> validateOtp(@RequestBody String body) throws JSONException{
		JSONObject json = new JSONObject(body);
		int otp = json.getInt("otp");
		int serverOtp = otpservice.getfpOtp(json.getString("mobile_number"));
		User userExists = userrepo.findByMobilenumber(json.getString("mobile_number"));
		if(userExists != null) {
			if(otp == serverOtp) {
				otpservice.clearfpOtp(json.getString("mobile_number"));
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody String body) throws JSONException{
		JSONObject json = new JSONObject(body);
		
		User user = userrepo.findByMobilenumber(json.getString("mobile_number"));
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		if(user != null) {
			user.setLastPasswordResetDate(timestamp);
			user.setPassword(pswdencoder.passwordEncoder().encode(json.getString("password")));
			
			User save = userrepo.save(user);
			if(save != null) {
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
	}
	
}

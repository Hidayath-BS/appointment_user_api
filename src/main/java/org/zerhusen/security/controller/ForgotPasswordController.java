package org.zerhusen.security.controller;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.service.MessageService;
import org.zerhusen.service.OtpService;
import org.zerhusen.service.PasswordEncoderCustom;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.security.*;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.model.security.*;


@RestController
@RequestMapping("/forgotpassword")
@CrossOrigin(origins="*")
public class ForgotPasswordController {

	@Autowired
	private AmsPatientUsersRepository patientRepo;
	
	@Autowired
	private MessageService messageServie;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	public PasswordEncoderCustom pswdencoder;
	
	
	@PostMapping("/forgotPasswordOtp")
	public ResponseEntity<?> forgotPasswordGenOtp(@RequestBody String user) throws JSONException{
			JSONObject jsonobj = new JSONObject(user);
			Ams_patient_users userExistes = patientRepo.findByMobileNumber(jsonobj.getString("mobilenumber"));
			
			if(userExistes !=null) {
//				i.e if user exists, check  weather he is active
				boolean userActive = userExistes.isActive();
				if(userActive == true ) {
//					user is active send OTP sms & success message response.
					int otp = otpService.genfpOtp(jsonobj.getString("mobilenumber"));
					messageServie.sendSmsOtp(otp, jsonobj.getString("mobilenumber"));
					
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
					
				}else {
//					user is not active return ask user to go for registration  page.
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
				
				
			}else {
//				user doesnot exists
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
	}
	
	
	@PostMapping("/validateFpOtp")
	public ResponseEntity<?> validatFpOtp(@RequestBody String req) throws JSONException {
			
		JSONObject jsonobj = new JSONObject(req);
		int otp = jsonobj.getInt("otp");
		String mobilenumber = jsonobj.getString("mobileNumber");
		if(otp >= 0){
			
		System.out.println("This is forgot Otp sent with rquest : "+otp);
			
		int serverOtp = otpService.getfpOtp(mobilenumber);
		

		
		System.out.println("This is otp stored in server : "+serverOtp);
		
		if(serverOtp > 0){
		if(otp == serverOtp){
			
			
			
		otpService.clearfpOtp(mobilenumber);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		

	}

	
	@PostMapping("/changePswd")
	public ResponseEntity<?> passwordReset(@RequestBody String request) throws JSONException {
		
		JSONObject json = new JSONObject(request);
		
		Ams_patient_users userExists = patientRepo.findByMobileNumber(json.getString("mobilenumber"));
		String pswd = json.getString("password");
		
		
		
		if(userExists !=  null) {
			boolean userActive = userExists.isActive();
			if(userActive == true) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				userExists.setPassword(pswdencoder.passwordEncoder().encode(pswd));
				userExists.setLastPasswordResetDate(timestamp);
				patientRepo.save(userExists);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}else {
//				user is not enabled
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		}else {
//			user doesnot exists
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	
}

package org.zerhusen.security.controller;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerhusen.model.security.User;
import org.zerhusen.security.repository.UserRepository;
import org.zerhusen.service.MessageService;
import org.zerhusen.service.OtpService;
import org.zerhusen.service.PasswordEncoderCustom;



@Controller
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MessageService messageServie;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	public PasswordEncoderCustom pswdencoder;
	
	
	@PostMapping("/forgotPasswordOtp")
	public ResponseEntity<?> forgotPasswordGenOtp(@RequestBody String user) throws JSONException{
			JSONObject jsonobj = new JSONObject(user);
			User userExistes = userRepo.findByMobilenumber(jsonobj.getString("mobilenumber"));
			
			if(userExistes !=null) {
//				i.e if user exists, check  weather he is active
				boolean userActive = userExistes.getEnabled();
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
	
	
	@GetMapping("/validateFpOtp")
	public @ResponseBody String validatFpOtp(@RequestParam("mobilenumber") String mobilenumber,@RequestParam("otp") int otp) {
			
		
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";

		if(otp >= 0){
			
		System.out.println("This is forgot Otp sent with rquest : "+otp);
			
		int serverOtp = otpService.getfpOtp(mobilenumber);
		

		
		System.out.println("This is otp stored in server : "+serverOtp);
		
		if(serverOtp > 0){
		if(otp == serverOtp){
			
			
			
		otpService.clearfpOtp(mobilenumber);
		return ("Entered Otp is valid");
		}else{
		return FAIL;
		}
		}else {
		return FAIL;
		}
		}else {
		return FAIL;
		}
		
		

	}

	
	@PostMapping("/changePswd")
	public ResponseEntity<?> passwordReset(@RequestBody String request) throws JSONException {
		
		JSONObject json = new JSONObject(request);
		
		User userExists = userRepo.findByMobilenumber(json.getString("mobilenumber"));
		String pswd = json.getString("password");
		
		
		
		if(userExists !=  null) {
			boolean userActive = userExists.getEnabled();
			if(userActive == true) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				userExists.setPassword(pswdencoder.passwordEncoder().encode(pswd));
				userExists.setLastPasswordResetDate(timestamp);
				userRepo.save(userExists);
				return ResponseEntity.ok("success");
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

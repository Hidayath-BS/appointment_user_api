package org.zerhusen.ams.rest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsPatientAuthority;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.model.security.Ak_city;
import org.zerhusen.ams.model.security.Ak_state;
import org.zerhusen.ams.repository.AmsPatientAuthorityRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.security.CityRepository;
import org.zerhusen.ams.repository.security.StateRepository;
import org.zerhusen.config.EmailConfig;
import org.zerhusen.service.MessageService;
import org.zerhusen.service.PasswordEncoderCustom;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/register")
public class PatientRegistrationRest {

	@Autowired
	private AmsPatientUsersRepository patientUsers;
	
	@Autowired
	private EmailConfig emailCfig;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	PasswordEncoderCustom pswd;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private AmsPatientAuthorityRepository patientAuthorityRepo;
	
	@PostMapping(value="/patientRegister")
	public ResponseEntity<Ams_patient_users> addPatients(@RequestBody String patients)throws JSONException, ParseException, MessagingException
	{
        
		MessageService msgService = new MessageService();
		JSONObject jsonobj = new JSONObject(patients);
		
		LocalDate date = LocalDate.now();
				
		Ak_state stateid = stateRepo.findById(jsonobj.getInt("stateid"));
		
		Ak_city cityid = cityRepo.findById(jsonobj.getInt("cityid"));
		
		int patientNumber = patientUsers.getMaxId()+1;
		String patientCode = "BN"+date.getYear()+date.getMonthValue()+date.getDayOfMonth()+patientNumber;
		
		Ams_patient_users patientExists = patientUsers.findByMobileNumber(jsonobj.getString("mobileNumber"));
		
		AmsPatientAuthority authority = patientAuthorityRepo.findByRole("PATIENT");
		
		if(patientExists!=null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else {
			Ams_patient_users patient =  new Ams_patient_users(jsonobj.getString("firstName"), jsonobj.getString("lastName"),
					jsonobj.getString("email"), pswd.passwordEncoder().encode(jsonobj.getString("password")), 
					jsonobj.getString("mobileNumber"), jsonobj.getString("addressLine1"), jsonobj.getString("addressLine2"),
					jsonobj.getString("pincode"), patientCode, true);
			patient.setState(stateid);
			patient.setCity(cityid);
			patient.setAuthority(new HashSet<AmsPatientAuthority>(Arrays.asList(authority)));
			patientUsers.save(patient);
			
			//Mail
			String subject ="Congrats!! Now you are registered with us!!";
			String text ="<html>"
					+ "<body> <p> Hi Dear <b>"+patient.getFirstName()+" "+patient.getLastName()+"</b> </p>"+"<p>Welcome to <b>BANGALORE NETHRALAYA</b> <br/> "
							+ "<hr/>"
							+ "Your Username for Login is : "+patient.getEmail()
							+ "<br/>Thank You <br/>"
							+ "Team <b>BANGALORE NETHRALAYA</b></p>"
							+ "</body> </html>";
			registerEmail(patient.getEmail(),subject, text);
			String text1 = "Hi Dear "+patient.getFirstName()+", \r\n" + 
					"Thank you for registering with us.\r\n" + 
					"Team BANGALORE NETHRALAYA";
			msgService.sendSmsRegister(patient.getMobileNumber(), text1);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
	}
	
	public void registerEmail(String email, String subject, String text)throws MessagingException{
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setFrom(emailCfig.getUsername());
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(text, true);
		javaMailSender.send(mail);
	}

}

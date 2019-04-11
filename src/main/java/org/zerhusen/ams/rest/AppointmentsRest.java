package org.zerhusen.ams.rest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.repository.AmsAppointmentsRepository;
import org.zerhusen.config.EmailConfig;



import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("/appointments")
public class AppointmentsRest {

	@Autowired
	public AmsAppointmentsRepository appointmentRepo;
	
	@Autowired
	private EmailConfig emailconifg;
	
	@Autowired
	private JavaMailSender javamailSender;
	
	@GetMapping("/appointmentDetails/{id}")
	public AmsAppointments getAppointmentDetails(@PathVariable("id") int id) {
		AmsAppointments appointment = appointmentRepo.findById(id);
		return appointment;
	}
	
	@SuppressWarnings("unused")
	@PutMapping("/cancelAppointment")
	public ResponseEntity<?> cancelAppointment(@RequestBody String request) throws JSONException, MessagingException{
		JSONObject json = new JSONObject(request);
		
		int id = json.getInt("appid");
		AmsAppointments appointment = appointmentRepo.findById(id);
		
		String subject = "APPOINTMENT CANCELLED ON "+ appointment.getDate();
		
		String text = "<html> <body> Hi Dear "+ appointment.getPatientName()
		+", <br> The Appointment with <b> Dr. "+ appointment.getSlot().getDoctor().getUsername()
		+"</b> on <b>"+ appointment.getDate() + "</b> AT <b>" + appointment.getSlot().getSlot().getStartTime() +
		"</b> <br/> At our <b> "+ appointment.getSlot().getBranch().getBranchName()+"</b> Has been Cancelled by you. <br> <br>"+
		"Thank You for being with Us.. <br> Team , <b>BANGALORE NETHRALAYA.</b> </body> </html>";
		
		if(appointment != null) {
			
			appointment.setActive(false);
			
			appointmentRepo.save(appointment);
			
			this.cancelHtmlEmailSender(appointment.getPatientUser().getEmail(), subject, text);
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
	
	
	public void cancelHtmlEmailSender(String email, String subject, String text) throws MessagingException {
		MimeMessage mail = javamailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setFrom(emailconifg.getUsername());
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(text, true);
		javamailSender.send(mail);
	}
	
	public void moneyRefundService() {
		
//		get Reference to Instamojo API
		
		ApiContext context = ApiContext.create("test_685a99204e8766ab0438221dbb2", "test_efb261252e33c997bdb7196eea4", ApiContext.Mode.TEST);
		Instamojo api = new InstamojoImpl(context);
		
		
		
	}
}

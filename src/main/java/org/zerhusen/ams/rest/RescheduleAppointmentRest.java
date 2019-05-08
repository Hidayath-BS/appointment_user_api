package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.model.AmsAvailableTimeSlots;
import org.zerhusen.ams.model.AmsReschedules;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.repository.AmsAppointmentsRepository;
import org.zerhusen.ams.repository.AmsAvailableTimeSlotRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.AmsRescheduleRepository;
import org.zerhusen.config.EmailConfig;
import org.zerhusen.security.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class RescheduleAppointmentRest {
	
	@Autowired
	private AmsRescheduleRepository appointmentResheduleRepo;

	@Autowired
	private AmsAvailableTimeSlotRepository availableTimeSlotRepo;
	@Autowired
	private AmsAppointmentsRepository appointmentRepo;
	@Autowired
	public AmsPatientUsersRepository userrepo;
	@Value("${jwt.header}")
	private String tokenHeader;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private EmailConfig emailconifg;
	
	@Autowired
	private JavaMailSender javamailSender;


	@GetMapping("/rescheduledAppointmentList")
	public Iterable<AmsReschedules> rescheduledAppointmentList(HttpServletRequest req) {
		String token = req.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Ams_patient_users user = userrepo.findByEmail(username);
		return appointmentResheduleRepo.findAll().stream().filter(i->i.isActive()== true && i.getAppointment().getPatientUser() != null && i.getAppointment().getPatientUser().equals(user)).collect(Collectors.toList());
	}

	@PutMapping("/deleteResheduledAppointment")
	public @ResponseBody ResponseEntity<?> deleteAppointment(@RequestBody String appointment) throws JSONException{
		
		JSONObject json = new JSONObject(appointment);
		
		AmsReschedules reSheduledAppointment = appointmentResheduleRepo.findById(json.getInt("appointmentid"));
		
		if(reSheduledAppointment != null) {
			reSheduledAppointment.setActive(false);
			appointmentResheduleRepo.save(reSheduledAppointment);
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}



	@GetMapping(value="/getavailableTimeSlot/{id}")
	public Iterable<AmsAvailableTimeSlots> availableSlots(@PathVariable("id") int id)  {
		AmsAppointments appointment = appointmentRepo.findById(id);
		return availableTimeSlotRepo.findAll().stream().filter(i->(i.isOnline() == true)&&
				(i.getBranch()== appointment.getSlot().getBranch())&&(i.isActive()== true)&&
				(i.getDoctor() == appointment.getSlot().getDoctor())).collect(Collectors.toList()); 
	}

	@GetMapping("/getDatee/{id}")
	public AmsAppointments getDatee(@PathVariable int id) {
		return  appointmentRepo.findById(id); 
	}
	
	@PostMapping("/ResheduledAppointments")
	public ResponseEntity<?> postResheduledAppointments(@RequestBody String appointment) throws JSONException, MessagingException{
		JSONObject jsonobj = new JSONObject(appointment);
		LocalDate date = LocalDate.parse(jsonobj.getString("datee"));
		AmsAppointments appointmentId = appointmentRepo.findById(jsonobj.getInt("appointmentId"));
		AmsAvailableTimeSlots availableTimeSlot = availableTimeSlotRepo.findById(jsonobj.getInt("slotid"));
		if(appointmentId != null && availableTimeSlot != null) {
			
			appointmentId.setRescheduled(true);
			appointmentRepo.save(appointmentId);
			AmsReschedules resheduleAppointment = new AmsReschedules(date, true, (byte) 1);
			resheduleAppointment.setAppointment(appointmentId);
			resheduleAppointment.setSlot(availableTimeSlot);
			appointmentResheduleRepo.save(resheduleAppointment);
			
			String email = appointmentId.getEmailId();
			
			String subject = "Rescheduled !!!, Appointment on "+availableTimeSlot.getDate()+ " At : "+availableTimeSlot.getSlot().getStartTime();
			
			String text = "<html><body>"
					+ "<h4>GREETINGS FORM BANGALORE NETHRALAYA !!!</h4>"
					+ "<p>"
					+ "Hi "+appointmentId.getPatientName()+", <br>"
					+ "You Have Opted for Reschedule of Appointment on :"+availableTimeSlot.getDate()
					+ "<br/>"
					+ "At "+availableTimeSlot.getSlot().getStartTime()+" , At Our Branch : "+ availableTimeSlot.getBranch().getBranchName()
					+ "</p>"
					+ "<hr/>"
					+ "<p>Thank You .<br>"
					+ "Team <b>BANGALORE NETHRALAYA</b> </p>"
					+ "</body> </html>";
			
			this.reschedultEmailSender(email, subject, text);
			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	public void reschedultEmailSender(String email, String subject, String text) throws MessagingException {
		MimeMessage mail = javamailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setFrom(emailconifg.getUsername());
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(text, true);
		javamailSender.send(mail);
	}
	
}

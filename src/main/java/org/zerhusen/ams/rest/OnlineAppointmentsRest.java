package org.zerhusen.ams.rest;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.model.AmsAvailableTimeSlots;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.payload.InstamojoPaymentUrlPayload;
import org.zerhusen.ams.payload.PaymentDetailPayload;
import org.zerhusen.ams.repository.AmsAppointmentsRepository;
import org.zerhusen.ams.repository.AmsAvailableTimeSlotRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.config.EmailConfig;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.service.MessageService;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/onlineAppointments")
public class OnlineAppointmentsRest {
	
	@Autowired
	private AmsAvailableTimeSlotRepository slotsRepository;
	
	@Autowired
	private AmsAppointmentsRepository appointRepo;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public AmsPatientUsersRepository userrepo;
	
	
	@Autowired
	private JavaMailSender javamailSender;
	
	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private MessageService msgService;
	
	@PostMapping("/addOnlineAppointment")
	public ResponseEntity<?> addOnlineAppointments(@RequestBody String request, HttpServletRequest req)throws JSONException, ParseException, MessagingException
	{
		JSONObject json = new JSONObject(request);
		
		
		String token = req.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Ams_patient_users user = userrepo.findByEmail(username);
		
		LocalDate date = LocalDate.parse(json.getString("date"));
		String patientName = json.getString("fullName");
		
		int age = json.getInt("age");
		
		
		
		String gender = json.getString("gender");
		
		String  contactNumber = json.getString("mobileNumber");
		String emailId = json.getString("email");
		
		boolean diabetic = json.getBoolean("diabetic");
		String diabeticDuration = json.getString("diabeticDuration");
		boolean bp = json.getBoolean("bp");
		String bpDuration = json.getString("bpDuration");
		
		boolean cardiac = json.getBoolean("cardiac");		
		
		boolean asthama = json.getBoolean("asthma");

		boolean eyeProblem = json.getBoolean("eyeProblem");
		String eyeProbDetails = json.getString("eyeProblemDetails");
		
		boolean eyeDrops = json.getBoolean("eyeDrops");
		String dropDetails = json.getString("dropDetails");
		
		boolean drugAllergy = json.getBoolean("drugAllergy");
		String drugAllergyDetails = json.getString("drugAllergyDuration");
		
		boolean medCondition = json.getBoolean("otherMedicalCondition");
		String medConditionDetails = json.getString("otherMedicalConditionDuration");
		
		String refBy = json.getString("refferedBy");
		
		
		
		AmsAvailableTimeSlots slot = slotsRepository.findById(json.getInt("slot"));
		
		if(slot!=null && user != null) {
			int onlineCount = slot.getOnlineCount();
			
//			AmsAppointments appointment = new AmsAppointments(date, patientName, age, gender, diabetic, diabeticDuration, bp, bpDuration, cardiac, asthama, contactNumber, emailId, (byte) 1, (byte) 1, eyeProblem, eyeProbDetails, eyeDrops, dropDetails, false, false, true, false, drugAllergy, drugAllergyDetails, medCondition, medConditionDetails, refBy);
			
			AmsAppointments appointment = new AmsAppointments(date, patientName, age, gender, diabetic, diabeticDuration, bp, bpDuration, cardiac, asthama, contactNumber, emailId, (byte) 1, (byte) 1, eyeProblem, eyeProbDetails, eyeDrops, dropDetails, false, false, true, false, drugAllergy, drugAllergyDetails, medCondition, medConditionDetails, refBy, json.getString("addressLine1") , json.getString("addressLine2") , json.getString("pincode") );
			
			
			appointment.setPatientUser(user);
			
			appointment.setPaymentMethod(json.getString("payment_method"));
			
			appointment.setSlot(slot);
			
			slot.setOnlineCount(onlineCount+1);
			
			 AmsAppointments appo = appointRepo.save(appointment);
			
			slotsRepository.save(slot);
			
			
			//Mail
			String subject ="Your Appointment request is received";
			String text ="<html> <body> Hi Dear "+appointment.getPatientName()+
					"<br/>"+"Your apointment has been scheduled on"+"<br/>"+
					"Requested date: "+appointment.getDate()+
					"          Appointment Time: "+appointment.getSlot().getSlot().getStartTime()+
					" At our "+ appointment.getSlot().getBranch().getBranchName()+" branch"+"<br/>"+ 
					"Thank you"+"<br/>"+
					"Bangalore Nethralaya"+
					" </body> </html>";
			this.appointmentEmail(appointment.getPatientUser().getEmail(),subject, text);
			
			//message
			String text1 = "Hi Dear "+appointment.getPatientName()+" , Your apointment scheduled on date: "+appointment.getDate()+" ,\r\n" + 
					"Appointment Time : "+appointment.getSlot().getSlot().getStartTime()+ "\r\n"+
					"Team , BANGALORE NETHRALAYA";
			msgService.sendSmsAppointmet(appointment.getContactNumber(), text1);
			
			if(json.getString("payment_method").equals("ONLINE")) {
				
				InstamojoPaymentUrlPayload payload = this.createPaymentRequest(appo);
				
				System.out.println(payload);
				
				return ResponseEntity.ok(payload);
				
				
			}else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			
			
		}
		else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
	}
	
	
	public InstamojoPaymentUrlPayload createPaymentRequest(AmsAppointments appointment) throws JSONException {
//		get API Context
		
		
		
		ApiContext context = ApiContext.create("test_fJEPvVj8xs9l0dQw4VZPY5aktpKldUAilWc", "test_2ZW3fILEKuDDTCnuKYDVmDKDI7TGjuqzcVRIu87YbbirCCBf9BKSWw2I8sJ1t6scPhz5xtonhqiirkDpO3xdU3o02cxIiKk29d9DFyTQ0SFCyhzW4GZKtqtXKdl", ApiContext.Mode.TEST);
		Instamojo api = new InstamojoImpl(context);
		
		/*
		 * Create a new payment order
		 */
		PaymentOrder order = new PaymentOrder();
		
		
		
		order.setName(appointment.getPatientName());
		order.setPhone(appointment.getPatientUser().getMobileNumber());
		order.setAmount(400D);
		order.setEmail(appointment.getPatientUser().getEmail());
		order.setTransactionId(appointment.getId()+"");
		order.setCurrency("INR");
		order.setRedirectUrl("http://localhost:8080/#/thankyou");
		
		InstamojoPaymentUrlPayload response = new InstamojoPaymentUrlPayload();
		
		try {
			PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
		    System.out.println(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
		    response.setPaymentUrl(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
		}catch (HTTPException e) {
		    System.out.println(e.getStatusCode());
		    System.out.println(e.getMessage());
		    System.out.println(e.getJsonPayload());

		} catch (ConnectionException e) {
		    System.out.println(e.getMessage());
		}
		
		return response;
		
	}
	
	
	public void appointmentEmail(String email, String subject, String text)throws MessagingException{
		MimeMessage mail = javamailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setFrom(emailConfig.getUsername());
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(text, true);
		javamailSender.send(mail);
	}
	
	
	@GetMapping("/getPaymentDetail/{payid}")
	public PaymentOrder getPaymentDetails(@PathVariable("payid") String paymentid) {
		ApiContext context = ApiContext.create("test_fJEPvVj8xs9l0dQw4VZPY5aktpKldUAilWc", "test_2ZW3fILEKuDDTCnuKYDVmDKDI7TGjuqzcVRIu87YbbirCCBf9BKSWw2I8sJ1t6scPhz5xtonhqiirkDpO3xdU3o02cxIiKk29d9DFyTQ0SFCyhzW4GZKtqtXKdl", ApiContext.Mode.TEST);
		Instamojo api = new InstamojoImpl(context);
		
		
		try {
		    PaymentOrder paymentOrder = api.getPaymentOrder(paymentid);
		    
		    PaymentDetailPayload detail = new PaymentDetailPayload(paymentOrder.getName(), paymentOrder.getTransactionId(), paymentOrder.getAmount(), paymentOrder.getDescription(), paymentOrder.getId());
		    
		    return paymentOrder;
		} catch (HTTPException e) {
		    System.out.println(e.getStatusCode());
		    System.out.println(e.getMessage());
		    System.out.println(e.getJsonPayload());
		    return null;
		} catch (ConnectionException e) {
		    System.out.println(e.getMessage());
		    return null;
		}
		
		
	}

}

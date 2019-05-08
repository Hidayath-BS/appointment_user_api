package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.model.ConsultationRequestResponses;
import org.zerhusen.ams.model.ConsultationRequests;
import org.zerhusen.ams.payload.ConsultationResponsePayload;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.ConsultationRequestRepository;
import org.zerhusen.ams.repository.ConsultationRequestResponsesRepository;
import org.zerhusen.security.JwtTokenUtil;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/consultation")
public class ConsultationRequestRest {

	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AmsPatientUsersRepository userRepo;
	
	@Autowired
	private ConsultationRequestRepository cosultationRepo;
	
	@Autowired
	private ConsultationRequestResponsesRepository responseRepo;
	
	@GetMapping("/MyConsultationRequest")
	public Iterable<ConsultationResponsePayload> getMyRequests(HttpServletRequest req){
		String token = req.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Ams_patient_users user = userRepo.findByEmail(username);
		
		List<ConsultationResponsePayload> result = new ArrayList<ConsultationResponsePayload>(); 
		
		Collection<ConsultationRequests> requests = cosultationRepo.findAll().stream().filter(i-> i.getPatient().equals(user) && i.isActive() == true).collect(Collectors.toList());
		
		for(ConsultationRequests request: requests) {
			
			
			List<ConsultationRequestResponses> response = responseRepo.findAll().stream().filter(i -> i.getRequest().equals(request) && i.isActive() == true).collect(Collectors.toList());
			
			ConsultationResponsePayload payload = new ConsultationResponsePayload();
			payload.setRequest(request);
			payload.setResponses(response);
			
			result.add(payload);
		}
		
		return result;
		
	}
	
	@PostMapping("/newConsultationrequest")
	public ResponseEntity<?> newConsultation(@RequestBody String request, HttpServletRequest req) throws JSONException{
		String token = req.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Ams_patient_users user = userRepo.findByEmail(username);
		
		JSONObject json = new JSONObject(request);
		
		LocalDate dateOfRequest = LocalDate.now();
		
		LocalTime requestTime = LocalTime.now();
		
		ConsultationRequests consultationRequest = new ConsultationRequests(json.getString("email"), json.getString("mobile_number"),
				json.getString("request") , dateOfRequest, requestTime, (byte) 1, true);
		
		if(user != null) {
			consultationRequest.setPatient(user);
			cosultationRepo.save(consultationRequest);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
		
		
	}
	
	
}

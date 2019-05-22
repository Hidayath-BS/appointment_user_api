package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsPatientConversation;
import org.zerhusen.ams.model.AmsPatientQueries;
import org.zerhusen.ams.model.AmsQueryResponse;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.payload.ConversationPaylod;
import org.zerhusen.ams.payload.QueryPayload;
import org.zerhusen.ams.repository.AmsPatientConversationRepository;
import org.zerhusen.ams.repository.AmsPatientQueryRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.AmsQueryResponseRepository;
import org.zerhusen.security.JwtTokenUtil;


@RestController
@CrossOrigin(origins="*")
public class QueriesRest {
	@Autowired
	AmsQueryResponseRepository queriesRepo;
	@Autowired
	private AmsPatientUsersRepository patientRepo;
	@Autowired
	public AmsPatientQueryRepository queryRepo;
	
	@Autowired
	public AmsPatientConversationRepository conversationRepo;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    @Qualifier("jwtUserDetailsService")
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private AmsPatientUsersRepository userRepo;


@GetMapping(value="/getConversations")
public Iterable<AmsPatientConversation> getConversations(HttpServletRequest req){
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = userRepo.findByEmail(username);
	
	return conversationRepo.findAll().stream().filter(i-> i.isActive() == true && i.getPatient().equals(user)).collect(Collectors.toList());
}

@GetMapping("/getConversationdetails/{id}")
public Iterable<QueryPayload> getQuery(@PathVariable("id") int id){
	
	AmsPatientConversation conversation = conversationRepo.findById(id);
	
	List<QueryPayload> result = new ArrayList<QueryPayload>();
	
	List<AmsPatientQueries> queries = queryRepo.findAll().stream().filter(i-> i.isActive()==true && i.getConversation().equals(conversation)).collect(Collectors.toList());
	
	
	for(AmsPatientQueries query: queries) {
		QueryPayload payload = new QueryPayload();
		
		payload.setQuery(query);
		
		List<AmsQueryResponse> response = queriesRepo.findAll().stream().filter(i-> i.isActive()==true && i.getQuery().equals(query)).collect(Collectors.toList());
		
		payload.setResponses(response);
		
		result.add(payload);
	}
	
	return result;
}
	
@GetMapping(value="/getQueries")
public Iterable<ConversationPaylod> getQueries(HttpServletRequest req){
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = userRepo.findByEmail(username);
	
	List<ConversationPaylod> res = new ArrayList<ConversationPaylod>();	
	
	
	List<AmsPatientConversation> conversation = conversationRepo.findAll().stream().filter(i-> i.isActive() == true && i.getPatient().equals(user)).collect(Collectors.toList());
	
	
	
	for(AmsPatientConversation conv: conversation) {
		ConversationPaylod convPay = new ConversationPaylod();
		
		List<QueryPayload> result = new ArrayList<QueryPayload>();	
		List<AmsPatientQueries> queries = queryRepo.findAll().stream().filter(i-> i.isActive() == true && i.getConversation().equals(conv)).collect(Collectors.toList());
		
		for(AmsPatientQueries query: queries) {
			QueryPayload payload = new QueryPayload();
			payload.setQuery(query);
			
			List<AmsQueryResponse> response = queriesRepo.findAll().stream().filter(i-> i.getQuery().equals(query) && i.isActive()==true).collect(Collectors.toList());
			
			payload.setResponses(response);
			
			result.add(payload);
		}
		
		convPay.setConversation(conv);
		convPay.setQueries(result);
		res.add(convPay);
	
	}

	return res;

}

@GetMapping("/getQueryResponse/{id}")
public Iterable<AmsQueryResponse> getQueryResponse(@PathVariable("id") int id){
	AmsPatientQueries query = queryRepo.findById(id);
	return queriesRepo.findAll().stream().filter(i-> i.isActive()==true && i.getQuery().equals(query)).collect(Collectors.toList());
}

@PostMapping("/postQuery")
public ResponseEntity<?> postQuery(@RequestBody String queries, HttpServletRequest req) throws JSONException{
	JSONObject obj = new JSONObject(queries);
	LocalDate queryDate = LocalDate.now(); 
	LocalTime queryTime = LocalTime.now();
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);

	AmsPatientConversation conversation = conversationRepo.findById(obj.getInt("conversation"));
	
	Ams_patient_users user = patientRepo.findByEmail(username);
	
	
	if(conversation != null && conversation.getPatient().equals(user)) {
		AmsPatientQueries ams= new AmsPatientQueries(obj.getString("query"),(byte)1,queryDate, queryTime, true);
		ams.setConversation(conversation);
		queryRepo.save(ams);
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}		
}

@PostMapping("/postNewConversation")
public ResponseEntity<?> createConversation(@RequestBody String query, HttpServletRequest req) throws JSONException{

	JSONObject json = new JSONObject(query);
	
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = patientRepo.findByEmail(username);
	
	LocalDate startDate = LocalDate.now();
	
	LocalTime startTime = LocalTime.now();
	
	AmsPatientConversation conversation = new AmsPatientConversation(startDate, startTime, json.getString("query"),true);
	
	conversation.setPatient(user);
	
	if(user != null) {
		AmsPatientConversation conv = conversationRepo.save(conversation);
		AmsPatientQueries askquery = new AmsPatientQueries(json.getString("query"), (byte) 1, startDate, startTime, true);
		askquery.setConversation(conv);
		queryRepo.save(askquery);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
}

}

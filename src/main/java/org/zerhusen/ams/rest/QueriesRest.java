package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.time.LocalTime;
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
import org.zerhusen.ams.model.AmsPatientQueries;
import org.zerhusen.ams.model.AmsQueryResponse;
import org.zerhusen.ams.model.Ams_patient_users;
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
	
	@Value("${jwt.header}")
	private String tokenHeader;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    @Qualifier("jwtUserDetailsService")
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private AmsPatientUsersRepository userRepo;



	
	
@GetMapping(value="/getQueries")
public Iterable<AmsPatientQueries> getQueries(HttpServletRequest req){
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = userRepo.findByEmail(username);
	return queryRepo.findAll().stream().filter(i-> i.isActive() == true && i.getPatient().equals(user)).collect(Collectors.toList());
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

	Ams_patient_users user = patientRepo.findByEmail(username);
	
	if(user != null) {
		AmsPatientQueries ams= new AmsPatientQueries(obj.getString("query"),(byte)1,queryDate, queryTime, true);
		ams.setPatient(user);
		queryRepo.save(ams);
		return new ResponseEntity<>(HttpStatus.OK);
	}else {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

		
}
}

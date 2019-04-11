package org.zerhusen.contollers.ams;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.repository.AmsAppointmentsRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.security.JwtTokenUtil;



@RestController
@RequestMapping("appointments")
@CrossOrigin(origins="*")
public class MyAppointmentRest {
	
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private AmsPatientUsersRepository userRepo;
    
    @Autowired
    private AmsAppointmentsRepository appointmentRepo;
	
    @GetMapping("/MyAppointments")
	public Iterable<AmsAppointments> getMyAppointments(HttpServletRequest req){
		String token = req.getHeader(tokenHeader).substring(7);
    	String username = jwtTokenUtil.getUsernameFromToken(token);
    	Ams_patient_users user = userRepo.findByEmail(username);
    	return appointmentRepo.findAll().stream().filter(i-> i.getPatientUser() != null  && i.getPatientUser().equals(user) ).collect(Collectors.toList());
	}
}

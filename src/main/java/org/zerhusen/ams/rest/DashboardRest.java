package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAppointments;
import org.zerhusen.ams.model.AmsAppointmentsForReview;
import org.zerhusen.ams.model.AmsDoctorSuggestion;
import org.zerhusen.ams.model.Ams_Services_available;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.model.security.User;
import org.zerhusen.ams.repository.AmsAppointmentForReviewRepository;
import org.zerhusen.ams.repository.AmsAppointmentsRepository;
import org.zerhusen.ams.repository.AmsDoctorSuggestionRepository;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.AmsServicesRepository;
import org.zerhusen.ams.repository.security.AuthorityRepository;
import org.zerhusen.ams.repository.security.UserRepository;
import org.zerhusen.security.JwtTokenUtil;


@RestController
@CrossOrigin(origins="*")
public class DashboardRest {
@Autowired
public AmsAppointmentsRepository appointmentRepo;

@Autowired
public AuthorityRepository roleRepo;

@Autowired
public AmsPatientUsersRepository userrepo;

@Autowired
public AmsServicesRepository serviceRepo;

@Autowired
private AmsDoctorSuggestionRepository doctorRepo;

@Value("${jwt.header}")
private String tokenHeader;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
@Qualifier("jwtUserDetailsService")
private UserDetailsService userDetailsService;


@Autowired
private AmsAppointmentForReviewRepository reviewRepository;


@GetMapping("/getPatientwiseAppointment")
public Iterable<AmsAppointments> getPatientwiseAppointment(HttpServletRequest req){
	
	LocalDate today = LocalDate.now();
	
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = userrepo.findByEmail(username);
	return appointmentRepo.findAll().stream().filter(i->i.isActive()== true && i.getPatientUser() != null && i.getPatientUser().equals(user) && (i.getDate().equals(today) || i.getDate().isAfter(today)) && i.isRescheduled() == false).collect(Collectors.toList());
}

@GetMapping("/getReviewDatesPatientWise")
public Iterable<AmsAppointmentsForReview> getReviewDatePatientWise(HttpServletRequest req){
	
	String token = req.getHeader(tokenHeader).substring(7);
	String username = jwtTokenUtil.getUsernameFromToken(token);
	Ams_patient_users user = userrepo.findByEmail(username);
	
	List<AmsAppointmentsForReview> result = new ArrayList<AmsAppointmentsForReview>();
	
	List<AmsAppointments> appointments = appointmentRepo.findAll().stream().filter(i->i.isActive()==true && i.getPatientUser()!=null && i.getPatientUser().equals(user)).collect(Collectors.toList());
	
	for(AmsAppointments appmnts : appointments) {
		
		AmsAppointmentsForReview review = reviewRepository.findByAppointment(appmnts);
		
		if(review != null) {
			result.add(review);
		}
		
	}
	
	return result;
	
}

@GetMapping(value="/getDoctors")
public Iterable<AmsDoctorSuggestion> getdoctors(){
	return doctorRepo.findAll().stream().filter(i->i.isActive()== true).collect(Collectors.toList());
}

@GetMapping("/getServices")
public Iterable<Ams_Services_available> getServices(){
	return serviceRepo.findAll().stream().filter(i->i.isActive()==true).collect(Collectors.toList());
}

}

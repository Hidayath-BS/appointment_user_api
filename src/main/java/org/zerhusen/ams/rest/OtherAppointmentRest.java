package org.zerhusen.ams.rest;

import java.time.LocalDate;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.model.OtherAppointments;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.OtherAppointmentsRepository;
import org.zerhusen.ams.repository.security.AuthorityRepository;
import org.zerhusen.security.JwtTokenUtil;

@CrossOrigin(origins="*")
@RestController
public class OtherAppointmentRest {
	
	@Autowired
	public AuthorityRepository roleRepo;

	@Autowired
	public AmsPatientUsersRepository userrepo;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private OtherAppointmentsRepository otherapprepo;
	
	@GetMapping("/getOtherAppointment")
	public Iterable<OtherAppointments> getPatientWiseOtherAppointments(HttpServletRequest req)
	{
		LocalDate today = LocalDate.now();
		String token = req.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Ams_patient_users user = userrepo.findByEmail(username);
		return otherapprepo.findAll().stream().filter(i->i.isActive()== true && i.getPatient() != null && i.getPatient().equals(user) && ( i.getAppointmentDate().isEqual(today) || i.getAppointmentDate().isAfter(today) ) ).collect(Collectors.toList());
	}

}

package org.zerhusen.security.controller;


import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.ams.repository.security.AuthorityRepository;
import org.zerhusen.ams.repository.security.UserRepository;
import org.zerhusen.security.JwtTokenUtil;
import org.zerhusen.service.PasswordEncoderCustom;



@CrossOrigin(origins="*")
@RestController
public class UserRestController {
	
	@Autowired
	private UserRepository userrepo;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AmsPatientUsersRepository userRepo;



    @Autowired
	PasswordEncoderCustom pswd;
    
    @Autowired
    AuthorityRepository authority;

    
    @GetMapping("/User")
    public Ams_patient_users getCurruntUser(HttpServletRequest req) {
    	String token = req.getHeader(tokenHeader).substring(7);
    	String username = jwtTokenUtil.getUsernameFromToken(token);
    	Ams_patient_users user = userRepo.findByEmail(username);
    	return user;
    }

    



}

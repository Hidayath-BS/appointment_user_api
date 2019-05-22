package org.zerhusen.ams.rest.masters;

import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsReferralDoctors;
import org.zerhusen.ams.repository.AmsReferralRepository;


@RestController
@RequestMapping("/masters")
@CrossOrigin(origins="*")
public class AmsReferralDoctorsRest {

	@Autowired
	public AmsReferralRepository amsDoctorReferral;
	
	@GetMapping("/allReferralDoctors")
	public Iterable<AmsReferralDoctors> getReferralDoctors(){
		return amsDoctorReferral.findAll().stream().filter(i-> i.isActive()==true).collect(Collectors.toList());
	}
}

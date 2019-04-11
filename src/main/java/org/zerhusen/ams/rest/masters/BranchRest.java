package org.zerhusen.ams.rest.masters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsHospitalBranch;
import org.zerhusen.ams.repository.AmsHospitalBranchRepository;

@RestController
@RequestMapping("/masters")
@CrossOrigin(origins="*")
public class BranchRest {

	
	@Autowired
	public AmsHospitalBranchRepository branchRepo;
	
	
	@GetMapping("/getBranches")
	public @ResponseBody Iterable<AmsHospitalBranch> getAllBranches(){
		return branchRepo.findAll().stream().filter(i-> i.isActive()==true).collect(Collectors.toList());
	}
}

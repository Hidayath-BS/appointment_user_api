package org.zerhusen.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerhusen.ams.model.Ams_patient_users;
import org.zerhusen.ams.repository.AmsPatientUsersRepository;
import org.zerhusen.security.JwtUserFactory;




@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    public AmsPatientUsersRepository userRepo;
    
    
    public JwtUserDetailsService(AmsPatientUsersRepository userRepo) {
    	this.userRepo = userRepo;
    }

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Ams_patient_users user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with mobilenumber '%s'.", email));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

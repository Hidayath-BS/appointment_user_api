package org.zerhusen.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerhusen.model.ams.Ams_patient_users;
import org.zerhusen.model.security.User;
import org.zerhusen.repository.ams.AmsPatientUsersRepository;
import org.zerhusen.security.JwtUserFactory;
import org.zerhusen.security.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AmsPatientUsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Ams_patient_users user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with mobilenumber '%s'.", email));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

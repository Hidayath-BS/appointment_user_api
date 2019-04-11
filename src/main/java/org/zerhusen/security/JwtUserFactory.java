package org.zerhusen.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.zerhusen.ams.model.AmsPatientAuthority;
import org.zerhusen.ams.model.Ams_patient_users;




public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Ams_patient_users user) {
        return new JwtUser(
                user.getId(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthority()),
                user.isActive(),
                user.getLastPasswordResetDate()
        );
    }

    
    
    
    private static List<GrantedAuthority> mapToGrantedAuthorities(Collection<AmsPatientAuthority> collection) {
        return collection.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());
    }
    
    
}

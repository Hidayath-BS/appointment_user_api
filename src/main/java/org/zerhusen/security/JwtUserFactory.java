package org.zerhusen.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.zerhusen.model.ams.AmsPatientAuthority;
import org.zerhusen.model.ams.Ams_patient_users;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.User;

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

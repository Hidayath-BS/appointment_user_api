package org.zerhusen.ams.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.ams.model.security.Authority;





public interface Rolerepository extends JpaRepository<Authority, Long> {
	Authority findByAuthority(String role);
}

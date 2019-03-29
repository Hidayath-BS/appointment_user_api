package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.Authority;


public interface Rolerepository extends JpaRepository<Authority, Long> {
	Authority findByAuthority(String role);
}

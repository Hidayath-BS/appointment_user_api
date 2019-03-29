package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	public Authority findByAuthority(String authority);
	
	public Authority findById(Long id);

}

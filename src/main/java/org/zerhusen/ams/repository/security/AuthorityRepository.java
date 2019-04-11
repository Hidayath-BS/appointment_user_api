package org.zerhusen.ams.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.ams.model.security.Authority;




public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	public Authority findByAuthority(String authority);
	
	public Authority findById(Long id);

}

package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsDoctorSuggestion;




@Repository
public interface AmsDoctorSuggestionRepository extends JpaRepository<AmsDoctorSuggestion, Integer> {

}

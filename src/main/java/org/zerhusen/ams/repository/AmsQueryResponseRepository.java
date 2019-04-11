package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsQueryResponse;




@Repository
public interface AmsQueryResponseRepository extends JpaRepository<AmsQueryResponse, Integer> {

}

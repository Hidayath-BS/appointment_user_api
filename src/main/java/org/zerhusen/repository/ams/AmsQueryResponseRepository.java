package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsQueryResponse;

@Repository
public interface AmsQueryResponseRepository extends JpaRepository<AmsQueryResponse, Integer> {

}

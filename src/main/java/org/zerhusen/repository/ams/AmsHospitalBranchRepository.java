package org.zerhusen.repository.ams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.model.ams.AmsHospitalBranch;

@Repository
public interface AmsHospitalBranchRepository extends JpaRepository<AmsHospitalBranch, Integer>{

	public AmsHospitalBranch findById(int id);
}

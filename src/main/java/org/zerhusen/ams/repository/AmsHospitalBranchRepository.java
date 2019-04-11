package org.zerhusen.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.ams.model.AmsHospitalBranch;



@Repository
public interface AmsHospitalBranchRepository extends JpaRepository<AmsHospitalBranch, Integer>{

	public AmsHospitalBranch findById(int id);
}

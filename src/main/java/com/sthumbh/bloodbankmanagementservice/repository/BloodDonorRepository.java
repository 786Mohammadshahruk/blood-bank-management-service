package com.sthumbh.bloodbankmanagementservice.repository;

import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodDonorRepository extends JpaRepository<BloodStockDetails,Long> {

}

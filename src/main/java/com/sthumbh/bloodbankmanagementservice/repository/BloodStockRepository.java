package com.sthumbh.bloodbankmanagementservice.repository;

import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStockRequest, Long> {
}

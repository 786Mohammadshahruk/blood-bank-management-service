package com.sthumbh.bloodbankmanagementservice.entitiy;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "blood-stocks-details")
public class BloodStockRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodType;
    private LocalDateTime lastUpdated;
    private String organizationType;
    private String category;
    private String stateCode;
    private String districtCode;
}

package com.sthumbh.bloodbankmanagementservice.entitiy;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "blood-stock-details")
@Builder
public class BloodStockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String bloodType;
    private String[] donateTo;
    private String[] receiveFrom;

}

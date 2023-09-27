package com.sthumbh.bloodbankmanagementservice.Enum;

public enum BloodEnum {
    A_POSITIVE("A+"),
    B_POSITIVE("B+"),
    O_POSITIVE("O+"),
    AB_POSITIVE("AB+"),
    A_NEGITIVE("A-"),
    B_NEGITIVE("B-"),
    O_NEGITIVE("O-"),
    AB_NEGITIVE("AB-");

    BloodEnum(String name){
        this.name();
    }
}

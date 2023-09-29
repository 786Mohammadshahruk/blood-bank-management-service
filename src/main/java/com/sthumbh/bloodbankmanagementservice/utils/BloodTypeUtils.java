package com.sthumbh.bloodbankmanagementservice.utils;

import java.util.Arrays;
import java.util.List;

public class BloodTypeUtils {

    public static List<String> getBloodsType() {
        return Arrays.asList("A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-");
    }
}

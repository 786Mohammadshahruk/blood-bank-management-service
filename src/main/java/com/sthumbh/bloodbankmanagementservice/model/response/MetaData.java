package com.sthumbh.bloodbankmanagementservice.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MetaData {

    private String status;
    private String code;
    private String message;
    private String version;

}


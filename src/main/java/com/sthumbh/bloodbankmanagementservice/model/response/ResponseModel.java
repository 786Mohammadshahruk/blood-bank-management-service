package com.sthumbh.bloodbankmanagementservice.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseModel {
    private MetaData metaData;
    private ResourceData resourceData;
}

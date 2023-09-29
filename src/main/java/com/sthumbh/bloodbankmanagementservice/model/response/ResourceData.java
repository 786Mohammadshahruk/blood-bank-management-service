package com.sthumbh.bloodbankmanagementservice.model.response;


import lombok.Data;

@Data
public class ResourceData<T> {
    private T data;
}

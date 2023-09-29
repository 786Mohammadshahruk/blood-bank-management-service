package com.sthumbh.bloodbankmanagementservice.exception;

import com.sthumbh.bloodbankmanagementservice.model.response.StatusCodes;
import com.sthumbh.bloodbankmanagementservice.model.response.MetaData;
import com.sthumbh.bloodbankmanagementservice.model.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UnknownBloodException.class)
    public ResponseEntity<ResponseModel> unknownBloodException(UnknownBloodException exception) {
        MetaData metaData = MetaData.builder()
                .code(StatusCodes.BUSINESS_ERROR_CODE)
                .message(exception.getMessage())
                .status(StatusCodes.BUSINESS_ERROR_STATUS)
                .version("1.0")
                .build();
        ResponseModel responseModel = ResponseModel.builder().metaData(metaData).build();
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }
}

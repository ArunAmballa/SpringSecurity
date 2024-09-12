package com.arun.SpringSecurity.appSecurity.advices;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ApiResponse<T>{

    private T data;

    private ApiError apiError;

    private LocalDateTime timestamp;


    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError){
        this();
        this.apiError = apiError;
    }

    public ApiResponse(T data,ApiError apiError,LocalDateTime timestamp){
        this.timestamp=timestamp;
        this.data=data;
        this.apiError=apiError;
    }


}

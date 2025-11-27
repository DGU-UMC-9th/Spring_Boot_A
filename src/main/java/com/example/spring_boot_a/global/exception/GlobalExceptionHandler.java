package com.example.spring_boot_a.global.exception;

import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.global.apiPayload.code.BaseErrorCode;
import com.example.spring_boot_a.global.apiPayload.code.GeneralErrorCode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException e) {
        BaseErrorCode errorCode = e.getErrorCode();

        ApiResponse<Object> body = ApiResponse.onFailure(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEntityNotFound(EntityNotFoundException e) {

        ApiResponse<Object> body = ApiResponse.onFailure(
                GeneralErrorCode.NOT_FOUND,
                e.getMessage()    // result에 상세 메시지를 실어 줄 수 있음
        );

        return ResponseEntity
                .status(GeneralErrorCode.NOT_FOUND.getStatus())
                .body(body);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ApiResponse<Object> body = ApiResponse.onFailure(
                GeneralErrorCode.BAD_REQUEST,
                msg   // result에 검증 실패 메시지
        );

        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(body);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleIntegrity(DataIntegrityViolationException e) {

        ApiResponse<Object> body = ApiResponse.onFailure(
                GeneralErrorCode.BAD_REQUEST,
                "데이터 제약 조건 위반(중복 등)"
        );

        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(body);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {

        ApiResponse<Object> body = ApiResponse.onFailure(
                GeneralErrorCode.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );

        return ResponseEntity
                .status(GeneralErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(body);
    }
}

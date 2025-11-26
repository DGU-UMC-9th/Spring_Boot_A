package com.example.spring_boot_a.global.apiPayload.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;


    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true,
                GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(),
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }


    public static <T> ApiResponse<T> onFailure(BaseErrorCode code) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), null);
    }

    @Builder
    public record PageResponse<T>(
            List<T> content,
            int page,
            int size,
            long totalElements,
            int totalPages
    ){}
}

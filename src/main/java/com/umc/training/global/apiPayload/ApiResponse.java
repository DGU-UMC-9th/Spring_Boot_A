package com.umc.training.global.apiPayload;

import com.umc.training.global.apiPayload.code.BaseErrorCode;
import com.umc.training.global.apiPayload.code.GeneralSuccessCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final Boolean isSuccess;

    private final String code;

    private final String message;

    private final T result;

    public ApiResponse(Boolean isSuccess, String code, String message, T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, GeneralSuccessCode.OK.getCode(), GeneralSuccessCode.OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> onSuccess(GeneralSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }


    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}

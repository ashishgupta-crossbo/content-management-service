package com.cms.service.dto.response;

import com.cms.service.dto.request.HotelRequestDto;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@Introspected
@AllArgsConstructor
@Serdeable
@Builder
public class CommonResponse <T>{
    private boolean success;
    private T data;
    private ApiError error;

    public CommonResponse() {
        this.error = null;
        this.success = true;
        this.data = null;
    }
    public CommonResponse(T responseData) {
        this.error = null;
        this.success = true;
        this.data = responseData;
    }
}

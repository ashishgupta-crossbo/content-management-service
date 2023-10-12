package com.cms.service.dto.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class HotelRequestDto {

    @NotBlank(message = "hotel code is required")
    private String hotelCode;

    @NotBlank(message = "hotel name is required")
    private String hotelName;

    @NotBlank(message = "continent can not be null or empty")
    private String continent;

    @NotBlank(message = "region can not be null or empty")
    private String region;

    @NotBlank(message = "url can not be null or empty")
    private String factSheetUrl;
}

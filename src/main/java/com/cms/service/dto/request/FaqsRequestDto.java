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
public class FaqsRequestDto {

    @NotBlank(message = "headline can not be null or empty")
    private String headline;

    @NotBlank(message = "description can not be null or empty")
    private String description;
}

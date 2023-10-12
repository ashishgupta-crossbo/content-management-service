package com.cms.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
@Builder
public class GetFaqResponseDto {

    @JsonProperty("faq_id")
    private long faqId;
    private String headline;
    private String description;
}

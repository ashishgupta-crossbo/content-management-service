package com.cms.service.models;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
@Introspected
@Entity
@ToString
@Table(name = "hotel",schema = "CMS")
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "hotel_code")
    private String hotelCode;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "continent")
    private String continent;

    @Column(name = "region")
    private String region;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "fact_sheet_url")
    private String factSheetUrl;
}

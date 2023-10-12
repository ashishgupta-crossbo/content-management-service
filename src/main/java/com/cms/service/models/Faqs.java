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
@Table(name = "faqs",schema = "CMS")
public class Faqs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "faq_id")
    private long id;

    @Column(name = "headline")
    private String headline;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}

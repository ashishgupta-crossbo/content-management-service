package com.cms.service.repository;

import com.cms.service.dto.response.GetFaqResponseDto;
import com.cms.service.models.Faqs;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface FaqsRepository extends CrudRepository<Faqs,Long> {

    Faqs save(@NonNull Faqs faqs);


    @Query(value = "SELECT x.faq_id, x.headline, x.description FROM CMS.faqs x")
    List<GetFaqResponseDto> getAllFaqs();

    @Query(value = "UPDATE CMS.faqs SET " +
            "updated_at = :updatedAt, " +
            "description = :description, " +
            "headline = :headline, " +
            "created_at = :createdAt, " +
            "is_active = :isActive " +
            "WHERE faq_id = :id")
    int updateFaq(long id, String updatedAt, String description, String headline, String createdAt, boolean isActive);

    @Query(value = "UPDATE CMS.faqs SET is_active = true WHERE faq_id = :id")
    int updateIsActive(long id);
}

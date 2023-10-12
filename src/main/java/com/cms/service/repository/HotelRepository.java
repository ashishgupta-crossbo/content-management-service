package com.cms.service.repository;

import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.models.Hotels;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.Transactional;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface HotelRepository extends CrudRepository<Hotels,Long> {

    Hotels save(@NonNull Hotels hotel);


    @Query("SELECT h.hotel_code, h.hotel_name, h.continent, h.region, h.fact_sheet_url FROM CMS.hotel h " +
            "WHERE (:continent IS NULL OR h.continent = :continent) " +
            "AND (:region IS NULL OR h.region = :region)")
    List<HotelRequestDto> getHotelsByContinentOrRegion(@Nullable String continent, @Nullable String region);

    @Transactional
    @Query(value = "UPDATE CMS.hotel " +
            "SET " +
            "hotel_code = :hotelCode, " +
            "hotel_name = :hotelName, " +
            "region = :region, " +
            "continent = :continent, " +
            "fact_sheet_url = :factSheetUrl " +
            "WHERE id = :id")
    int updateHotel(Long id,String hotelCode,String hotelName,String region,String continent,String factSheetUrl);

    Hotels findByHotelCode(String hotelCode);

    @Query(value = "UPDATE CMS.hotel SET is_active=true where hotel_code = :hotelCode")
    void updateIsActive(String hotelCode);
}

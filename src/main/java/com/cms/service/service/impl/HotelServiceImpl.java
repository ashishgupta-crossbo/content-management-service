package com.cms.service.service.impl;

import com.cms.service.common.ErrorCode;
import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.exceptions.CustomException;
import com.cms.service.models.Hotels;
import com.cms.service.repository.HotelRepository;
import com.cms.service.service.HotelService;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
@Primary
public class HotelServiceImpl implements HotelService {
    @Inject
    private final HotelRepository hotelRepository;

    @Inject
    private final HotelService hotelService;

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class.getName());


    public HotelServiceImpl(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
    hotelService = new HotelByRegion(new HotelsByContinent(new GetAllHotels( this.hotelRepository), this.hotelRepository), this.hotelRepository);
    }
    @Override
    public void createHotel(HotelRequestDto hotelRequestDto) {
        try {
            saveHotelsInDb(hotelRequestDto);
        }catch (Exception e){
            logger.info("Getting error in createHotel function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }

    private void saveHotelsInDb(HotelRequestDto hotelRequestDto) {
     Hotels hotels = Hotels.builder().hotelCode(hotelRequestDto.getHotelCode()).hotelName(hotelRequestDto.getHotelName()).region(
             hotelRequestDto.getRegion()).continent(hotelRequestDto.getContinent()).createdAt(LocalDateTime.now().
             toString()).updatedAt(LocalDateTime.now().toString()).isActive(false).factSheetUrl(hotelRequestDto.getFactSheetUrl()).build();
     hotelRepository.save(hotels);
    }

    @Override
    public List<HotelRequestDto> getAllHotels(String continent, String origin) {
        try {
            return hotelService.getAllHotels(continent,origin);
        }catch (Exception e){
            logger.info("Getting error in getAllHotels function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }

    @Override
    public void updateHotel(long id, HotelRequestDto hotelRequestDto) {
        try {
            Optional<Hotels> existingHotel = hotelRepository.findById(id);
            if (existingHotel.isEmpty()) {
                throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
            }
            Hotels hotels = updateHotels(hotelRequestDto);
            hotelRepository.updateHotel(id,hotels.getHotelCode(),hotels.getHotelName(), hotelRequestDto.getRegion(), hotelRequestDto.getContinent(), hotels.getFactSheetUrl());
        }catch (Exception e){
            logger.info("Getting error in updateHotel function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
            }

    private Hotels updateHotels(HotelRequestDto hotelRequestDto) {
            return Hotels.builder().hotelCode(hotelRequestDto.getHotelCode()).hotelName(hotelRequestDto.getHotelName()).region(
                    hotelRequestDto.getRegion()).continent(hotelRequestDto.getContinent()).updatedAt(LocalDateTime.now().
                    toString()).isActive(false).factSheetUrl(hotelRequestDto.getFactSheetUrl()).build();
    }

    @Override
    public void deleteHotel(String hotelCode) {
        try {
           Hotels existingHotel = hotelRepository.findByHotelCode(hotelCode);
            if (existingHotel!=null) {
                hotelRepository.updateIsActive(existingHotel.getHotelCode());
                logger.info("existing hotel record after update {}",existingHotel);
            }else {
                throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
            }
        }catch (Exception e){
            logger.info("Getting error in deleteHotel function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }
}

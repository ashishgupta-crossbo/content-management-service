package com.cms.service.service.impl;

import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.repository.HotelRepository;
import com.cms.service.service.HotelService;
import jakarta.inject.Inject;

import java.util.List;


public class GetAllHotels implements HotelService {

    @Inject
    private HotelRepository hotelRepository;


    public GetAllHotels(HotelRepository hotelRepository) {
        this.hotelRepository=hotelRepository;
    }
    @Override
    public void createHotel(HotelRequestDto hotelRequestDto) {

    }

    @Override
    public List<HotelRequestDto> getAllHotels(String continent, String origin) {
       return hotelRepository.getHotelsByContinentOrRegion(continent,origin);
    }

    @Override
    public void updateHotel(long id, HotelRequestDto hotelRequestDto) {

    }

    @Override
    public void deleteHotel(String hotelCode) {

    }
}

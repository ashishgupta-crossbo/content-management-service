package com.cms.service.service.impl;

import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.repository.HotelRepository;
import com.cms.service.service.HotelService;
import jakarta.inject.Inject;

import java.util.List;

public class HotelsByContinent implements HotelService {

    @Inject
    private HotelService hotelService;

    @Inject
    private HotelRepository hotelRepository;


    public HotelsByContinent(HotelService hotelService, HotelRepository hotelRepository) {
        this.hotelService = hotelService;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void createHotel(HotelRequestDto hotelRequestDto) {
    }

    @Override
    public List<HotelRequestDto> getAllHotels(String continent, String origin) {
        if (continent != null) {
            return hotelRepository.getHotelsByContinentOrRegion(continent,origin);
        } else {
            return hotelService.getAllHotels(continent, origin);
        }
    }

    @Override
    public void updateHotel(long id, HotelRequestDto hotelRequestDto) {
    }

    @Override
    public void deleteHotel(String hotelCode) {

    }
}

package com.cms.service.service;

import com.cms.service.dto.request.HotelRequestDto;

import java.util.List;

public interface HotelService {
    void createHotel(HotelRequestDto hotelRequestDto);

    List<HotelRequestDto> getAllHotels(String continent,String origin);

    void updateHotel(long id, HotelRequestDto hotelRequestDto);

    void deleteHotel(String hotelCode);
}

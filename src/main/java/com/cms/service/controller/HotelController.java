package com.cms.service.controller;

import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.dto.response.CommonResponse;
import com.cms.service.repository.HotelRepository;
import com.cms.service.service.HotelService;
import com.cms.service.service.impl.GetAllHotels;
import com.cms.service.service.impl.HotelByRegion;
import com.cms.service.service.impl.HotelsByContinent;
import com.cms.service.validation.Validator;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@ExecuteOn(TaskExecutors.IO)
public class HotelController {

    @Inject
    private final HotelService hotelService;

    @Inject
    private final Validator hotelValidator;

    public HotelController(HotelService hotelService, Validator hotelValidator) {
        this.hotelService = hotelService;
        this.hotelValidator = hotelValidator;
    }


    @Post(value = "/hotel", processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<String>> create(@Valid @Body HotelRequestDto hotelRequestDto) {
        hotelValidator.validate(hotelRequestDto);
        hotelService.createHotel(hotelRequestDto);
      return HttpResponse.ok(new CommonResponse<>());
    }

    @Get(value = "/hotels",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<List<HotelRequestDto>>>getHotels(@QueryValue @Nullable String continent, @QueryValue @Nullable String region){
       List<HotelRequestDto> hotelRequestDto= hotelService.getAllHotels(continent,region);
        return HttpResponse.ok(new CommonResponse<>(hotelRequestDto));
    }

    @Put(value = "/hotel/{id}",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<String>>updateHotel(@PathVariable long id, @Valid @Body HotelRequestDto hotelRequestDto){
        hotelValidator.validate(hotelRequestDto);
        hotelService.updateHotel(id,hotelRequestDto);
        return HttpResponse.ok(new CommonResponse<>());
    }

    @Delete(value = "/hotel",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<String>>deleteHotel(@QueryValue String hotelCode){
        hotelService.deleteHotel(hotelCode);
        return HttpResponse.ok(new CommonResponse<>());
    }

}

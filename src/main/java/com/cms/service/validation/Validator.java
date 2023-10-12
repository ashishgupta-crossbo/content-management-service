package com.cms.service.validation;
import com.cms.service.common.ErrorCode;
import com.cms.service.dto.request.FaqsRequestDto;
import com.cms.service.dto.request.HotelRequestDto;
import com.cms.service.exceptions.CustomException;
import jakarta.inject.Singleton;

@Singleton
public class Validator {

    public void validate(HotelRequestDto hotelRequestDto) {
        if (hotelRequestDto.getHotelCode()==null || hotelRequestDto.getHotelName()==null){
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }


    public void validateFaq(FaqsRequestDto faqsRequestDto) {
        if (faqsRequestDto.getDescription()==null || faqsRequestDto.getHeadline()==null){
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }
}

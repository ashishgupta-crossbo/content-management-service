package com.cms.service.service;

import com.cms.service.dto.request.FaqsRequestDto;
import com.cms.service.dto.response.CreateFaqResponseDto;
import com.cms.service.dto.response.FaqListResponseDto;

public interface FaqsService {
    CreateFaqResponseDto createFaq(FaqsRequestDto faqsRequestDto);

    FaqListResponseDto getAllFaq();

    void updateFaq(FaqsRequestDto faqsRequestDto, long id);

    void deleteFaq(long id);
}

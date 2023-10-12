package com.cms.service.controller;

import com.cms.service.dto.request.FaqsRequestDto;
import com.cms.service.dto.response.CommonResponse;
import com.cms.service.dto.response.CreateFaqResponseDto;
import com.cms.service.dto.response.FaqListResponseDto;
import com.cms.service.service.FaqsService;
import com.cms.service.validation.Validator;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@Controller
public class FaqsController {

    @Inject
    private final FaqsService faqService;

    @Inject
    private final Validator validator;

    public FaqsController(FaqsService faqService, Validator validator) {
        this.faqService = faqService;
        this.validator = validator;
    }

    @Post(value = "/faq", processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<CreateFaqResponseDto>> createFaq(@Valid @Body FaqsRequestDto faqsRequestDto) {
        validator.validateFaq(faqsRequestDto);
      CreateFaqResponseDto createFaqResponseDto= faqService.createFaq(faqsRequestDto);
        return HttpResponse.ok(new CommonResponse<>(createFaqResponseDto));
    }

    @Get(value = "/faqs",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<FaqListResponseDto>>getAllFaqs(){
       FaqListResponseDto faqListResponseDto= faqService.getAllFaq();
        return HttpResponse.ok(new CommonResponse<>(faqListResponseDto));
    }

    @Put(value = "/faq/{id}",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<String>> updateFaq(@Valid @Body FaqsRequestDto faqsRequestDto, @PathVariable long id){
        validator.validateFaq(faqsRequestDto);
        faqService.updateFaq(faqsRequestDto,id);
        return HttpResponse.ok(new CommonResponse<>());
    }

    @Delete(value = "/faq",processes = MediaType.APPLICATION_JSON)
    public HttpResponse<CommonResponse<String>>deleteFaq(@QueryValue long id){
        faqService.deleteFaq(id);
        return HttpResponse.ok(new CommonResponse<>());
    }
}

package com.cms.service.service.impl;

import com.cms.service.common.ErrorCode;
import com.cms.service.dto.request.FaqsRequestDto;
import com.cms.service.dto.response.CreateFaqResponseDto;
import com.cms.service.dto.response.FaqListResponseDto;
import com.cms.service.dto.response.GetFaqResponseDto;
import com.cms.service.exceptions.CustomException;
import com.cms.service.models.Faqs;
import com.cms.service.repository.FaqsRepository;
import com.cms.service.service.FaqsService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class FaqServiceImpl implements FaqsService {

    private static final Logger logger = LoggerFactory.getLogger(FaqServiceImpl.class.getName());
    @Inject
    private final FaqsRepository faqsRepository;

    public FaqServiceImpl(FaqsRepository faqsRepository) {
        this.faqsRepository = faqsRepository;
    }

    @Override
    public CreateFaqResponseDto createFaq(FaqsRequestDto faqsRequestDto) {
        Faqs faqs;
        try {
            faqs = saveValuesInDb(faqsRequestDto);
            faqsRepository.save(faqs);
        } catch (Exception e) {
            logger.info("Error Occurred in createFaq function {}", e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        return CreateFaqResponseDto.builder().faqId(faqs.getId()).build();
    }

    private Faqs saveValuesInDb(FaqsRequestDto faqsRequestDto) {
        return Faqs.builder().description(faqsRequestDto.getDescription()).updatedAt(LocalDateTime.now().toString()).createdAt(LocalDateTime.now().toString()).headline(faqsRequestDto.getHeadline()).isActive(false).build();
    }

    @Override
    public FaqListResponseDto getAllFaq() {
        try {
            List<GetFaqResponseDto> faqList= faqsRepository.getAllFaqs();
            if (faqList.isEmpty()){
                throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
            }
            return new FaqListResponseDto(faqList);
        }catch (Exception e){
            logger.info("Error Occurred in getAllFaq function {}", e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }

    }

    @Override
    public void updateFaq(FaqsRequestDto faqsRequestDto, long id) {
        try {
            Optional<Faqs> existingFaq = faqsRepository.findById(id);
            if (existingFaq.isEmpty()) {
                throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
            }
            Faqs faqs=saveValuesInDb(faqsRequestDto);
            faqsRepository.updateFaq(id,faqs.getUpdatedAt(),faqs.getDescription(),faqs.getHeadline(),faqs.getCreatedAt(),faqs.getIsActive());

        }catch (Exception e){
            logger.info("Getting error in updateFaq function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);

        }

    }

    @Override
    public void deleteFaq(long id) {
        try {
            Optional<Faqs> faqsOptional = faqsRepository.findById(id);
            if (faqsOptional.isPresent()) {
                Faqs existingFaq = faqsOptional.get();
                logger.info("Existing faq before delete {}",existingFaq);
                faqsRepository.updateIsActive(existingFaq.getId());
                logger.info("FAQ record after delete: {}", existingFaq);
            } else {
                throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
            }
        }catch (Exception e){
            logger.info("Getting error in deleteFaq function {}",e.getMessage());
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }

    }

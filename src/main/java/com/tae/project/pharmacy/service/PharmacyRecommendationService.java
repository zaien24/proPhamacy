package com.tae.project.pharmacy.service;


import com.tae.project.api.dto.DocumentDto;
import com.tae.project.api.dto.KakaoApiResponseDto;
import com.tae.project.api.service.KakaoAddressSearchService;
import com.tae.project.direction.entity.Direction;
import com.tae.project.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService.recommendPharmacyList fail] Input address: {}", address);
            return ;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        //List<Direction> directionList = directionService.buildDirectionList(documentDto); // 공공기관 약국 데이터
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);  // 카카오 약국 데이터

        directionService.saveAll(directionList);

    }
}
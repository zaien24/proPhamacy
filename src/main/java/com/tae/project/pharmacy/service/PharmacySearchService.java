package com.tae.project.pharmacy.service;

import com.tae.project.pharmacy.dto.PharmacyDto;
import com.tae.project.pharmacy.entity.Pharmacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyRepositoryService pharmacyRepositoryService;

    public List<PharmacyDto> searchPharmacyDtoList() {

        // redis

        // db
//        return pharmacyRepositoryService.findAll() // entity -> dto 변환
//                .stream()
//                .map(entity -> convertToPharmacyDto(entity))
//                .collect(Collectors.toList());
        return pharmacyRepositoryService.findAll() // entity -> dto 변환
                .stream()
                .map(this::convertToPharmacyDto)
                .collect(Collectors.toList());
    }

    //entity -> dto 변환
    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {

        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyName(pharmacy.getPharmacyName())
                .pharmacyAddress(pharmacy.getPharmacyAddress())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build();
    }
}
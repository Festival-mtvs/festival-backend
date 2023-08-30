package com.midnights.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midnights.demo.aggregate.dto.festival.FestivalDTO;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.repository.FestivalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private String hostArea;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.festivalRepository = festivalRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;

    }

    // 해당 지역에 포함 된 festival List 조회
    public List<FestivalDTO> findAllFestivalByHostArea(Pageable pageable) {

        List<Festival> festivals = festivalRepository.findFestivalsByHostArea(String.valueOf(Sort.by(Sort.Direction.DESC, "hostArea")));

        return festivals.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
    }

    // 상세 조회
    public FestivalDTO findFestivalByNo(Long festivalNo) {

        Festival findFestival = festivalRepository.findFestivalByFestivalNo(festivalNo);

        return modelMapper.map(findFestival, FestivalDTO.class);
    }

}

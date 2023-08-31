package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.admin.RequestCreateFestival;
import com.midnights.demo.aggregate.dto.admin.RequestEditFestival;
import com.midnights.demo.aggregate.dto.admin.ResponseEditFestival;
import com.midnights.demo.aggregate.dto.admin.ResponseReadFestival;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final FestivalRepository festivalRepository;

    @Transactional
    public void createFestival(RequestCreateFestival requestCreateFestival) {
        Festival festival = Festival.toEntity(requestCreateFestival);

        festivalRepository.save(festival);
    }

    @Transactional(readOnly = true)
    public ResponseReadFestival readFestival(Pageable pageable) {
        Page<Festival> festivals = festivalRepository.findAll(pageable);

        return new ResponseReadFestival(festivals);
    }

    @Transactional
    public void editFestival(RequestEditFestival requestEditFestival, Long id) {
        Festival festival = festivalRepository.findFestivalByFestivalNo(id);
        festival.update(requestEditFestival);
    }

    @Transactional
    public void deleteFestival(Long id) {
        Festival festival = festivalRepository.findFestivalByFestivalNo(id);
        festivalRepository.delete(festival);
    }
}

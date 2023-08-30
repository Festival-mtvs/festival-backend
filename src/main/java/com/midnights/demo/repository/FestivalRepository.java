package com.midnights.demo.repository;

import com.midnights.demo.aggregate.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

//    List<Festival> findFestivalsByHostArea(String hostArea);

    Page<Festival> findFestivalByHostArea(String hostArea, Pageable pageable);


    Festival findFestivalByFestivalNo(Long festivalNo);
}

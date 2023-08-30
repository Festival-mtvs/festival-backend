package com.midnights.demo.repository;

import com.midnights.demo.aggregate.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
}

package com.midnights.demo.repository;

import com.midnights.demo.aggregate.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByMemberId(String id);

    List<Heart> findAllByMemberId(String id);
}

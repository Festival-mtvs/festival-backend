package com.midnights.demo.repository;

import com.midnights.demo.aggregate.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Point findByMemberId(String id);
}

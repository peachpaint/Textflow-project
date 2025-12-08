package org.example.textflowproject.repository;

import org.example.textflowproject.domain.point.PointsLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointsLedgerRepository extends JpaRepository<PointsLedger, Long> {
  List<PointsLedger> findByUser_UserIdOrderByCreatedAtDesc(Long userId);
}

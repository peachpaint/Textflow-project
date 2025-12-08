package org.example.textflowproject.repository;

import org.example.textflowproject.domain.point.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
  Optional<UserBalance> findByUser_UserId(Long userId);
}

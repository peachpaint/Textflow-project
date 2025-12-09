package org.example.textflowproject.domain.point;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
  Optional<UserBalance> findByUser_UserId(Long userId);
}

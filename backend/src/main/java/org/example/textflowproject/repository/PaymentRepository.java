package org.example.textflowproject.repository;

import org.example.textflowproject.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> findByUser_UserIdOrderByCreatedAtDesc(Long userId);
}

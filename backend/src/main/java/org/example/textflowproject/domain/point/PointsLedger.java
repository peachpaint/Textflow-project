package org.example.textflowproject.domain.point;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.user.User;
import org.example.textflowproject.domain.payment.Payment;

import java.time.LocalDateTime;

@Entity
@Table(name = "points_ledger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointsLedger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ledger_id")
  private Long ledgerId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private Long delta;

  @Column(name = "balance_after", nullable = false)
  private Long balanceAfter;

  @Column(nullable = false, length = 100)
  private String reason;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reference_payment_id")
  private Payment referencePayment;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
}

package org.example.textflowproject.domain.access;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.user.User;
import org.example.textflowproject.domain.episode.Episode;
import org.example.textflowproject.domain.payment.Payment;
import org.example.textflowproject.domain.point.PointsLedger;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_episode_access")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class UserEpisodeAccess {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "access_id")
  private Long accessId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id", nullable = false)
  private Episode episode;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, length = 20)
  private AccessType type;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ledger_id")
  private PointsLedger ledger;

  @Column(name = "purchased_at", nullable = false)
  private LocalDateTime purchasedAt;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;

  @Column(nullable = false)
  private Boolean active;

  @PrePersist
  public void prePersist() {
    purchasedAt = LocalDateTime.now();
    if (active == null) {
      active = true;
    }
  }
}

package org.example.textflowproject.domain.point;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_balances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBalance {
  @Id
  @Column(name = "user_id")
  private Long userId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false)
  private Long balance;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}



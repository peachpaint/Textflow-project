package org.example.textflowproject.domain.Like;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.user.User;
import org.example.textflowproject.domain.episode.Episode;

import java.time.LocalDateTime;

@Entity
@Table(name = "episode_likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodeLike {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "like_id")
  private Long likeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id", nullable = false)
  private Episode episode;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
}

package org.example.textflowproject.domain.metrics;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.episode.Episode;

import java.time.LocalDate;

@Entity
@Table(name = "episode_daily_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodeDailyMetrics {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id", nullable = false)
  private Episode episode;

  @Column(name = "metric_date", nullable = false)
  private LocalDate metricDate;

  @Column(name = "view_count", nullable = false)
  private Long viewCount;

  @Column(name = "like_count", nullable = false)
  private Long likeCount;

  @Column(name = "comment_count", nullable = false)
  private Long commentCount;

  @PrePersist
  public void prePersist() {
    if (viewCount == null) {
      viewCount = 0L;
    }
    if (likeCount == null) {
      likeCount = 0L;
    }
    if (commentCount == null) {
      commentCount = 0L;
    }
  }
}

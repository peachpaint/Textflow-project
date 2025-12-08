package org.example.textflowproject.domain.episode;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.work.Work;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "episodes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Episode {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "episode_id")
  private Long episodeId;

  @Column(name = "episode_no", nullable = false)
  private Integer episodeNo;

  @Column(length = 255)
  private String title;

  @Lob
  @Column(columnDefinition = "LONGTEXT")
  private String content;

  @Column(name = "content_meta", columnDefinition = "JSON")
  private String contentMeta;

  @Enumerated(EnumType.STRING)
  @Column(name = "content_type", nullable = false, length = 20)
  private ContentType contentType;

  @Column(name = "episode_length")
  private Integer episodeLength;

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private EpisodeStatus status;

  @Column(name = "is_adult", nullable = false)
  private Boolean isAdult;

  @Column(name = "view_count", nullable = false)
  private Long viewCount;

  @Column(name = "like_count", nullable = false)
  private Long likeCount;

  @Column(name = "comment_count", nullable = false)
  private Long commentCount;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "work_id", nullable = false)
  private Work work;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
    if (price == null) {
      price = BigDecimal.ZERO;
    }
    if (status == null) {
      status = EpisodeStatus.PENDING;
    }
    if (isAdult == null) {
      isAdult = false;
    }
    if (viewCount == null) {
      viewCount = 0L;
    }
    if (likeCount == null) {
      likeCount = 0L;
    }
    if (commentCount == null) {
      commentCount = 0L;
    }
    if (contentType == null) {
      contentType = ContentType.TEXT;
    }
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}



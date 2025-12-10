package org.example.textflowproject.domain.episode;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.work.Work;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "episodes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

  // 비즈니스 메서드
  
  /**
   * 조회수 증가
   */
  public void increaseViewCount() {
    this.viewCount++;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 좋아요 증가
   */
  public void increaseLikeCount() {
    this.likeCount++;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 좋아요 감소
   */
  public void decreaseLikeCount() {
    if (this.likeCount > 0) {
      this.likeCount--;
      this.updatedAt = LocalDateTime.now();
    }
  }

  /**
   * 댓글 수 증가
   */
  public void increaseCommentCount() {
    this.commentCount++;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 댓글 수 감소
   */
  public void decreaseCommentCount() {
    if (this.commentCount > 0) {
      this.commentCount--;
      this.updatedAt = LocalDateTime.now();
    }
  }

  /**
   * 에피소드 상태 변경
   */
  public void updateStatus(EpisodeStatus newStatus) {
    if (newStatus == null) {
      throw new IllegalArgumentException("에피소드 상태는 null일 수 없습니다.");
    }
    this.status = newStatus;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 에피소드 내용 수정
   */
  public void updateContent(String title, String content) {
    if (title != null && !title.isBlank()) {
      this.title = title;
    }
    if (content != null) {
      this.content = content;
    }
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 가격 변경
   */
  public void updatePrice(BigDecimal newPrice) {
    if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
    }
    this.price = newPrice;
    this.updatedAt = LocalDateTime.now();
  }

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



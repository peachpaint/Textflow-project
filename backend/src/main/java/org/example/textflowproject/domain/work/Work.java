package org.example.textflowproject.domain.work;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.episode.Episode;
import org.example.textflowproject.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "works")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Work {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "work_id")
  private Long workId;

  @Column(nullable = false, length = 255)
  private String title;

  @Column(nullable = false, unique = true, length = 255)
  private String slug;

  @Column(columnDefinition = "LONGTEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private Genre category;

  @Column(name = "thumbnail_url", length = 500)
  private String thumbnailUrl;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private WorkStatus status;

  @Column(name = "publication_status", nullable = false, length = 20)
  private String publicationStatus;

  @Column(name = "is_adult", nullable = false)
  private Boolean isAdult;

  @Column(name = "view_count", nullable = false)
  private Long viewCount;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
  private List<Episode> episodes;

  // 비즈니스 메서드
  
  /**
   * 조회수 증가
   */
  public void increaseViewCount() {
    this.viewCount++;
  }

  /**
   * 작품 상태 변경 (검증 로직 포함 가능)
   */
  public void updateStatus(WorkStatus newStatus) {
    if (newStatus == null) {
      throw new IllegalArgumentException("작품 상태는 null일 수 없습니다.");
    }
    this.status = newStatus;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 작품 정보 수정
   */
  public void updateInfo(String title, String description, String thumbnailUrl) {
    if (title != null && !title.isBlank()) {
      this.title = title;
    }
    if (description != null) {
      this.description = description;
    }
    if (thumbnailUrl != null) {
      this.thumbnailUrl = thumbnailUrl;
    }
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 성인 작품 여부 설정
   */
  public void setAdultContent(boolean isAdult) {
    this.isAdult = isAdult;
    this.updatedAt = LocalDateTime.now();
  }

  /**
   * 출간 상태 변경
   */
  public void updatePublicationStatus(String newPublicationStatus) {
    if (newPublicationStatus == null || newPublicationStatus.isBlank()) {
      throw new IllegalArgumentException("출간 상태는 비어있을 수 없습니다.");
    }
    this.publicationStatus = newPublicationStatus;
    this.updatedAt = LocalDateTime.now();
  }

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
    if (isAdult == null) {
      isAdult = false;
    }
    if (viewCount == null) {
      viewCount = 0L;
    }
    if (publicationStatus == null) {
      publicationStatus = "ONGOING";
    }
    //status 기본값 설정 (DB가 NOT NULL이라 에러 방지)
    if (status == null) {
      status = WorkStatus.PENDING; // WorkStatus enum에 PENDING이 반드시 있어야 함
    }
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}

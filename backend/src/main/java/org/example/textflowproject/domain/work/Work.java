package org.example.textflowproject.domain.work;

import jakarta.persistence.*;
import lombok.*;
import org.example.textflowproject.domain.episode.Episode;
import org.example.textflowproject.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "works")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
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

  @Column(length = 100)
  private String category;

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
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }
}

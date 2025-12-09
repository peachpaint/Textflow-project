package org.example.textflowproject.domain.work.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkListResponse {
    private Long workId;
    private String title;
    private String slug;
    private String description;
    private String category;
    private String thumbnailUrl;
    private String status;
    private String publicationStatus;
    private Boolean isAdult;
    private Long viewCount;
    private String authorName;
    private Long authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 추가 정보
    private Integer episodeCount;
    private Double rating;
}

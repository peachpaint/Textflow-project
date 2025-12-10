package org.example.textflowproject.domain.work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkDetailDto {
  private Long workId;
  private String title;
  private String slug;
  private String description;
  private String thumbnailUrl;
  private String category;
  private String authorName;
  private Long viewCount;
  private String publicationStatus;
  private Boolean isAdult;
  private List<EpisodeSimpleDto> episodes;
}

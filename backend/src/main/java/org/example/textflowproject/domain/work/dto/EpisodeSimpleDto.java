package org.example.textflowproject.domain.work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EpisodeSimpleDto {
  private Long episodeId;
  private Integer episodeNo;
  private String title;
  private String createdAt; // ISO or toString()
}

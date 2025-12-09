package org.example.textflowproject.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularWorkDto {
  private Long workId;
  private String title;
  private String thumbnail;
  private int rank;
}

package org.example.textflowproject.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewByGenreDto {
  private String genre;
  private List<WorkSimpleDto> works;
}

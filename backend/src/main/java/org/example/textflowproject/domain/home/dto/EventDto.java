package org.example.textflowproject.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDto {
  private Long eventId;
  private String title;
  private String imageUrl;
  private String startAt;
  private String endAt;
}
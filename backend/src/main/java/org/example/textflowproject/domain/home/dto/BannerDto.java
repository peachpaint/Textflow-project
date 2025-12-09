package org.example.textflowproject.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BannerDto {
  private Long id;
  private String imageUrl;
  private String linkType;
  private Long targetId;
}

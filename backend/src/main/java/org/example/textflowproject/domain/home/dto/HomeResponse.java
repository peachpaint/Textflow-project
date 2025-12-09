package org.example.textflowproject.domain.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class HomeResponse {

  private List<BannerDto> banners;
  private List<WorkSimpleDto> recommendations;
  private List<NewByGenreDto> newByGenre;
  private List<PopularWorkDto> popular;
  private List<EventDto> events;

}

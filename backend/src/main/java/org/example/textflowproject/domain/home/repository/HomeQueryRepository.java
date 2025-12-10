package org.example.textflowproject.domain.home.repository;

import org.example.textflowproject.domain.home.dto.*;

import java.util.List;

public interface HomeQueryRepository {
  public List<BannerDto> getBanners();
  public List<WorkSimpleDto> getRecommendations();
  public List<NewByGenreDto> getNewByGenre();
  public List<PopularWorkDto> getPopular();
  public List<EventDto> getEvents();
}

package org.example.textflowproject.domain.home.repository;

import org.example.textflowproject.domain.home.dto.*;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class HomeQueryRepository {

  // 일단 더미 데이터 (연결 준비될 때까지)
  public List<BannerDto> getBanners() {
    return Arrays.asList(
        new BannerDto(1L, "https://cdn/banner1.png", "WORK", 101L)
    );
  }

  public List<WorkSimpleDto> getRecommendations() {
    return Arrays.asList(
        new WorkSimpleDto(101L, "추천작 1", "thumb1.png", "romance")
    );
  }

  public List<NewByGenreDto> getNewByGenre() {
    return Arrays.asList(
        new NewByGenreDto("romance", Arrays.asList(
            new WorkSimpleDto(201L, "로맨스 신작", "r1.png", "romance")
        ))
    );
  }

  public List<PopularWorkDto> getPopular() {
    return Arrays.asList(
        new PopularWorkDto(301L, "인기작 1", "p1.png", 1)
    );
  }

  public List<EventDto> getEvents() {
    return Arrays.asList(
        new EventDto(10L, "연말 이벤트", "ev.png", "2025-12-01", "2025-12-31")
    );
  }
}

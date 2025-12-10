package org.example.textflowproject.domain.home.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.home.dto.*;
import org.example.textflowproject.domain.work.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HomeQueryRepositoryImpl implements HomeQueryRepository {

  private final EntityManager em;

  @Value("${home.useStaticAssets:true}")
  private boolean useStaticAssets;

  /**
   배너 - 별도 테이블(Banner)을 만들어 관리 계획 -> 우선은 하드코딩;
  **/
  @Override
  public List<BannerDto> getBanners() {
    if (useStaticAssets) {
      return List.of(
          new BannerDto(1L,
              "https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?ixlib=rb-4.1.0&q=80&w=1080",
              "WORK",
              1L),
          new BannerDto(2L,
              "https://images.unsplash.com/photo-1761285367125-61a6470266ad?ixlib=rb-4.1.0&q=80&w=1080",
              "WORK",
              2L),
          new BannerDto(3L,
              "https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?ixlib=rb-4.1.0&q=80&w=1080",
              "EXTERNAL",
              null)
      );
    }
    // DB 모드 (기존 JPQL)
    List<Object[]> rows = em.createQuery(
        "select b.bannerId, b.imageUrl, b.linkType, b.targetId from Banner b where b.enabled = true order by b.priority desc",
        Object[].class
    ).getResultList();

    return rows.stream()
        .map(r -> new BannerDto(((Number) r[0]).longValue(), (String) r[1], (String) r[2], r[3] == null ? null : ((Number) r[3]).longValue()))
        .collect(Collectors.toList());
  }

  /**
   * 추천(홈 추천) - works 테이블에서 view_count 상위 6개 조회해 WorkSimpleDto로 매핑.
   */
  @Override
  public List<WorkSimpleDto> getRecommendations() {
    TypedQuery<Object[]> q = em.createQuery(
        "select w.workId, w.title, w.thumbnailUrl, w.category from Work w where w.status = 'APPROVED' order by w.viewCount desc",
        Object[].class
    );
    q.setMaxResults(6);
    List<Object[]> rows = q.getResultList();

    return rows.stream()
        .map(r -> new WorkSimpleDto(
            ((Number) r[0]).longValue(), 
            (String) r[1], 
            (String) r[2], 
            r[3] != null ? ((Genre) r[3]).getKoreanName() : null
        ))
        .collect(Collectors.toList());
  }

  /**
   * 장르별 최신(신작) – 각 장르 당 최근 작품 4개씩 리턴
   */
  @Override
  public List<NewByGenreDto> getNewByGenre() {
    // Genre enum의 값들을 사용
    List<Genre> genres = Arrays.asList(Genre.FANTASY, Genre.ROMANCE, Genre.ACTION, Genre.THRILLER, Genre.DAILY);
    List<NewByGenreDto> result = new ArrayList<>();

    for (Genre g : genres) {
      TypedQuery<Object[]> q = em.createQuery(
          "select w.workId, w.title, w.thumbnailUrl, w.category from Work w where w.category = :genre and w.status = 'APPROVED' order by w.createdAt desc",
          Object[].class
      );
      q.setParameter("genre", g);
      q.setMaxResults(4);
      List<Object[]> rows = q.getResultList();
      List<WorkSimpleDto> works = rows.stream()
          .map(r -> new WorkSimpleDto(((Number) r[0]).longValue(), (String) r[1], (String) r[2], 
              r[3] != null ? ((Genre) r[3]).getKoreanName() : null))
          .collect(Collectors.toList());
      result.add(new NewByGenreDto(g.getKoreanName(), works));
    }

    return result;
  }

  /**
   * 인기 작품 (랭킹) - view_count 기준 상위 N
   */
  @Override
  public List<PopularWorkDto> getPopular() {
    TypedQuery<Object[]> q = em.createQuery(
        "select w.workId, w.title, w.thumbnailUrl, w.viewCount from Work w where w.status = 'APPROVED' order by w.viewCount desc",
        Object[].class
    );
    q.setMaxResults(10);
    List<Object[]> rows = q.getResultList();

    List<PopularWorkDto> result = new ArrayList<>();
    int rank = 1;
    for (Object[] r : rows) {
      result.add(new PopularWorkDto(((Number) r[0]).longValue(), (String) r[1], (String) r[2], rank++));
    }
    return result;
  }

  /**
   * 이벤트 목록 - 별도 테이블(Banner)을 만들어 관리 계획 -> 우선은 하드코딩;
   */
  @Override
  public List<EventDto> getEvents() {
    if (useStaticAssets) {
      return List.of(
          // eventId, title, imageUrl, startAt, endAt
          new EventDto(1L, "신규 가입 이벤트",
              "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixlib=rb-4.1.0&q=80&w=1080",
              "2025-12-01 00:00:00",
              "2026-01-01 23:59:59"),
          new EventDto(2L, "매일 출석 체크",
              "https://images.unsplash.com/photo-1504384308090-c894fdcc538d?ixlib=rb-4.1.0&q=80&w=1080",
              "2025-11-15 00:00:00",
              "2026-02-28 23:59:59"),
          new EventDto(3L, "작품 리뷰 이벤트",
              "https://images.unsplash.com/photo-1526318472351-c75fcf0708d4?ixlib=rb-4.1.0&q=80&w=1080",
              "2025-12-05 00:00:00",
              "2026-01-10 23:59:59")
      );
    }

    // DB 모드
    List<Object[]> rows = em.createQuery(
        "select e.eventId, e.title, e.imageUrl, e.startAt, e.endAt from EventEntity e where e.enabled = true order by e.startAt desc",
        Object[].class
    ).getResultList();

    return rows.stream().map(r -> new EventDto(
        ((Number) r[0]).longValue(),
        (String) r[1],
        (String) r[2],
        r[3] == null ? null : r[3].toString(),
        r[4] == null ? null : r[4].toString()
    )).collect(Collectors.toList());
  }
}

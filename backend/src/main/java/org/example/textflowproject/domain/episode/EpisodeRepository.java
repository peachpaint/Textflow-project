package org.example.textflowproject.domain.episode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
  // 오래된 회차부터 (홈 화면 일부 분야에서 사용 가능)
  List<Episode> findByWork_WorkIdOrderByEpisodeNoAsc(Long workId);

  // 최신 회차부터 (작품 상세 페이지에 사용)_Desc 정렬
  List<Episode> findByWork_WorkIdOrderByEpisodeNoDesc(Long workId);
}

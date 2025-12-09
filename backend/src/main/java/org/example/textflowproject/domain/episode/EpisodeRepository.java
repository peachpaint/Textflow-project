package org.example.textflowproject.domain.episode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
  List<Episode> findByWork_WorkIdOrderByEpisodeNoAsc(Long workId);
}

package org.example.textflowproject.repository;

import org.example.textflowproject.domain.episode.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
  List<Episode> findByWork_WorkIdOrderByEpisodeNumberAsc(Long workId);
}

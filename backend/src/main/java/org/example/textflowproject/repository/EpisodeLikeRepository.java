package org.example.textflowproject.repository;

import org.example.textflowproject.domain.Like.EpisodeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpisodeLikeRepository extends JpaRepository<EpisodeLike, Long> {
  boolean existsByUser_UserIdAndEpisode_EpisodeId(Long userId, Long episodeId);
  Optional<EpisodeLike> findByUser_UserIdAndEpisode_EpisodeId(Long userId, Long episodeId);
  long countByEpisode_EpisodeId(Long episodeId);
}


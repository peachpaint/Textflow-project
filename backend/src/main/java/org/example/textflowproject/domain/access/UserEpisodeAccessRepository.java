package org.example.textflowproject.domain.access;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEpisodeAccessRepository extends JpaRepository<UserEpisodeAccess, Long> {
  boolean existsByUser_UserIdAndEpisode_EpisodeIdAndActiveTrue(Long userId, Long episodeId);
  Optional<UserEpisodeAccess> findByUser_UserIdAndEpisode_EpisodeIdAndActiveTrue(Long userId, Long episodeId);
  List<UserEpisodeAccess> findByUser_UserId(Long userId);
  List<UserEpisodeAccess> findByEpisode_EpisodeId(Long episodeId);
}

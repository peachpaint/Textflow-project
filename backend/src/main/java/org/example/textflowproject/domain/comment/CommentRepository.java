package org.example.textflowproject.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByEpisode_EpisodeIdOrderByCreatedAtDesc(Long episodeId);
  List<Comment> findByUser_UserId(Long userId);
  long countByEpisode_EpisodeId(Long episodeId);
}

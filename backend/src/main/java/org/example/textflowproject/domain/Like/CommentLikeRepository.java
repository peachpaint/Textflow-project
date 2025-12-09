package org.example.textflowproject.domain.Like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
  boolean existsByUser_UserIdAndComment_CommentId(Long userId, Long commentId);
  Optional<CommentLike> findByUser_UserIdAndComment_CommentId(Long userId, Long commentId);
  long countByComment_CommentId(Long commentId);
}

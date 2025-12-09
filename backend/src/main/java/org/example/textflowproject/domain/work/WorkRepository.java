package org.example.textflowproject.domain.work;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

  // 슬러그 기반 조회 (필수)
  boolean existsBySlug(String slug);
  Optional<Work> findBySlug(String slug);

  Page<Work> findByAuthor_UserId(Long authorId, Pageable pageable);
  List<Work> findByAuthor_UserId(Long authorId);
  
  // 장르별 조회
  List<Work> findByCategory(String category, Pageable pageable);
  
  // 조회수 높은 순
  List<Work> findTopByOrderByViewCountDesc(Pageable pageable);
  
  // 최신순
  List<Work> findAllByOrderByCreatedAtDesc(Pageable pageable);
  
  // 검색
  List<Work> findByTitleContainingOrDescriptionContaining(String title, String description);
}

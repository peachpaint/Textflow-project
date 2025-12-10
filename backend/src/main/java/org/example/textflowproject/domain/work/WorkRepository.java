package org.example.textflowproject.domain.work;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

  // 슬러그 기반 조회 (필수)
  boolean existsBySlug(String slug);
  Optional<Work> findBySlug(String slug);

  Page<Work> findByAuthor_UserId(Long authorId, Pageable pageable);
  List<Work> findByAuthor_UserId(Long authorId);
  
  // 장르별 조회 (N+1 방지: fetch join)
  @Query("SELECT DISTINCT w FROM Work w LEFT JOIN FETCH w.author LEFT JOIN FETCH w.episodes WHERE w.category = :category ORDER BY w.createdAt DESC")
  List<Work> findByCategoryWithAuthorAndEpisodes(@Param("category") Genre category, Pageable pageable);
  
  List<Work> findByCategory(Genre category, Pageable pageable);
  
  // 조회수 높은 순 (N+1 방지: fetch join)
  @Query("SELECT DISTINCT w FROM Work w LEFT JOIN FETCH w.author LEFT JOIN FETCH w.episodes ORDER BY w.viewCount DESC")
  List<Work> findTopByOrderByViewCountDescWithAuthorAndEpisodes(Pageable pageable);
  
  List<Work> findTopByOrderByViewCountDesc(Pageable pageable);
  
  // 최신순 (N+1 방지: fetch join)
  @Query("SELECT DISTINCT w FROM Work w LEFT JOIN FETCH w.author LEFT JOIN FETCH w.episodes ORDER BY w.createdAt DESC")
  List<Work> findAllByOrderByCreatedAtDescWithAuthorAndEpisodes(Pageable pageable);
  
  List<Work> findAllByOrderByCreatedAtDesc(Pageable pageable);
  
  // 검색 (N+1 방지: fetch join)
  @Query("SELECT DISTINCT w FROM Work w LEFT JOIN FETCH w.author LEFT JOIN FETCH w.episodes WHERE w.title LIKE %:keyword% ORDER BY w.viewCount DESC")
  List<Work> searchByTitleWithAuthorAndEpisodes(@Param("keyword") String keyword, Pageable pageable);
  
  List<Work> findByTitleContainingOrDescriptionContaining(String title, String description);
  
  // ID로 조회 (N+1 방지: fetch join)
  @Query("SELECT w FROM Work w LEFT JOIN FETCH w.author LEFT JOIN FETCH w.episodes WHERE w.workId = :id")
  Optional<Work> findByIdWithAuthorAndEpisodes(@Param("id") Long id);
}

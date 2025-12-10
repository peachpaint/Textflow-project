package org.example.textflowproject.domain.work.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.common.exception.WorkNotFoundException;
import org.example.textflowproject.domain.episode.Episode;
import org.example.textflowproject.domain.episode.EpisodeRepository;
import org.example.textflowproject.domain.work.Work;
import org.example.textflowproject.domain.work.WorkRepository;
import org.example.textflowproject.domain.work.dto.EpisodeSimpleDto;
import org.example.textflowproject.domain.work.dto.WorkDetailDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkDetailService {

  private final WorkRepository workRepository;
  private final EpisodeRepository episodeRepository;

  /**
   * 작품 상세 조회 (workId 기준)
   * - Work 엔티티(기본정보) 조회
   * - Episode 목록은 최신순(desc)으로 조회하여 DTO로 변환
   */
  @Transactional(readOnly = true)
  public WorkDetailDto getWorkDetailById(Long workId) {
    Work work = workRepository.findById(workId)
        .orElseThrow(() -> new WorkNotFoundException("Work not found with id: " + workId));

    // 에피소드 최신순으로 조회
    List<Episode> episodes = episodeRepository.findByWork_WorkIdOrderByEpisodeNoDesc(work.getWorkId());

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    List<EpisodeSimpleDto> episodeDtos = episodes.stream()
        .map(e -> new EpisodeSimpleDto(
            e.getEpisodeId(),
            e.getEpisodeNo(),
            e.getTitle() != null ? e.getTitle() : ("EP. " + e.getEpisodeNo()),
            e.getCreatedAt() != null ? e.getCreatedAt().format(fmt) : null
        ))
        .collect(Collectors.toList());

    // author display name: try getDisplayName(), fallback to username if available
    String authorName = null;
    try {
      if (work.getAuthor() != null) {
        // 안전하게 메서드 존재 여부에 따라 가져오기
        try {
          authorName = (String) work.getAuthor().getClass().getMethod("getDisplayName").invoke(work.getAuthor());
        } catch (NoSuchMethodException ignore) {
          // fallback
          try {
            authorName = (String) work.getAuthor().getClass().getMethod("getUsername").invoke(work.getAuthor());
          } catch (Exception ignore2) {
            authorName = null;
          }
        }
      }
    } catch (Exception e) {
      authorName = null;
    }

    return new WorkDetailDto(
        work.getWorkId(),
        work.getTitle(),
        work.getSlug(),
        work.getDescription(),
        work.getThumbnailUrl(),
        work.getCategory() != null ? work.getCategory().getKoreanName() : null,
        authorName,
        work.getViewCount(),
        work.getPublicationStatus(),
        work.getIsAdult(),
        episodeDtos
    );
  }

  /**
   * 작품 상세 조회 (slug 기준)
   * - Work 엔티티(기본정보) 조회
   * - Episode 목록은 최신순(desc)으로 조회하여 DTO로 변환
   */
  @Transactional(readOnly = true)
  public WorkDetailDto getWorkDetailBySlug(String slug) {
    Work work = workRepository.findBySlug(slug)
        .orElseThrow(() -> new WorkNotFoundException("Work not found with slug: " + slug));

    // 에피소드 최신순으로 조회
    List<Episode> episodes = episodeRepository.findByWork_WorkIdOrderByEpisodeNoDesc(work.getWorkId());

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    List<EpisodeSimpleDto> episodeDtos = episodes.stream()
        .map(e -> new EpisodeSimpleDto(
            e.getEpisodeId(),
            e.getEpisodeNo(),
            e.getTitle() != null ? e.getTitle() : ("EP. " + e.getEpisodeNo()),
            e.getCreatedAt() != null ? e.getCreatedAt().format(fmt) : null
        ))
        .collect(Collectors.toList());

    // author display name: try getDisplayName(), fallback to username if available
    String authorName = null;
    try {
      if (work.getAuthor() != null) {
        // 안전하게 메서드 존재 여부에 따라 가져오기
        try {
          authorName = (String) work.getAuthor().getClass().getMethod("getDisplayName").invoke(work.getAuthor());
        } catch (NoSuchMethodException ignore) {
          // fallback
          try {
            authorName = (String) work.getAuthor().getClass().getMethod("getUsername").invoke(work.getAuthor());
          } catch (Exception ignore2) {
            authorName = null;
          }
        }
      }
    } catch (Exception e) {
      authorName = null;
    }

    return new WorkDetailDto(
        work.getWorkId(),
        work.getTitle(),
        work.getSlug(),
        work.getDescription(),
        work.getThumbnailUrl(),
        work.getCategory() != null ? work.getCategory().getKoreanName() : null,
        authorName,
        work.getViewCount(),
        work.getPublicationStatus(),
        work.getIsAdult(),
        episodeDtos
    );
  }
}

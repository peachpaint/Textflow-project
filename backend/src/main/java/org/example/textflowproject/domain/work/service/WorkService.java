package org.example.textflowproject.domain.work.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.domain.work.Work;
import org.example.textflowproject.domain.work.WorkRepository;
import org.example.textflowproject.domain.work.dto.WorkListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final WorkRepository workRepository;

    // 평점 높은 작품 (추천)
    public List<WorkListResponse> getTopRatedWorks(int limit) {
        // TODO: 실제 평점 계산 로직 추가
        List<Work> works = workRepository.findAll(Pageable.ofSize(limit)).getContent();
        return works.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 장르별 작품
    public List<WorkListResponse> getWorksByGenre(String genre, Pageable pageable) {
        List<Work> works = workRepository.findByCategory(genre, pageable);
        return works.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 조회수 높은 작품 (인기 순위)
    public List<WorkListResponse> getTopViewedWorks(int limit) {
        List<Work> works = workRepository.findTopByOrderByViewCountDesc(Pageable.ofSize(limit));
        return works.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 신작
    public List<WorkListResponse> getNewWorks(Pageable pageable) {
        List<Work> works = workRepository.findAllByOrderByCreatedAtDesc(pageable);
        return works.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 작품 ID로 조회
    public WorkListResponse getWorkById(Long workId) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new RuntimeException("작품을 찾을 수 없습니다: " + workId));
        return convertToResponse(work);
    }

    // 검색
    public List<WorkListResponse> searchWorks(String keyword) {
        List<Work> works = workRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
        return works.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Work -> WorkListResponse 변환
    private WorkListResponse convertToResponse(Work work) {
        return WorkListResponse.builder()
                .workId(work.getWorkId())
                .title(work.getTitle())
                .slug(work.getSlug())
                .description(work.getDescription())
                .category(work.getCategory())
                .thumbnailUrl(work.getThumbnailUrl())
                .status(work.getStatus() != null ? work.getStatus().name() : null)
                .publicationStatus(work.getPublicationStatus())
                .isAdult(work.getIsAdult())
                .viewCount(work.getViewCount())
                .authorName(work.getAuthor() != null ? 
                        (work.getAuthor().getDisplayName() != null ? 
                                work.getAuthor().getDisplayName() : 
                                work.getAuthor().getUsername()) : null)
                .authorId(work.getAuthor() != null ? work.getAuthor().getUserId() : null)
                .createdAt(work.getCreatedAt())
                .updatedAt(work.getUpdatedAt())
                .episodeCount(work.getEpisodes() != null ? work.getEpisodes().size() : 0)
                .rating(null) // TODO: 평점 계산 로직 추가
                .build();
    }
}

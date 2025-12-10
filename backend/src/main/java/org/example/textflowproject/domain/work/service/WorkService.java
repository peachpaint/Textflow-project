package org.example.textflowproject.domain.work.service;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.common.exception.WorkNotFoundException;
import org.example.textflowproject.domain.work.Genre;
import org.example.textflowproject.domain.work.Work;
import org.example.textflowproject.domain.work.WorkRepository;
import org.example.textflowproject.domain.work.dto.WorkListResponse;
import org.example.textflowproject.domain.work.mapper.WorkMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    // 평점 높은 작품 (추천)
    @Transactional(readOnly = true)
    public List<WorkListResponse> getTopRatedWorks(int limit) {
        // TODO: 실제 평점 계산 로직 추가
        List<Work> works = workRepository.findAll(Pageable.ofSize(limit)).getContent();
        return workMapper.toDtoList(works);
    }

    // 장르별 작품
    @Transactional(readOnly = true)
    public List<WorkListResponse> getWorksByGenre(Genre genre, Pageable pageable) {
        List<Work> works = workRepository.findByCategoryWithAuthorAndEpisodes(genre, pageable);
        return workMapper.toDtoList(works);
    }

    // 조회수 높은 작품 (인기 순위)
    @Transactional(readOnly = true)
    public List<WorkListResponse> getTopViewedWorks(int limit) {
        List<Work> works = workRepository.findTopByOrderByViewCountDescWithAuthorAndEpisodes(Pageable.ofSize(limit));
        return workMapper.toDtoList(works);
    }

    // 신작
    @Transactional(readOnly = true)
    public List<WorkListResponse> getNewWorks(Pageable pageable) {
        List<Work> works = workRepository.findAllByOrderByCreatedAtDescWithAuthorAndEpisodes(pageable);
        return workMapper.toDtoList(works);
    }

    // 작품 ID로 조회
    @Transactional(readOnly = true)
    public WorkListResponse getWorkById(Long workId) {
        Work work = workRepository.findByIdWithAuthorAndEpisodes(workId)
                .orElseThrow(() -> new WorkNotFoundException(workId));
        return workMapper.toDto(work);
    }

    // 검색
    @Transactional(readOnly = true)
    public List<WorkListResponse> searchWorks(String keyword, Pageable pageable) {
        List<Work> works = workRepository.searchByTitleWithAuthorAndEpisodes(keyword, pageable);
        return workMapper.toDtoList(works);
    }
}

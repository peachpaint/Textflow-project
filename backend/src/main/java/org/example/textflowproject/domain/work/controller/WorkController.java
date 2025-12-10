package org.example.textflowproject.domain.work.controller;

import lombok.RequiredArgsConstructor;
import org.example.textflowproject.common.constants.AppConstants;
import org.example.textflowproject.domain.work.Genre;
import org.example.textflowproject.domain.work.dto.WorkListResponse;
import org.example.textflowproject.domain.work.service.WorkService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    // 추천 작품 목록
    @GetMapping("/recommended")
    public ResponseEntity<List<WorkListResponse>> getRecommendedWorks() {
        // TODO: 실제 추천 로직 구현 (평점 기반, 조회수 기반 등)
        return ResponseEntity.ok(workService.getTopRatedWorks(AppConstants.Work.RECOMMENDED_WORKS_LIMIT));
    }

    // 장르별 작품 목록
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<WorkListResponse>> getWorksByGenre(
            @PathVariable String genre,
            @PageableDefault(size = AppConstants.Pagination.DEFAULT_SIZE, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Genre genreEnum = Genre.fromKoreanName(genre);
        return ResponseEntity.ok(workService.getWorksByGenre(genreEnum, pageable));
    }

    // 인기 순위 (실시간 조회수 기반)
    @GetMapping("/rankings")
    public ResponseEntity<List<WorkListResponse>> getRankings() {
        return ResponseEntity.ok(workService.getTopViewedWorks(AppConstants.Work.RANKING_WORKS_LIMIT));
    }

    // 신작 목록
    @GetMapping("/new")
    public ResponseEntity<List<WorkListResponse>> getNewWorks(
            @PageableDefault(size = AppConstants.Pagination.DEFAULT_SIZE, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(workService.getNewWorks(pageable));
    }

    // 작품 상세
    @GetMapping("/{workId}")
    public ResponseEntity<WorkListResponse> getWorkDetail(@PathVariable Long workId) {
        return ResponseEntity.ok(workService.getWorkById(workId));
    }

    // 검색
    @GetMapping("/search")
    public ResponseEntity<List<WorkListResponse>> searchWorks(
            @RequestParam String keyword,
            @PageableDefault(size = AppConstants.Pagination.DEFAULT_SIZE, sort = "viewCount", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(workService.searchWorks(keyword, pageable));
    }
}

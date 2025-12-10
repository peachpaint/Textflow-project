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

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

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

  /**
   * 작품 생성 API용 메서드
   * - slug가 비어 있으면 자동 생성
   * - 중복 시 숫자 suffix 를 붙여 고유화
   */
  @Transactional
  public Work createWork(Work work) {
    // slug가 비어있거나 공백이면 자동 생성
    if (work.getSlug() == null || work.getSlug().isBlank()) {
      String generated = generateSlug(work.getTitle());
      work.updateSlug(generated);
    } else {
      // 이미 수동 slug가 있다면, 중복 체크 및 보정
      String desired = sanitizeSlug(work.getSlug());
      if (workRepository.existsBySlug(desired)) {
        desired = makeUniqueSlug(desired);
      }
      work.updateSlug(desired);
    }

    // 기타 초기값은 Work 엔티티의 @PrePersist 에서 처리될 것
    return workRepository.save(work);
  }

  /**
   * 제목으로부터 기본 slug 생성 (sanitize + lower-case + 공백->하이픈)
   */
  private String generateSlug(String title) {
    if (title == null) {
      title = "work";
    }
    String base = sanitizeSlug(title);

    // 기본 base가 빈 문자열이 될 경우 안전한 기본값 사용
    if (base.isEmpty()) {
      base = "work";
    }

    // 이미 존재하지 않으면 바로 사용
    if (!workRepository.existsBySlug(base)) {
      return base;
    }

    // 중복이면 숫자 접미사 붙이기
    return makeUniqueSlug(base);
  }

  /**
   * 숫자 suffix 를 붙여 고유 slug 만들기
   * 예: base, base-1, base-2, ...
   */
  private String makeUniqueSlug(String base) {
    int suffix = 1;
    String candidate;
    do {
      candidate = base + "-" + suffix++;
      // loop until a non-existing slug is found
    } while (workRepository.existsBySlug(candidate));
    return candidate;
  }

  /**
   * slug에 안전한 문자만 남기도록 정제
   * - 소문자화
   * - 연속 공백은 하이픈으로
   * - 특수문자 제거(한글/영문/숫자와 하이픈 허용)
   * - 예: "회귀한 천재 마법사!!" -> "회귀한-천재-마법사"
   */
  private String sanitizeSlug(String input) {
    if (input == null) return "";

    // Normalize (유니코드 정규화) — 한글에는 영향 없음, 라틴문자 조합문자 처리에 도움
    String normalized = Normalizer.normalize(input, Normalizer.Form.NFKC);

    // 소문자 변환(영문에 대해). 한국어 대소문자 개념 없음, 안전하게 toLowerCase 적용
    String lower = normalized.toLowerCase(Locale.ROOT);

    // 허용할 문자만 남기기: 한글(가-힣), 영문(a-z), 숫자(0-9), 공백, 하이픈
    // 먼저 모든 허용 문자와 아닌 문자 구분
    String cleaned = lower.replaceAll("[^\\p{IsHangul}a-z0-9\\s-]", ""); // 특수문자 제거

    // 공백/언더스코어/다중하이픈을 단일 하이픈으로 통일
    cleaned = cleaned.trim().replaceAll("[\\s_]+", "-").replaceAll("-{2,}", "-");

    // 끝/시작의 하이픈 제거
    cleaned = cleaned.replaceAll("^-|-$", "");

    return cleaned;
  }
}

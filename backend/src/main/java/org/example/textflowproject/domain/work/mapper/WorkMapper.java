package org.example.textflowproject.domain.work.mapper;

import org.example.textflowproject.domain.work.Work;
import org.example.textflowproject.domain.work.dto.WorkListResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkMapper {

    /**
     * Work 엔티티를 WorkListResponse DTO로 변환
     */
    public WorkListResponse toDto(Work work) {
        if (work == null) {
            return null;
        }
        
        return WorkListResponse.builder()
                .workId(work.getWorkId())
                .title(work.getTitle())
                .slug(work.getSlug())
                .description(work.getDescription())
                .category(work.getCategory() != null ? work.getCategory().getKoreanName() : null)
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

    /**
     * Work 엔티티 리스트를 WorkListResponse DTO 리스트로 변환
     */
    public List<WorkListResponse> toDtoList(List<Work> works) {
        if (works == null) {
            return List.of();
        }
        
        return works.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

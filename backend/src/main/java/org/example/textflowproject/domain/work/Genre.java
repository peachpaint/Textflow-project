package org.example.textflowproject.domain.work;

import lombok.Getter;

/**
 * 작품 장르 Enum
 */
@Getter
public enum Genre {
    FANTASY("판타지", "fantasy"),
    ROMANCE("로맨스", "romance"),
    ACTION("액션", "action"),
    THRILLER("스릴러", "thriller"),
    DAILY("일상", "daily");

    private final String koreanName;
    private final String englishName;

    Genre(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    /**
     * 한글 이름으로 Genre 찾기
     */
    public static Genre fromKoreanName(String koreanName) {
        if (koreanName == null) {
            return null;
        }
        for (Genre genre : Genre.values()) {
            if (genre.koreanName.equals(koreanName)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 장르입니다: " + koreanName);
    }

    /**
     * 영문 이름으로 Genre 찾기
     */
    public static Genre fromEnglishName(String englishName) {
        if (englishName == null) {
            return null;
        }
        for (Genre genre : Genre.values()) {
            if (genre.englishName.equalsIgnoreCase(englishName)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 장르입니다: " + englishName);
    }

    /**
     * DB에 저장될 값 (한글 이름)
     */
    @Override
    public String toString() {
        return koreanName;
    }
}

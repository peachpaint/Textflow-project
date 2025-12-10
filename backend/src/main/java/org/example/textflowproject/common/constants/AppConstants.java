package org.example.textflowproject.common.constants;

/**
 * 애플리케이션 전역 상수 정의
 */
public final class AppConstants {

    private AppConstants() {
        // 인스턴스화 방지
    }

    /**
     * 작품 관련 상수
     */
    public static final class Work {
        public static final int RECOMMENDED_WORKS_LIMIT = 6;
        public static final int RANKING_WORKS_LIMIT = 10;
        public static final int DEFAULT_PAGE_SIZE = 20;
        public static final int NEW_WORKS_PAGE_SIZE = 20;
        public static final int GENRE_WORKS_LIMIT = 4;
        
        private Work() {}
    }

    /**
     * 페이지네이션 기본값
     */
    public static final class Pagination {
        public static final int DEFAULT_PAGE = 0;
        public static final int DEFAULT_SIZE = 20;
        public static final int MAX_SIZE = 100;
        
        private Pagination() {}
    }

    /**
     * 파일 업로드 제한
     */
    public static final class FileUpload {
        public static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB
        public static final long MAX_REQUEST_SIZE = 100 * 1024 * 1024; // 100MB
        
        private FileUpload() {}
    }

    /**
     * 캐시 설정
     */
    public static final class Cache {
        public static final String TOP_RATED_WORKS = "topRatedWorks";
        public static final String TOP_VIEWED_WORKS = "topViewedWorks";
        public static final String NEW_WORKS = "newWorks";
        public static final int CACHE_TTL_SECONDS = 3600; // 1시간
        
        private Cache() {}
    }

    /**
     * 에피소드 관련 상수
     */
    public static final class Episode {
        public static final int FREE_EPISODE_COUNT = 3;
        public static final int DEFAULT_EPISODE_PRICE = 300;
        
        private Episode() {}
    }
}

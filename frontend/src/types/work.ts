// 작품 관련 타입 정의

export interface Work {
  workId: number;
  title: string;
  thumbnail: string;
  rating: number;
  views: number;
  genre: string;
  authorName: string;
  description?: string;
  synopsis?: string;
  serialStatus: "ONGOING" | "COMPLETED" | "HIATUS";
  ageRating: "ALL" | "TEEN" | "ADULT";
  tags?: string[];
  likeCount?: number;
  bookmarkCount?: number;
  totalEpisodes?: number;
  lastUpdated?: string;
}

export interface Episode {
  episodeId: number;
  workId: number;
  episodeNumber: number;
  title: string;
  thumbnail: string;
  uploadDate: string;
  viewCount: number;
  likeCount: number;
  isFree: boolean;
  price?: number;
  isNew?: boolean;
}

export interface WorkDetailResponse {
  work: Work;
  episodes: Episode[];
  relatedWorks?: Work[];
}

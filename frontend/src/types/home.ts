// 홈 화면 관련 타입 정의

export interface BannerDto {
  id: number;
  imageUrl: string;
  linkType?: string;
  targetId?: number;
}

export interface WorkSimpleDto {
  workId: number;
  title: string;
  thumbnailUrl: string;
  category: string;
}

export interface NewByGenreDto {
  genre: string;
  works: WorkSimpleDto[];
}

export interface PopularWorkDto {
  workId: number;
  title: string;
  thumbnailUrl: string;
  rank: number;
}

export interface EventDto {
  eventId: number;
  title: string;
  imageUrl?: string;
  startAt?: string;
  endAt?: string;
}

export interface HomeResponse {
  banners: BannerDto[];
  recommendations: WorkSimpleDto[];
  newByGenre: NewByGenreDto[];
  popular: PopularWorkDto[];
  events: EventDto[];
}

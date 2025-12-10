import axios, { AxiosResponse } from "axios";
import { WorkDetailDto, Work } from "../types/work";
import { HomeResponse } from "../types/home";

// Backend API URL
const API_BASE_URL = "http://localhost:8080/Textflow";

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // 쿠키/세션 사용 시
});

// 요청 인터셉터 (필요시 토큰 추가 등)
apiClient.interceptors.request.use(
  (config) => {
    // 예: 로컬스토리지에서 토큰 가져오기
    // const token = localStorage.getItem('token');
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`;
    // }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 응답 인터셉터 (에러 처리)
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // 인증 오류 처리
      console.error("인증이 필요합니다.");
    } else if (error.response?.status === 404) {
      console.error("요청한 리소스를 찾을 수 없습니다.");
    } else if (error.response?.status >= 500) {
      console.error("서버 오류가 발생했습니다.");
    }
    return Promise.reject(error);
  }
);

export default apiClient;

// API 함수 타입 정의
export const api = {
  // 홈 데이터
  getHomeData: (): Promise<AxiosResponse<HomeResponse>> =>
    apiClient.get<HomeResponse>("/home"),

  // 작품 관련 API
  getRecommendedWorks: (): Promise<AxiosResponse<Work[]>> =>
    apiClient.get<Work[]>("/api/works/recommended"),

  getWorksByGenre: (
    genre: string,
    page: number = 0,
    size: number = 20
  ): Promise<AxiosResponse<Work[]>> =>
    apiClient.get<Work[]>(`/api/works/genre/${genre}`, {
      params: { page, size },
    }),

  getRankings: (): Promise<AxiosResponse<Work[]>> =>
    apiClient.get<Work[]>("/api/works/rankings"),

  getNewWorks: (
    page: number = 0,
    size: number = 20
  ): Promise<AxiosResponse<Work[]>> =>
    apiClient.get<Work[]>("/api/works/new", {
      params: { page, size },
    }),

  getWorkDetail: (id: number): Promise<AxiosResponse<WorkDetailDto>> =>
    apiClient.get<WorkDetailDto>(`/works/${id}`),

  searchWorks: (
    keyword: string,
    page: number = 0,
    size: number = 20
  ): Promise<AxiosResponse<Work[]>> =>
    apiClient.get<Work[]>("/api/works/search", {
      params: { keyword, page, size },
    }),

  // 회차 관련 API
  getEpisodesByWork: (workId: number): Promise<AxiosResponse<any[]>> =>
    apiClient.get<any[]>(`/api/episodes/work/${workId}`),

  getEpisodeDetail: (episodeId: number): Promise<AxiosResponse<any>> =>
    apiClient.get<any>(`/api/episodes/${episodeId}`),

  // 좋아요 관련
  addLike: (workId: number): Promise<AxiosResponse<void>> =>
    apiClient.post<void>(`/api/works/${workId}/like`),

  removeLike: (workId: number): Promise<AxiosResponse<void>> =>
    apiClient.delete<void>(`/api/works/${workId}/like`),

  // 북마크 관련
  addBookmark: (workId: number): Promise<AxiosResponse<void>> =>
    apiClient.post<void>(`/api/works/${workId}/bookmark`),

  removeBookmark: (workId: number): Promise<AxiosResponse<void>> =>
    apiClient.delete<void>(`/api/works/${workId}/bookmark`),

  // 신고하기
  report: (data: any): Promise<AxiosResponse<void>> =>
    apiClient.post<void>("/reports", data),
};

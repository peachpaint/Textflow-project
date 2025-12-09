import axios from "axios";

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
    }
    return Promise.reject(error);
  }
);

export default apiClient;

// API 함수 예시
export const api = {
  // 홈 데이터
  getHomeData: () => apiClient.get("/home"),

  // 작품 관련 API
  getRecommendedWorks: () => apiClient.get("/api/works/recommended"),
  getWorksByGenre: (genre: string) =>
    apiClient.get(`/api/works/genre/${genre}`),
  getRankings: () => apiClient.get("/api/works/rankings"),
  getNewWorks: () => apiClient.get("/api/works/new"),
  getWorkDetail: (id: number) => apiClient.get(`/api/works/${id}`),
  searchWorks: (keyword: string) =>
    apiClient.get("/api/works/search", { params: { keyword } }),

  // 좋아요 관련
  addLike: (data: any) => apiClient.post("/likes", data),

  // 신고하기
  report: (data: any) => apiClient.post("/reports", data),
};

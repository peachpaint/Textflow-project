import { useState, useEffect } from "react";
import { BannerCarousel } from "../components/BannerCarousel";
import { RecommendedSection } from "../components/RecommendedSection";
import { GenreSection } from "../components/GenreSection";
import { RankingSection } from "../components/RankingSection";
import { EventSection } from "../components/EventSection";
import Header from "../components/common/Header";
import { LoadingSpinner } from "../components/common/LoadingSpinner";
import { ErrorAlert } from "../components/common/ErrorAlert";
import { api } from "../api/client";
import { HomeResponse } from "../types/home";

export default function Home() {
  const [homeData, setHomeData] = useState<HomeResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchHomeData = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await api.getHomeData();
        setHomeData(response.data);
      } catch (error) {
        console.error("홈 데이터 로딩 실패:", error);
        setError(
          "데이터를 불러오는데 실패했습니다. 잠시 후 다시 시도해주세요."
        );
      } finally {
        setLoading(false);
      }
    };

    fetchHomeData();
  }, []);

  const handleRetry = () => {
    const fetchHomeData = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await api.getHomeData();
        setHomeData(response.data);
      } catch (error) {
        console.error("홈 데이터 로딩 실패:", error);
        setError(
          "데이터를 불러오는데 실패했습니다. 잠시 후 다시 시도해주세요."
        );
      } finally {
        setLoading(false);
      }
    };
    fetchHomeData();
  };

  if (loading) {
    return <LoadingSpinner />;
  }

  if (error) {
    return (
      <div className="min-h-screen bg-[#fafafa]">
        <Header />
        <ErrorAlert message={error} onRetry={handleRetry} />
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#fafafa]">
      {/* Header */}
      <Header />

      {/* Main Content */}
      <main className="pb-8">
        {/* 상단 배너 */}
        <BannerCarousel banners={homeData?.banners} />

        {/* 추천 작품 섹션 */}
        <RecommendedSection />

        {/* 장르별 신작 */}
        <GenreSection />

        {/* 인기 순위 */}
        <RankingSection />

        {/* 이벤트 섹션 */}
        <EventSection events={homeData?.events} />
      </main>
    </div>
  );
}

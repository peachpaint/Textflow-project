import { BannerCarousel } from "./components/BannerCarousel";
import { RecommendedSection } from "./components/RecommendedSection";
import { GenreSection } from "./components/GenreSection";
import { RankingSection } from "./components/RankingSection";
import { EventSection } from "./components/EventSection";
import { ConnectionTest } from "./components/ConnectionTest";
import { Search, Bell, Menu } from "lucide-react";

export default function App() {
  return (
    <div className="min-h-screen bg-[#fafafa]">
      {/* Header */}
      <header className="sticky top-0 z-50 bg-teal-primary shadow-lg">
        <div className="flex items-center justify-between px-4 py-3">
          <div className="flex items-center gap-3">
            <Menu className="w-6 h-6 text-white cursor-pointer" />
            <h1 className="text-white text-xl font-bold">WEBTOON PLUS</h1>
          </div>
          <div className="flex items-center gap-4">
            <Search className="w-6 h-6 text-white cursor-pointer" />
            <Bell className="w-6 h-6 text-white cursor-pointer" />
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="pb-8">
        {/* 상단 배너 */}
        <BannerCarousel />

        {/* 추천 작품 섹션 */}
        <RecommendedSection />

        {/* 장르별 신작 */}
        <GenreSection />

        {/* 인기 순위 */}
        <RankingSection />

        {/* 이벤트 섹션 */}
        <EventSection />
      </main>

      {/* Backend 연결 테스트 (개발용) */}
      <ConnectionTest />
    </div>
  );
}

import { BannerCarousel } from "./components/BannerCarousel";
import { RecommendedSection } from "./components/RecommendedSection";
import { GenreSection } from "./components/GenreSection";
import { RankingSection } from "./components/RankingSection";
import { EventSection } from "./components/EventSection";
import { Search, Bell, Menu } from "lucide-react";

export default function App() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-[#026873] to-[#1B818C]">
      {/* Header */}
      <header className="sticky top-0 z-50 bg-[#026873] shadow-lg">
        <div className="flex items-center justify-between px-4 py-3">
          <div className="flex items-center gap-3">
            <Menu className="w-6 h-6 text-white" />
            <h1 className="text-white">WEBTOON PLUS</h1>
          </div>
          <div className="flex items-center gap-4">
            <Search className="w-6 h-6 text-white" />
            <Bell className="w-6 h-6 text-white" />
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
    </div>
  );
}

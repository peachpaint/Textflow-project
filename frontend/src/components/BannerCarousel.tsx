import { useState, useEffect } from "react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { BannerDto } from "../types/home";

interface BannerCarouselProps {
  banners?: BannerDto[];
}

// 기본 배너 데이터 (API 로딩 중 또는 데이터 없을 때 표시)
const defaultBanners: BannerDto[] = [
  {
    id: 1,
    imageUrl:
      "https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmYW50YXN5JTIwYXJ0JTIwZGlnaXRhbHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 2,
    imageUrl:
      "https://images.unsplash.com/photo-1761285367125-61a6470266ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMGlsbHVzdHJhdGlvbiUyMGFydHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
  },
  {
    id: 3,
    imageUrl:
      "https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBoZXJvJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDgxOTg0M3ww&ixlib=rb-4.1.0&q=80&w=1080",
  },
];

export function BannerCarousel({
  banners = defaultBanners,
}: BannerCarouselProps) {
  // 현재 슬라이드 상태 관리, setCurrentIndex 로 변경, 0 → 첫 번째 슬라이드
  const [currentIndex, setCurrentIndex] = useState(0);

  //자동 슬라이드 변경을 위한 useEffect
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentIndex((prev) => (prev + 1) % banners.length);
    }, 4000);

    return () => clearInterval(timer);
  }, []);

  // 이전 슬라이드
  const goToPrevious = () => {
    setCurrentIndex((prev) => (prev - 1 + banners.length) % banners.length);
  }; // -1이 되지 않도록 처리 -> (prev - 1 + banners.length) => 0에서 -1이 아닌 2로 이동
  // 다음 슬라이드
  const goToNext = () => {
    setCurrentIndex((prev) => (prev + 1) % banners.length);
  };
  // 특정 슬라이드로 이동
  const goToSlide = (index: number) => {
    setCurrentIndex(index);
  };

  return (
    <div className="relative w-full h-64 overflow-hidden">
      {/* UI 렌더링_전체 캐러셀 박스
      1.박스_relative : 안의 absolute 요소 기준점
      2.w-full : 가로 전체 차지
      3.h-64 : 높이 16rem
      4.overflow-hidden : 이미지 넘어가는 부분 가림 */}
      {banners.map((banner, index) => (
        <div
          key={banner.id}
          className={`absolute inset-0 transition-opacity duration-500 ${
            index === currentIndex ? "opacity-100" : "opacity-0"
          }`}
        >
          <img
            src={banner.imageUrl}
            alt={`배너 ${banner.id}`}
            className="w-full h-full object-cover"
          />
          <div className="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent" />
        </div>
      ))}

      {/* Navigation Buttons */}
      <button
        onClick={goToPrevious}
        className="absolute left-2 top-1/2 -translate-y-1/2 bg-black/30 hover:bg-black/50 text-white rounded-full p-2 transition-colors"
        aria-label="Previous slide"
      >
        <ChevronLeft className="w-6 h-6" />
      </button>
      <button
        onClick={goToNext}
        className="absolute right-2 top-1/2 -translate-y-1/2 bg-black/30 hover:bg-black/50 text-white rounded-full p-2 transition-colors"
        aria-label="Next slide"
      >
        <ChevronRight className="w-6 h-6" />
      </button>
      {/* 좌우 버튼 UI_두 버튼(왼쪽/오른쪽)은 같은 스타일:
      1.가운데 위치 (top-1/2, -translate-y-1/2)
      2.반투명 검정 배경
      3.hover 시 더 어둡게 */}

      {/* 아래 점 : Indicators */}
      <div className="absolute bottom-4 left-1/2 -translate-x-1/2 flex gap-2">
        {banners.map((_, index) => (
          <button
            key={index}
            onClick={() => goToSlide(index)}
            className={`h-2 rounded-full transition-all ${
              index === currentIndex ? "w-6 bg-white" : "w-2 bg-white/50"
            }`}
            aria-label={`Go to slide ${index + 1}`}
          />
        ))}
      </div>
      {/* 1.현재 슬라이드 → 길쭉한 w-6
      2.다른 슬라이드 → 짧은 w-2
      3.클릭하면 해당 인덱스로 이동 */}
    </div>
  );
}

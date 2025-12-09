import { useState, useEffect } from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';

const banners = [
  {
    id: 1,
    image: 'https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmYW50YXN5JTIwYXJ0JTIwZGlnaXRhbHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    title: '신규 연재 시작!',
    subtitle: '판타지 대작 \'마법사의 귀환\'',
    bgColor: '#1FAFBF',
  },
  {
    id: 2,
    image: 'https://images.unsplash.com/photo-1761285367125-61a6470266ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMGlsbHVzdHJhdGlvbiUyMGFydHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    title: '독점 연재 중',
    subtitle: '로맨스 웹소설 \'달빛 아래서\'',
    bgColor: '#6CCED9',
  },
  {
    id: 3,
    image: 'https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBoZXJvJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDgxOTg0M3ww&ixlib=rb-4.1.0&q=80&w=1080',
    title: '시즌2 오픈!',
    subtitle: '액션 웹툰 \'최강의 검\'',
    bgColor: '#05F2DB',
  },
];

export function BannerCarousel() {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentIndex((prev) => (prev + 1) % banners.length);
    }, 4000);

    return () => clearInterval(timer);
  }, []);

  const goToPrevious = () => {
    setCurrentIndex((prev) => (prev - 1 + banners.length) % banners.length);
  };

  const goToNext = () => {
    setCurrentIndex((prev) => (prev + 1) % banners.length);
  };

  const goToSlide = (index: number) => {
    setCurrentIndex(index);
  };

  return (
    <div className="relative w-full h-64 overflow-hidden">
      {banners.map((banner, index) => (
        <div
          key={banner.id}
          className={`absolute inset-0 transition-opacity duration-500 ${
            index === currentIndex ? 'opacity-100' : 'opacity-0'
          }`}
          style={{ backgroundColor: banner.bgColor }}
        >
          <img
            src={banner.image}
            alt={banner.title}
            className="w-full h-full object-cover opacity-60"
          />
          <div className="absolute inset-0 flex flex-col justify-end p-6 bg-gradient-to-t from-black/50 to-transparent">
            <h2 className="text-white text-lg font-bold">{banner.title}</h2>
            <p className="text-white text-sm">{banner.subtitle}</p>
          </div>
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

      {/* Indicators */}
      <div className="absolute bottom-4 left-1/2 -translate-x-1/2 flex gap-2">
        {banners.map((_, index) => (
          <button
            key={index}
            onClick={() => goToSlide(index)}
            className={`h-2 rounded-full transition-all ${
              index === currentIndex
                ? 'w-6 bg-white'
                : 'w-2 bg-white/50'
            }`}
            aria-label={`Go to slide ${index + 1}`}
          />
        ))}
      </div>
    </div>
  );
}

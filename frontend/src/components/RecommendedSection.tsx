import { Star, Eye } from 'lucide-react';

const recommendedWorks = [
  {
    id: 1,
    title: '회귀한 천재 마법사',
    author: '김작가',
    genre: '판타지',
    image: 'https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmYW50YXN5JTIwYXJ0JTIwZGlnaXRhbHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    badge: 'NEW',
    rating: 9.8,
    views: '1.2M',
  },
  {
    id: 2,
    title: '운명의 붉은 실',
    author: '박작가',
    genre: '로맨스',
    image: 'https://images.unsplash.com/photo-1761285367125-61a6470266ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMGlsbHVzdHJhdGlvbiUyMGFydHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080',
    badge: 'HOT',
    rating: 9.5,
    views: '980K',
  },
  {
    id: 3,
    title: '레벨업의 신',
    author: '이작가',
    genre: '액션',
    image: 'https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBoZXJvJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDgxOTg0M3ww&ixlib=rb-4.1.0&q=80&w=1080',
    badge: '인기',
    rating: 9.7,
    views: '1.5M',
  },
];

export function RecommendedSection() {
  return (
    <section className="px-4 py-6">
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-[#0D0D0D] text-lg font-bold">오늘의 추천</h2>
        <button className="text-teal-tertiary text-sm hover:underline">
          더보기 &gt;
        </button>
      </div>

      <div className="flex gap-4 overflow-x-auto pb-2 scrollbar-hide">
        {recommendedWorks.map((work) => (
          <div
            key={work.id}
            className="flex-shrink-0 w-72 bg-white/10 backdrop-blur-md rounded-2xl overflow-hidden border border-teal-secondary/30 hover:border-teal-tertiary hover:scale-105 transition-all cursor-pointer"
          >
            <div className="relative h-40">
              <img
                src={work.image}
                alt={work.title}
                className="w-full h-full object-cover"
              />
              <span className="absolute top-2 left-2 bg-teal-tertiary text-white px-3 py-1 rounded-full text-xs font-semibold">
                {work.badge}
              </span>
            </div>
            <div className="p-4">
              <h3 className="text-[#0D0D0D] text-base font-semibold mb-1">
                {work.title}
              </h3>
              <p className="text-teal-secondary text-sm mb-3">
                {work.author} · {work.genre}
              </p>
              <div className="flex items-center justify-between text-sm">
                <div className="flex items-center gap-1 text-teal-tertiary">
                  <Star className="w-4 h-4 fill-current" />
                  <span>{work.rating}</span>
                </div>
                <div className="flex items-center gap-1 text-gray-500">
                  <Eye className="w-4 h-4" />
                  <span>{work.views}</span>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}

import { Star, TrendingUp } from "lucide-react";
import { useNavigate } from "react-router-dom";

const rankings = [
  {
    rank: 1,
    title: "회귀한 천재 마법사",
    author: "김작가",
    genre: "판타지",
    thumbnail:
      "https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmYW50YXN5JTIwYXJ0JTIwZGlnaXRhbHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
    trend: "up",
  },
  {
    rank: 2,
    title: "레벨업의 신",
    author: "이작가",
    genre: "액션",
    thumbnail:
      "https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBoZXJvJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDgxOTg0M3ww&ixlib=rb-4.1.0&q=80&w=1080",
    trend: "same",
  },
  {
    rank: 3,
    title: "운명의 붉은 실",
    author: "박작가",
    genre: "로맨스",
    thumbnail:
      "https://images.unsplash.com/photo-1761285367125-61a6470266ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMGlsbHVzdHJhdGlvbiUyMGFydHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
    trend: "up",
  },
  {
    rank: 4,
    title: "미스터리 탐정",
    author: "최작가",
    genre: "스릴러",
    thumbnail:
      "https://images.unsplash.com/photo-1698956483970-a47edef29331?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxteXN0ZXJ5JTIwdGhyaWxsZXIlMjBib29rfGVufDF8fHx8MTc2NDY5NjkzN3ww&ixlib=rb-4.1.0&q=80&w=1080",
    trend: "down",
  },
  {
    rank: 5,
    title: "환생한 용사",
    author: "정작가",
    genre: "판타지",
    thumbnail:
      "https://images.unsplash.com/photo-1653723367970-ae966c1b803e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhbmltZSUyMHN0eWxlJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDc1MzgyM3ww&ixlib=rb-4.1.0&q=80&w=1080",
    trend: "up",
  },
];

export function RankingSection() {
  const navigate = useNavigate();

  const handleWorkClick = (rank: number) => {
    navigate(`/work/${rank}`);
  };

  const getRankStyle = (rank: number) => {
    switch (rank) {
      case 1:
        return "bg-teal-tertiary text-teal-dark";
      case 2:
        return "bg-teal-secondary text-teal-dark";
      case 3:
        return "bg-[#6CCED9] text-teal-dark";
      default:
        return "bg-teal-dark/40 text-teal-dark";
    }
  };

  const renderTrendIcon = (trend: string) => {
    if (trend === "up") {
      return <TrendingUp className="w-4 h-4 text-teal-tertiary" />;
    }
    if (trend === "down") {
      return <TrendingUp className="w-4 h-4 text-red-400 rotate-180" />;
    }
    return <div className="w-4 h-0.5 bg-white/30" />;
  };

  return (
    <section className="px-4 py-6">
      <div className="flex items-center gap-2 mb-4">
        <Star className="w-6 h-6 text-teal-tertiary" />
        <h2 className="text-[#0D0D0D] text-lg font-bold">실시간 인기 순위</h2>
      </div>

      <div className="bg-white/5 backdrop-blur-md rounded-2xl overflow-hidden border border-teal-secondary/20">
        {rankings.map((item, index) => (
          <div
            key={item.rank}
            onClick={() => handleWorkClick(item.rank)}
            className={`flex items-center gap-4 p-4 cursor-pointer hover:bg-white/10 transition-colors ${
              index !== rankings.length - 1 ? "border-b border-white/10" : ""
            }`}
          >
            {/* Rank Number */}
            <div
              className={`w-8 h-8 rounded-full flex items-center justify-center font-bold text-sm ${getRankStyle(
                item.rank
              )}`}
            >
              {item.rank}
            </div>

            {/* Thumbnail */}
            <div className="w-12 h-16 rounded-lg overflow-hidden flex-shrink-0">
              <img
                src={item.thumbnail}
                alt={item.title}
                className="w-full h-full object-cover"
              />
            </div>

            {/* Info */}
            <div className="flex-1 min-w-0">
              <h4 className="text-[#0D0D0D] text-sm font-semibold truncate">
                {item.title}
              </h4>
              <p className="text-teal-secondary text-xs">
                {item.author} · {item.genre}
              </p>
            </div>

            {/* Trend */}
            <div className="flex-shrink-0">{renderTrendIcon(item.trend)}</div>
          </div>
        ))}
      </div>
    </section>
  );
}

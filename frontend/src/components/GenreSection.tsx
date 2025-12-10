import { useState } from "react";
import { useNavigate } from "react-router-dom";

const genres = ["전체", "판타지", "로맨스", "액션", "스릴러", "일상"];

const works = [
  {
    id: 1,
    title: "마법의 세계",
    episode: "EP. 45",
    image:
      "https://images.unsplash.com/photo-1670073952001-1aafed4bfc02?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmYW50YXN5JTIwYXJ0JTIwZGlnaXRhbHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "판타지",
    isNew: true,
  },
  {
    id: 2,
    title: "사랑의 온도",
    episode: "EP. 32",
    image:
      "https://images.unsplash.com/photo-1761285367125-61a6470266ad?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyb21hbnRpYyUyMGlsbHVzdHJhdGlvbiUyMGFydHxlbnwxfHx8fDE3NjQ4MTk4NDJ8MA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "로맨스",
    isNew: true,
  },
  {
    id: 3,
    title: "최강의 검사",
    episode: "EP. 78",
    image:
      "https://images.unsplash.com/photo-1720031995215-3b13aa6c42a7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBoZXJvJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDgxOTg0M3ww&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "액션",
    isNew: false,
  },
  {
    id: 4,
    title: "미스터리 탐정",
    episode: "EP. 21",
    image:
      "https://images.unsplash.com/photo-1698956483970-a47edef29331?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxteXN0ZXJ5JTIwdGhyaWxsZXIlMjBib29rfGVufDF8fHx8MTc2NDY5NjkzN3ww&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "스릴러",
    isNew: true,
  },
  {
    id: 5,
    title: "환생한 용사",
    episode: "EP. 56",
    image:
      "https://images.unsplash.com/photo-1653723367970-ae966c1b803e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhbmltZSUyMHN0eWxlJTIwaWxsdXN0cmF0aW9ufGVufDF8fHx8MTc2NDc1MzgyM3ww&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "판타지",
    isNew: false,
  },
  {
    id: 6,
    title: "운명의 만남",
    episode: "EP. 12",
    image:
      "https://images.unsplash.com/photo-1759863738666-7584248cdf7e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21pYyUyMGJvb2slMjBpbGx1c3RyYXRpb258ZW58MXx8fHwxNzY0ODE5ODQxfDA&ixlib=rb-4.1.0&q=80&w=1080",
    genre: "로맨스",
    isNew: true,
  },
];

export function GenreSection() {
  const [selectedGenre, setSelectedGenre] = useState("전체");
  const navigate = useNavigate();

  const filteredWorks =
    selectedGenre === "전체"
      ? works
      : works.filter((work) => work.genre === selectedGenre);

  const handleWorkClick = (workId: number) => {
    navigate(`/work/${workId}`);
  };

  return (
    <section className="px-4 py-6">
      <h2 className="text-[#0D0D0D] text-lg font-bold mb-4">장르별 신작</h2>

      {/* Genre Tabs */}
      <div className="flex gap-2 mb-4 overflow-x-auto pb-2 scrollbar-hide">
        {genres.map((genre) => (
          <button
            key={genre}
            onClick={() => setSelectedGenre(genre)}
            className={`px-4 py-2 rounded-full text-sm font-medium whitespace-nowrap transition-all ${
              selectedGenre === genre
                ? "bg-teal-tertiary text-white"
                : "bg-white/10 text-[#0D0D0D] hover:bg-white/20"
            }`}
          >
            {genre}
          </button>
        ))}
      </div>

      {/* Works Grid */}
      <div className="grid grid-cols-3 gap-3">
        {filteredWorks.map((work) => (
          <div
            key={work.id}
            onClick={() => handleWorkClick(work.id)}
            className="bg-white/5 rounded-xl overflow-hidden cursor-pointer hover:scale-105 transition-transform"
          >
            <div className="relative aspect-[3/4]">
              <img
                src={work.image}
                alt={work.title}
                className="w-full h-full object-cover"
              />
              {work.isNew && (
                <span className="absolute top-1 right-1 bg-teal-primary text-white px-2 py-0.5 rounded text-xs">
                  NEW
                </span>
              )}
            </div>
            <div className="p-2">
              <h4 className="text-[#0D0D0D] text-sm font-semibold truncate">
                {work.title}
              </h4>
              <p className="text-teal-secondary text-xs">{work.episode}</p>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}

import { Episode } from "../../types/work";
import { Play } from "lucide-react";

interface EpisodeListProps {
  episodes: Episode[];
  onEpisodeClick: (episodeId: number) => void;
}

export default function EpisodeList({
  episodes,
  onEpisodeClick,
}: EpisodeListProps) {
  return (
    <div className="bg-white">
      <div className="px-6 py-4 border-b border-gray-200 flex items-center justify-between">
        <h2 className="text-lg font-bold">
          전체 회차 <span className="text-teal-primary">{episodes.length}</span>
        </h2>
        <select className="text-sm border border-gray-300 rounded-lg px-3 py-1.5 focus:outline-none focus:ring-2 focus:ring-teal-primary/20">
          <option>최신순</option>
          <option>오래된순</option>
        </select>
      </div>

      <div className="divide-y divide-gray-100">
        {episodes.map((episode) => (
          <button
            key={episode.episodeId}
            onClick={() => onEpisodeClick(episode.episodeId)}
            className="w-full px-6 py-4 flex items-center gap-4 hover:bg-gray-50 transition-colors text-left group"
          >
            {/* 썸네일 */}
            <div className="relative flex-shrink-0">
              <img
                src={episode.thumbnail}
                alt={episode.title}
                className="w-20 h-20 object-cover rounded-lg"
              />
              <div className="absolute inset-0 bg-black/40 rounded-lg opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center">
                <Play size={24} className="text-white" fill="white" />
              </div>
              {episode.isNew && (
                <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs px-1.5 py-0.5 rounded-full font-bold">
                  NEW
                </span>
              )}
            </div>

            {/* 회차 정보 */}
            <div className="flex-1 min-w-0">
              <div className="flex items-center gap-2 mb-1">
                <span className="text-gray-500 text-sm font-medium">
                  {episode.episodeNumber}화
                </span>
                {!episode.isFree && (
                  <span className="px-2 py-0.5 bg-yellow-100 text-yellow-700 text-xs rounded font-medium">
                    유료
                  </span>
                )}
              </div>

              <h3 className="text-gray-900 font-medium mb-1 truncate group-hover:text-teal-primary transition-colors">
                {episode.title}
              </h3>

              <div className="flex items-center gap-3 text-xs text-gray-500">
                <span>{episode.uploadDate}</span>
                <span>조회 {episode.viewCount.toLocaleString()}</span>
                {episode.likeCount > 0 && (
                  <span>❤️ {episode.likeCount.toLocaleString()}</span>
                )}
              </div>
            </div>

            {/* 가격 표시 */}
            {!episode.isFree && episode.price && (
              <div className="flex-shrink-0 text-right">
                <div className="text-teal-primary font-bold">
                  {episode.price}원
                </div>
              </div>
            )}
          </button>
        ))}
      </div>
    </div>
  );
}

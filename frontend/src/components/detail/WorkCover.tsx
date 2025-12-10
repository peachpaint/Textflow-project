import { Heart, Bell, Share } from "lucide-react";
import { Work } from "../../types/work";

interface WorkCoverProps {
  work: Work;
  onLike: () => void;
  onBookmark: () => void;
  onShare: () => void;
  isLiked: boolean;
  isBookmarked: boolean;
}

export default function WorkCover({
  work,
  onLike,
  onBookmark,
  onShare,
  isLiked,
  isBookmarked,
}: WorkCoverProps) {
  return (
    <div className="relative">
      {/* 커버 이미지 */}
      <div className="relative h-[400px] bg-gradient-to-b from-gray-900 to-gray-800">
        <img
          src={work.thumbnail}
          alt={work.title}
          className="w-full h-full object-cover opacity-60"
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/80 via-black/40 to-transparent" />

        {/* 작품 정보 오버레이 */}
        <div className="absolute bottom-0 left-0 right-0 p-6 text-white">
          <div className="flex gap-4">
            {/* 썸네일 */}
            <div className="flex-shrink-0">
              <img
                src={work.thumbnail}
                alt={work.title}
                className="w-28 h-36 object-cover rounded-lg shadow-2xl border-2 border-white/20"
              />
            </div>

            {/* 텍스트 정보 */}
            <div className="flex-1 flex flex-col justify-end">
              <h1 className="text-2xl font-bold mb-2 drop-shadow-lg">
                {work.title}
              </h1>
              <div className="flex items-center gap-3 text-sm text-white/90 mb-3">
                <span>{work.authorName}</span>
                <span>•</span>
                <span>{work.genre}</span>
                <span>•</span>
                <span className="px-2 py-0.5 bg-teal-primary/80 rounded text-xs">
                  {work.serialStatus === "ONGOING" ? "연재중" : "완결"}
                </span>
              </div>

              {/* 통계 */}
              <div className="flex items-center gap-4 text-sm text-white/80">
                <span>⭐ {work.rating.toFixed(1)}</span>
                <span>👁 {work.views.toLocaleString()}</span>
                {work.likeCount && (
                  <span>❤️ {work.likeCount.toLocaleString()}</span>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* 액션 버튼들 */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-around max-w-md mx-auto">
          <button
            onClick={onLike}
            className={`flex flex-col items-center gap-1 transition-colors ${
              isLiked ? "text-red-500" : "text-gray-600 hover:text-red-500"
            }`}
          >
            <Heart size={24} fill={isLiked ? "currentColor" : "none"} />
            <span className="text-xs">좋아요</span>
          </button>

          <button
            onClick={onBookmark}
            className={`flex flex-col items-center gap-1 transition-colors ${
              isBookmarked
                ? "text-teal-primary"
                : "text-gray-600 hover:text-teal-primary"
            }`}
          >
            <Bell size={24} fill={isBookmarked ? "currentColor" : "none"} />
            <span className="text-xs">알림받기</span>
          </button>

          <button
            onClick={onShare}
            className="flex flex-col items-center gap-1 text-gray-600 hover:text-teal-primary transition-colors"
          >
            <Share size={24} />
            <span className="text-xs">공유하기</span>
          </button>
        </div>
      </div>
    </div>
  );
}

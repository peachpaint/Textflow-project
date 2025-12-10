import { Play, Bookmark } from "lucide-react";

interface FixedBottomBarProps {
  onFirstEpisode: () => void;
  onContinueReading: () => void;
  hasProgress?: boolean;
  lastReadEpisode?: number;
}

export default function FixedBottomBar({
  onFirstEpisode,
  onContinueReading,
  hasProgress = false,
  lastReadEpisode,
}: FixedBottomBarProps) {
  return (
    <div className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 shadow-lg z-40">
      <div className="max-w-screen-lg mx-auto px-4 py-3 flex items-center gap-3">
        {hasProgress ? (
          <>
            <button
              onClick={onContinueReading}
              className="flex-1 bg-teal-primary text-white font-bold py-3 px-4 rounded-lg hover:bg-teal-dark transition-colors flex items-center justify-center gap-2"
            >
              <Play size={20} fill="white" />
              <span>
                {lastReadEpisode ? `${lastReadEpisode}화 이어보기` : "이어보기"}
              </span>
            </button>

            <button
              onClick={onFirstEpisode}
              className="px-5 py-3 border-2 border-teal-primary text-teal-primary font-bold rounded-lg hover:bg-teal-primary/5 transition-colors"
            >
              첫화부터
            </button>
          </>
        ) : (
          <button
            onClick={onFirstEpisode}
            className="flex-1 bg-teal-primary text-white font-bold py-3 px-4 rounded-lg hover:bg-teal-dark transition-colors flex items-center justify-center gap-2"
          >
            <Play size={20} fill="white" />
            <span>첫화보기</span>
          </button>
        )}

        <button className="p-3 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors">
          <Bookmark size={24} className="text-gray-600" />
        </button>
      </div>
    </div>
  );
}

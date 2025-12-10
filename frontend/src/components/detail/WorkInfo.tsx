import { Work } from "../../types/work";

interface WorkInfoProps {
  work: Work;
}

export default function WorkInfo({ work }: WorkInfoProps) {
  return (
    <div className="bg-white px-6 py-5 border-b border-gray-200">
      <h2 className="text-lg font-bold mb-3">작품 정보</h2>

      <div className="space-y-3">
        <div className="flex">
          <span className="text-gray-500 w-24 flex-shrink-0">작가</span>
          <span className="text-gray-900 font-medium">{work.authorName}</span>
        </div>

        <div className="flex">
          <span className="text-gray-500 w-24 flex-shrink-0">장르</span>
          <span className="text-gray-900">{work.genre}</span>
        </div>

        <div className="flex">
          <span className="text-gray-500 w-24 flex-shrink-0">연재 상태</span>
          <span className="text-gray-900">
            {work.serialStatus === "ONGOING"
              ? "연재중"
              : work.serialStatus === "COMPLETED"
              ? "완결"
              : "휴재"}
          </span>
        </div>

        {work.ageRating && (
          <div className="flex">
            <span className="text-gray-500 w-24 flex-shrink-0">연령 등급</span>
            <span className="text-gray-900">
              {work.ageRating === "ALL"
                ? "전체 이용가"
                : work.ageRating === "TEEN"
                ? "15세 이용가"
                : "19세 이용가"}
            </span>
          </div>
        )}

        {work.totalEpisodes && (
          <div className="flex">
            <span className="text-gray-500 w-24 flex-shrink-0">총 회차</span>
            <span className="text-gray-900">{work.totalEpisodes}화</span>
          </div>
        )}

        {work.lastUpdated && (
          <div className="flex">
            <span className="text-gray-500 w-24 flex-shrink-0">
              최근 업데이트
            </span>
            <span className="text-gray-900">{work.lastUpdated}</span>
          </div>
        )}
      </div>

      {work.tags && work.tags.length > 0 && (
        <div className="mt-4 pt-4 border-t border-gray-100">
          <div className="flex flex-wrap gap-2">
            {work.tags.map((tag, index) => (
              <span
                key={index}
                className="px-3 py-1 bg-gray-100 text-gray-700 text-sm rounded-full hover:bg-gray-200 transition-colors cursor-pointer"
              >
                #{tag}
              </span>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

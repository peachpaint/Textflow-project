import { ChevronDown } from "lucide-react";
import { useState } from "react";

interface WorkSynopsisProps {
  synopsis: string;
}

export default function WorkSynopsis({ synopsis }: WorkSynopsisProps) {
  const [isExpanded, setIsExpanded] = useState(false);
  const maxLength = 150;
  const shouldTruncate = synopsis.length > maxLength;

  return (
    <div className="bg-white px-6 py-5 border-b border-gray-200">
      <h2 className="text-lg font-bold mb-3">작품 소개</h2>

      <div className="relative">
        <p
          className={`text-gray-700 leading-relaxed whitespace-pre-wrap ${
            !isExpanded && shouldTruncate ? "line-clamp-3" : ""
          }`}
        >
          {synopsis}
        </p>

        {shouldTruncate && (
          <button
            onClick={() => setIsExpanded(!isExpanded)}
            className="mt-3 flex items-center gap-1 text-teal-primary hover:text-teal-dark font-medium text-sm transition-colors"
          >
            <span>{isExpanded ? "접기" : "더보기"}</span>
            <ChevronDown
              size={16}
              className={`transition-transform ${
                isExpanded ? "rotate-180" : ""
              }`}
            />
          </button>
        )}
      </div>
    </div>
  );
}

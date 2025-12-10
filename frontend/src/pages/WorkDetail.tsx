import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Header from "../components/common/Header";
import { LoadingSpinner } from "../components/common/LoadingSpinner";
import { ErrorAlert } from "../components/common/ErrorAlert";
import WorkCover from "../components/detail/WorkCover";
import WorkInfo from "../components/detail/WorkInfo";
import WorkSynopsis from "../components/detail/WorkSynopsis";
import EpisodeList from "../components/detail/EpisodeList";
import FixedBottomBar from "../components/common/FixedBottomBar";
import { Work, Episode } from "../types/work";
import { api } from "../api/client";

export default function WorkDetail() {
  const { workId } = useParams<{ workId: string }>();
  const navigate = useNavigate();

  const [work, setWork] = useState<Work | null>(null);
  const [episodes, setEpisodes] = useState<Episode[]>([]);
  const [isLiked, setIsLiked] = useState(false);
  const [isBookmarked, setIsBookmarked] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (workId) {
      fetchWorkDetail();
    }
  }, [workId]);

  const fetchWorkDetail = async () => {
    if (!workId) return;

    try {
      setLoading(true);
      setError(null);

      // API로 작품 상세 정보 가져오기
      const workResponse = await api.getWorkDetail(Number(workId));
      const workData = workResponse.data;

      // 백엔드 DTO를 프론트엔드 Work 타입으로 변환
      if (workData) {
        const convertedWork: Work = {
          workId: workData.workId || Number(workId),
          title: workData.title || "",
          thumbnail:
            workData.thumbnailUrl || "https://via.placeholder.com/300x400",
          rating: 0, // TODO: 평점 API 추가 필요
          views: Number(workData.viewCount) || 0,
          genre: workData.category || "기타",
          authorName: workData.authorName || "작가 미상",
          synopsis: workData.description || "",
          serialStatus:
            workData.publicationStatus === "ONGOING"
              ? "ONGOING"
              : workData.publicationStatus === "COMPLETED"
              ? "COMPLETED"
              : "HIATUS",
          ageRating: workData.isAdult ? "ADULT" : "ALL",
          totalEpisodes: workData.episodes?.length || 0,
        };
        setWork(convertedWork);

        // 회차 목록 변환
        if (workData.episodes && workData.episodes.length > 0) {
          const convertedEpisodes: Episode[] = workData.episodes.map(
            (ep: any, index: number) => ({
              episodeId: ep.episodeId,
              workId: Number(workId),
              episodeNumber: ep.episodeNo,
              title: ep.title,
              thumbnail:
                workData.thumbnailUrl || "https://via.placeholder.com/160x120",
              uploadDate: ep.createdAt?.split("T")[0] || "",
              viewCount: 0, // TODO: 회차별 조회수 API 추가 필요
              likeCount: 0, // TODO: 회차별 좋아요 API 추가 필요
              isFree: index < 3, // 첫 3화 무료 (임시)
              price: index >= 3 ? 300 : undefined,
              isNew: index === 0,
            })
          );
          setEpisodes(convertedEpisodes);
        }
      }
    } catch (error) {
      console.error("작품 정보 로딩 실패:", error);
      setError("작품 정보를 불러오는데 실패했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const handleLike = async () => {
    if (!work) return;

    try {
      if (!isLiked) {
        await api.addLike(work.workId);
      } else {
        await api.removeLike(work.workId);
      }
      setIsLiked(!isLiked);
    } catch (error) {
      console.error("좋아요 처리 실패:", error);
    }
  };

  const handleBookmark = async () => {
    if (!work) return;

    try {
      if (!isBookmarked) {
        await api.addBookmark(work.workId);
      } else {
        await api.removeBookmark(work.workId);
      }
      setIsBookmarked(!isBookmarked);
    } catch (error) {
      console.error("알림 설정 실패:", error);
    }
  };

  const handleShare = () => {
    if (!work) return;

    // Web Share API 사용
    if (navigator.share) {
      navigator
        .share({
          title: work.title,
          text: `${work.authorName}의 ${work.title}을 확인해보세요!`,
          url: window.location.href,
        })
        .catch((error) => console.error("공유 실패:", error));
    } else {
      // 클립보드에 URL 복사
      navigator.clipboard.writeText(window.location.href);
      alert("링크가 복사되었습니다.");
    }
  };

  const handleEpisodeClick = (episodeId: number) => {
    navigate(`/viewer/${workId}/${episodeId}`);
  };

  const handleFirstEpisode = () => {
    if (episodes.length > 0) {
      // 첫 번째 회차 (가장 오래된 회차)
      const firstEpisode = episodes[episodes.length - 1];
      handleEpisodeClick(firstEpisode.episodeId);
    }
  };

  const handleContinueReading = () => {
    if (episodes.length > 0) {
      // TODO: 실제로는 사용자의 마지막 읽은 회차를 가져와야 함
      handleEpisodeClick(episodes[0].episodeId);
    }
  };

  if (loading) {
    return <LoadingSpinner />;
  }

  if (error) {
    return (
      <div className="min-h-screen bg-[#fafafa]">
        <Header showBackButton />
        <ErrorAlert message={error} onRetry={fetchWorkDetail} />
      </div>
    );
  }

  if (!work) {
    return (
      <div className="min-h-screen bg-[#fafafa]">
        <Header showBackButton />
        <div className="flex items-center justify-center py-20">
          <p className="text-gray-600">작품을 찾을 수 없습니다.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#fafafa] pb-20">
      {/* Header */}
      <Header showBackButton title={work.title} />

      {/* 작품 커버 및 액션 버튼 */}
      <WorkCover
        work={work}
        onLike={handleLike}
        onBookmark={handleBookmark}
        onShare={handleShare}
        isLiked={isLiked}
        isBookmarked={isBookmarked}
      />

      {/* 작품 정보 */}
      <WorkInfo work={work} />

      {/* 줄거리 */}
      {work.synopsis && <WorkSynopsis synopsis={work.synopsis} />}

      {/* 회차 목록 */}
      <EpisodeList episodes={episodes} onEpisodeClick={handleEpisodeClick} />

      {/* 하단 고정 바 */}
      <FixedBottomBar
        onFirstEpisode={handleFirstEpisode}
        onContinueReading={handleContinueReading}
        hasProgress={false}
      />
    </div>
  );
}

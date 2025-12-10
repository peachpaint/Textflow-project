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
    // TODO: API 연결 후 실제 데이터 가져오기
    // 현재는 Mock 데이터 사용
    fetchWorkDetail();
  }, [workId]);

  const fetchWorkDetail = async () => {
    try {
      setLoading(true);
      setError(null);

      // Mock 데이터 (나중에 API 호출로 대체)
      const mockWork: Work = {
        workId: Number(workId) || 1,
        title: "전지적 독자 시점",
        thumbnail: "https://via.placeholder.com/300x400",
        rating: 9.8,
        views: 1250000,
        genre: "판타지",
        authorName: "singNsong",
        synopsis: `'이것은 내가 가장 좋아하는 웹소설의 마지막 장면이었다.'

대중교통 기사 김독자는 10년 간 연재한 웹소설 '멸망한 세계에서 살아남는 세 가지 방법'의 유일한 독자였다.

그런데 어느 날 그 소설이 현실이 되었다. 
소설 속 세계관 그대로 멸망이 시작되고, 괴물들이 나타나기 시작했다.

하지만 김독자는 이 세계를 잘 알고 있다. 
소설을 끝까지 읽은 유일한 독자이기 때문이다.

이제 그는 소설 속 지식을 바탕으로 이 멸망한 세계에서 살아남아야 한다.`,
        serialStatus: "ONGOING",
        ageRating: "TEEN",
        tags: ["판타지", "액션", "회귀", "성장", "먼치킨"],
        likeCount: 45230,
        bookmarkCount: 32100,
        totalEpisodes: 127,
        lastUpdated: "2024.01.15",
      };

      const mockEpisodes: Episode[] = Array.from({ length: 10 }, (_, i) => ({
        episodeId: i + 1,
        workId: Number(workId) || 1,
        episodeNumber: 127 - i,
        title: `${127 - i}화 제목`,
        thumbnail: `https://via.placeholder.com/160x120`,
        uploadDate: `2024.01.${15 - i}`,
        viewCount: Math.floor(Math.random() * 50000) + 10000,
        likeCount: Math.floor(Math.random() * 5000) + 500,
        isFree: i < 3,
        price: i >= 3 ? 300 : undefined,
        isNew: i === 0,
      }));

      setWork(mockWork);
      setEpisodes(mockEpisodes);
    } catch (error) {
      console.error("작품 정보 로딩 실패:", error);
      setError("작품 정보를 불러오는데 실패했습니다.");
    } finally {
      setLoading(false);
    }
  };

  const handleLike = () => {
    setIsLiked(!isLiked);
    // TODO: API 호출로 좋아요 상태 저장
  };

  const handleBookmark = () => {
    setIsBookmarked(!isBookmarked);
    // TODO: API 호출로 알림 상태 저장
  };

  const handleShare = () => {
    // TODO: 공유 기능 구현
    alert("공유 기능 (준비중)");
  };

  const handleEpisodeClick = (episodeId: number) => {
    // TODO: 뷰어 페이지로 이동
    navigate(`/viewer/${workId}/${episodeId}`);
  };

  const handleFirstEpisode = () => {
    if (episodes.length > 0) {
      const firstEpisode = episodes[episodes.length - 1];
      handleEpisodeClick(firstEpisode.episodeId);
    }
  };

  const handleContinueReading = () => {
    // TODO: 마지막으로 읽은 회차로 이동
    if (episodes.length > 0) {
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

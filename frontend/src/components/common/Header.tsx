import { Menu, Search, Bell, ArrowLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

interface HeaderProps {
  showBackButton?: boolean;
  title?: string;
}

export default function Header({ showBackButton = false, title }: HeaderProps) {
  const navigate = useNavigate();

  return (
    <header className="sticky top-0 z-50 bg-teal-primary px-4 py-3 flex items-center justify-between shadow-md">
      <div className="flex items-center gap-3">
        {showBackButton ? (
          <button
            onClick={() => navigate(-1)}
            className="text-white hover:bg-white/10 p-2 rounded-lg transition-colors"
            aria-label="뒤로 가기"
          >
            <ArrowLeft size={24} />
          </button>
        ) : (
          <button
            className="text-white hover:bg-white/10 p-2 rounded-lg transition-colors"
            aria-label="메뉴"
          >
            <Menu size={24} />
          </button>
        )}
        {title && (
          <h1 className="text-white text-lg font-semibold truncate max-w-[200px]">
            {title}
          </h1>
        )}
      </div>

      {!title && (
        <div className="text-white text-xl font-bold">WEBTOON PLUS</div>
      )}

      <div className="flex items-center gap-3">
        <button
          className="text-white hover:bg-white/10 p-2 rounded-lg transition-colors"
          aria-label="검색"
        >
          <Search size={24} />
        </button>
        <button
          className="text-white hover:bg-white/10 p-2 rounded-lg transition-colors"
          aria-label="알림"
        >
          <Bell size={24} />
        </button>
      </div>
    </header>
  );
}
